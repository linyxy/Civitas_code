package linyxy.civitas.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import structure.SharedPreferenceUtil;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
	private static JSONObject posting(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		Log.d(API, "send request to server| query");
		// ��������
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = convertParams(requestMap);
		BinaryHttpResponseHandler BHP = new BinaryHttpResponseHandler(){
			
		};
		
		client.post(BASE_URL,params,new AsyncHttpResponseHandler());
		DefaultHttpClient httpResponse =  (DefaultHttpClient) client.getHttpClient();
		
		return new JSONObject(processResponse(httpResponse));
		
	}
	
	/*
	 * �����ؽ��������Ӧ
	 */
	private static String processResponse(DefaultHttpClient httpResponse)
	{
		if (((HttpResponse) httpResponse).getStatusLine()
				.getStatusCode() == 200)
			{
				Log.d(API, "server successfully responesd");
				// ��ȡ��������Ӧ�ַ���
				String result = "";
				try {
					result = EntityUtils
						.toString(((HttpResponse) httpResponse).getEntity());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d(API, result);
				return result;
			}
		return null;
	}
	
	/*
	 * ��post����ת����asyncHttpClient��������
	 */
	private static RequestParams convertParams(Map<String, String> rawParams)
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
}
