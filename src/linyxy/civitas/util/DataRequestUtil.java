package linyxy.civitas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linyxy.civitas.SQLiteActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/*
 * ����һ�������������������࣬
 * ����������������������
 * ������ˢ�����ݿ⣬sharedP��������ݵĺ���
 * �൱��Http�����ݿ����
 */
public class DataRequestUtil extends Activity{

	public static final String pseronStatus = "personStatus";
	static String dataR = "DataRequest";
	
	public DataRequestUtil() {
		// TODO Auto-generated constructor stub
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		String action;
		
	}
	
	public void process(String action)
	{
//		if(action.equals("getStatus")) getStatus();
//		if(action.equals("getChat")) getChat();
//		if(action.equals("SharedTest")) sharePTest(null);
		
	}

	/**
	 * ͨ��post�������������ȡ���ݵĺ���
	 * ���� ����λ�� ����map
	 * @param conectPosition
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static JSONObject query(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		Log.d(dataR, "send request to server| query");
		String url = HttpUtil.BASE_URL + conectPosition+".jsp";
		// ��������
		return new JSONObject(HttpUtil.postRequest(url,requestMap));
	}
	
	public static JSONArray requestData(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		Log.d(dataR, "send request to server | requestData");
		String url = HttpUtil.BASE_URL + conectPosition+".jsp";
		// ��������
		return new JSONArray(HttpUtil.postRequest(url,requestMap));
	}
	
	
	/**��ȡ��ǰ��ҵ�����ֵ
	@input ����ʺ�
	@return һ������������֣��ȼ������飬���������֣��������������б�
	 */
	@SuppressWarnings("null")
	public void getStatus()
	{
		Map<String, String> map =getName();
		
		
		List<String> key = null;
		key.add("name");
		key.add("level");
		key.add("Exp");
		key.add("energy");
		key.add("happy");
		key.add("health");
		key.add("hunger");
		//�����б�
		
		
		//���õ����ݴ���sharedP
		try {
			JSONObject status = query("getStatus",map);
			
			for(String in:key)
			{
				SharedPreferenceUtil.updateSharedPreference(DataRequestUtil.this,pseronStatus, in, status.getString(in));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("�ڻ�ȡstatusʱ���ִ���");
			DialogUtil.showDialog(DataRequestUtil.this, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
			e.printStackTrace();
		}
		
		return;
		
	}
	
	/**��ȡ������ַ
	@input �û���
	@return String �����ص㣬û�й���return null��������SharedP��

	 */
	public void getWorkPlace()
	{
		Map<String, String> map = getName();
		
		try {
			JSONObject w = query("getWorkPlace",map);
			SharedPreferenceUtil.updateSharedPreference(DataRequestUtil.this, pseronStatus, "workPlace", w.getString("workPlace"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	/**
	 * �ӷ�������ȡchats�ĺ���
	 */
	public void getChat()
	{
		Map<String, String> map = getName();
		
		try {
			JSONArray arr = requestData("getChat",map);
			//�ӷ�������ȡ��JSONArray��Ҫ���н�����Ȼ�����SQLite�еȴ���ȡ
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �ӷ�������ȡNotifications�ĺ���
	 */
	public void getNotifications()
	{
		Map<String,String> map = getName();
		
		try{
			JSONArray arr = requestData("getNotifications",map);	
			List<JSONObject> array = new ArrayList<JSONObject>();
			array = JSONAnalysis.JSONArrayDivider(arr);
			SQLiteActivity SQL = new SQLiteActivity();
			SQL.refreshData("notifications", array, "content","notificationType");
			
			
		} catch (Exception e) {
			Log.d(dataR, "something wrong when handling the notifications");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * ���ڶ�ȡsharedP�е��û���
	 * @return map containing of name
	 */
	public Map<String, String> getName()
	{
		String username = SharedPreferenceUtil.readSharedPreference(DataRequestUtil.this,pseronStatus, "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		return map;
	}
	
	public static void sharePTest(Context ctx)
	{
		Log.d(dataR, "Tring to put sample SharedP");
		SharedPreferenceUtil.updateSharedPreference(ctx, pseronStatus, "ShareTest", "sample imput");
		
		String outByThisCtx = SharedPreferenceUtil.readSharedPreference(ctx, pseronStatus, "ShareTest");
		Log.d(dataR, outByThisCtx);
		
		String outByAppCtx = SharedPreferenceUtil.readSharedPreference(ctx.getApplicationContext(), pseronStatus, "ShareTest");
		Log.d(dataR, outByAppCtx);
		
		return ;
	}

}
