package linyxy.civitas.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linyxy.civitas.FullscreenActivity;
import linyxy.civitas.R;
import linyxy.civitas.SQLiteActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import structure.DialogUtil;
import structure.HttpUtil;
import structure.Md5Util;
import structure.SharedPreferenceUtil;

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
	public static final String dataR = "DataRequest";
	private static final String BASE_URL = "http://azure33.chinacloudapp.cn/onionc/api.php";
		

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
		// ��������
		return new JSONObject(HttpUtil.postRequest(BASE_URL,requestMap));
	}
	
	public static JSONArray requestData(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		Log.d(dataR, "send request to server | requestData");
		// ��������
		
		return new JSONArray(HttpUtil.postRequest(BASE_URL,requestMap));
	}
	
	
//------------------New Sever Code-------------
	/*
	 * ���´�����ݷ�����API��д
	 * û��ע�͵ĺ����붼�ο�API
	 */
	
	public static Map<String, String> getV(Context ctx)
	{
		Map<String,String> v = new HashMap<String,String>();
		
		v.put("v", ctx.getString(R.string.v));
		
		return v;
	}

	public static Map<String,String> getBasic(Context ctx,String model)
	{
		Map<String,String> basic = getV(ctx);
		basic.put("m", model);
		return basic;
	}
	
	public static String basiclogin(Context ctx,String name,String password)
	{
		String res = "badServer";
		
		Map<String,String> baslogin = getBasic(ctx, "basiclogin");
		baslogin.put("login", name);
		baslogin.put("password", password);
		
		Map<String,String> ba = appendAppAuthen(ctx,baslogin);
		Log.d(dataR, "start to login!");
		
		try {
			JSONObject r = query("", ba);
			Log.d(dataR, "conneted to server");
			//�����token�򷵻�token
			if(!r.getJSONObject("data").isNull("token"))
			{
				Log.d(dataR, "get token from success login");
				SharedPreferenceUtil.updateSharedPreference(ctx, DataRequestUtil.pseronStatus, "token",r.getJSONObject("data").getString("token"));
				return "loginTrue";
			}
			//û��token����message
			if(!r.isNull("message"))
			{
				return r.optString("message");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * ģ����Ȩ��ʽ��app
	 */
	public static Map<String,String> appendAppAuthen(Context ctx,Map<String,String> raw)
	{
		
		String time = getTimeStamp();
		String nonce= getNonce();
		String app_s= ctx.getString(R.string.app_secret);
		raw.put("app_id", ctx.getString(R.string.app_id));
		raw.put("time", time);
		raw.put("nonce", nonce);
		raw.put("sign", Md5Util.MD5(time+app_s+nonce));
		return raw;
	}
	
	/*
	 * ��ȡһ��ʱ���
	 */
	private static String getTimeStamp()
	{
		long t = new Date().getTime();
		t = t/1000;
		String stamp = String.valueOf(t);
		
		System.out.println("current time is---->"+stamp);
		return stamp;
	}
	
	/*
	 * ��ȡһ��nonce
	 */
	private static String getNonce()
	{
		int in = (int)(Math.random()*10000);
		return String.valueOf(in);
	}
	
	public static String ping(Context ctx)
	{
		Map<String,String> pi = getBasic(ctx,"ping");
		try {
			JSONObject response = query("", pi);
			Log.d(dataR, response.getJSONObject("data").optString("ping"));
			return response.getJSONObject("data").optString("ping");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "badServer";
	}
	

//--------------------Old Sever Code------------------	
	
	
	/**
	 * �������е�½�ĺ���
	 * �����û������룬��̨���е�½
	 * @param ctx
	 * @param userName �û���
	 * @param password ����
	 * 
	 * @return boolean success/failed
	 */
	public static String login(Context ctx,String userName,String password)
	{
		try
		{
			Map<String,String> values = new HashMap<String,String>();
			values.put("userName", userName);
			values.put("password",password);
			JSONObject result = query("lgoin",values);
			// �û���������صĲ��ǡ�false��

			
			if (!result.getString("userName").equals("false"))
			{
				SharedPreferenceUtil.updateSharedPreference(ctx, DataRequestUtil.pseronStatus, "userName",result.getString("userName"));
				Log.d(FullscreenActivity.Login, "successfully logined");
				return "loginTrue";
			}
			else 
			{
				return "loginFalse";
			}
			
		}
		catch (Exception e)
		{	
			Log.d(FullscreenActivity.Login, "sever response failed");
			//DialogUtil.showDialog(ctx, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
			e.printStackTrace();
		}
		
	
		return "badSever";
	}
	
	/**��ȡ��ǰ��ҵ�����ֵ
	@input ����ʺ�
	@return һ������������֣��ȼ������飬���������֣��������������б�
	 */
	@SuppressWarnings("null")
	public void getStatus(Context ctx)
	{
		Map<String, String> map =getName(ctx);
		
		
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
	public void getWorkPlace(Context ctx)
	{
		Map<String, String> map = getName(ctx);
		
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
	public void getChat(Context ctx)
	{
		Map<String, String> map = getName(ctx);
		
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
	public void getNotifications(Context ctx)
	{
		Map<String,String> map = getName(ctx);
		
		try{
			JSONArray arr = requestData("getNotifications",map);	
			List<JSONObject> array = new ArrayList<JSONObject>();
			array = JSONAnalysis.JSONArrayDivider(arr);
			SQLiteActivity SQL = new SQLiteActivity(ctx);
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
	public static Map<String, String> getName(Context ctx)
	{
		String username = SharedPreferenceUtil.readSharedPreference(ctx,pseronStatus, "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		return map;
	}
	
	/**
	 * 
	 * ����һ���µ�վ����
	 * @param ctx
	 * @param receiver
	 * @param content
	 * @return
	 */
	public static String sendNewLetter(Context ctx,String receiver,String content)
	{
		String result="badServer";
		
		//������tag
		String cus ="newLetterTrue";
		
		
		
		Map<String,String> letter = getName(ctx);
		letter.put("TODO", "TODO");//TODO
		letter.put("receiver", receiver);
		letter.put("content", content);
		
		try {
			System.out.println("this part is runnable");
			JSONObject re = query("",letter);
			System.out.println("�õ���"+re.get(result));
			
			if(re.get(result).equals("TODO"))
				return "newLetterTrue";
			return "newLetterFalse";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("result is --->"+result);
		
		return result;
	}
	
	
	//--------test---------test---------test-------------
	public static void sharePTest(Context ctx)
	{
		Log.d(dataR, "Tring to put sample SharedP");
		SharedPreferenceUtil.updateSharedPreference(ctx, pseronStatus, "ShareTest", "sample imput");
		
		String outByThisCtx = SharedPreferenceUtil.readSharedPreference(ctx, pseronStatus, "ShareTest");
		Log.d(dataR, "ctx------>"+outByThisCtx);
		
		String outByAppCtx = SharedPreferenceUtil.readSharedPreference(ctx.getApplicationContext(), pseronStatus, "ShareTest");
		Log.d(dataR, "aplicationCtx----->"+outByAppCtx);
		
		return ;
	}
	
	public static void SQLiteTest(Context ctx)
	{
		SQLiteActivity SQL = new SQLiteActivity(ctx);
		Log.d(dataR, "Opening a SQLite test");
		SQL.tableTest();
	}

}
