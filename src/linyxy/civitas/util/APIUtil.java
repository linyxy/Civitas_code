package linyxy.civitas.util;

import java.util.Map;

import org.json.JSONObject;

import structure.DialogUtil;
import structure.HttpUtil;
import structure.SharedPreferenceUtil;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

/*
 * APIUtil
 * �н�API�Ĵ���ӿ�
 */
public class APIUtil {

	public static final String API = "API";
	
	private static final String BASE_URL = "http://azure33.chinacloudapp.cn/onionc/api.php";
	
	/**
	 * ͨ��post�������������ȡ���ݵĺ���
	 * ���� ����λ�� ����map
	 * @param conectPosition
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static JSONObject posting(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		Log.d(API, "send request to server| query");
		// ��������
		return new JSONObject(HttpUtil.postRequest(BASE_URL,requestMap));
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
			
			Log.d(API, "status exists? "+ response.isNull("status"));
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
				return "ActFalse "+response.getString("message");
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
}
