package linyxy.civitas.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
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
		String username = SharedPreferenceUtil.readSharedPreference(DataRequestUtil.this,pseronStatus, "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		//��ȡsharedP�е��û��������û�����Ϊ������ȥ������request status
		
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
	@return String �����ص㣬û�й���return null

	 */
	public void getWorkPlace()
	{
		String username = SharedPreferenceUtil.readSharedPreference(DataRequestUtil.this,pseronStatus, "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		//��ȡsharedP�е��û��������û�����Ϊ������ȥ������request workpalce
		
		
		try {
			JSONObject w = query("getWorkPlace",map);
			SharedPreferenceUtil.updateSharedPreference(DataRequestUtil.this, pseronStatus, "workPlace", w.getString("workPlace"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	


}
