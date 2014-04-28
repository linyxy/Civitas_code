package linyxy.civitas.util;

import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import structure.DialogUtil;
import structure.HttpUtil;
import structure.SharedPreferenceUtil;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.RequestParams;

/*
 * APIUtil
 * �н�API�Ĵ���ӿ�
 */
public class APIUtil {

	public static final String API = "API";
	
	public static final String BASE_URL = "http://azure33.chinacloudapp.cn/onionc/api.php";
	static JSONObject repon= new JSONObject();
	
	/**
	 * ͨ��post�������������ȡ���ݵĺ���
	 * ���� ����λ�� ����map
	 * @param conectPosition
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	private static JSONObject posting(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		
		// ���巢�������URL
		Log.d(API, "send request to server| query");
		// ��������
		return new JSONObject(HttpUtil.postRequest(BASE_URL,requestMap));
		
	}
	
	
	/*
	 * ��post����ת����asyncHttpClient��������
	 */
	public static RequestParams convertParams(Map<String, String> rawParams)
	{
		RequestParams params = new RequestParams();
		for(String key: rawParams.keySet())
		{
			System.out.println("key name: "+key+" key value: "+rawParams.get(key));
			//��װ�������
			params.put(key , rawParams.get(key));
		}
		return params;
	}
	
	/**
	 * �����ڲ��������ӽӿ�
	 * ��Է���JSON������Ԥ�ȴ���
	 * @param ctx
	 * @param conectPosition
	 * @param requestMap
	 * @return  JSONObject �������ݣ�"false" �����Ӧʧ�ܣ����ǲ�����֤�����"badToken" ��Ӧʧ��token�����"badServer" ����������Ӧ
	 */
	public static String query(Context ctx,String conectPosition,Map<String, String> requestMap)
	{
		JSONObject response = new JSONObject();
		
		try {
			response = posting(conectPosition, requestMap);
			
			Log.d(API, "status exists? "+ !response.isNull("status"));
			Log.d(API, "status getInt ?"+ response.getInt("status"));
			
			if(!response.isNull("status") && response.getInt("status")==0)
			{
				//if(response.isNull("data"))
				
				Log.d(API, "correct request");
				return response.getString("data");

			}
			else if(!response.isNull("status") && response.getInt("status")<20000)
			{
				//�����Ӧʧ�ܣ����ǲ�����֤����
				return "badAct "+response.getString("message");
			}
			else if(!response.isNull("status") && response.getInt("status")>=20000)
			{
				//��Ӧʧ��token����
				SharedPreferenceUtil.updateSharedPreference(ctx, DataRequestUtil.pseronStatus,"token","");
				return  "badToken "+ response.getString("message");
			}
			
		} catch (Exception e) {
			
			Log.w(API, "<!--Server response FAILED! -->"+response.toString());
			
			e.printStackTrace();
		}
		
		
		
		return "badServer";
	}
	
	/**
	 * ��Server���ؽ��������Ӧ
	 */
	public static String responseDeal(Context ctx,String r)
	{
		try {
			JSONObject response = new JSONObject(r);
			Log.d(API, "status exists? "+ !response.isNull("status"));
			Log.d(API, "status getInt ?"+ response.getInt("status"));
			
			if(!response.isNull("status") && response.getInt("status")==0)
			{
				//if(response.isNull("data"))
				
				Log.d(API, "correct request");
				return response.getString("data");

			}
			else if(!response.isNull("status") && response.getInt("status")<20000)
			{
				//�����Ӧʧ�ܣ����ǲ�����֤����
				DialogUtil.showDialog(ctx, response.getString("message"), false);
				return "badAct "+response.getString("message");
			}
			else if(!response.isNull("status") && response.getInt("status")>=20000)
			{
				//��Ӧʧ��token����
				DialogUtil.showDialog(ctx, response.getString("message"), false);
				SharedPreferenceUtil.updateSharedPreference(ctx, DataRequestUtil.pseronStatus,"token","");
				return  "badToken "+ response.getString("message");
			}
			
		} catch (Exception e) {
			
			Log.w(API, "<!--Server response FAILED! -->"+r);
			
			e.printStackTrace();
		}
		
		
		
		return "badServer";
	}
	
}
