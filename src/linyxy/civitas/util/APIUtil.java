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
 * 承接API的处理接口
 */
public class APIUtil {

	public static final String API = "API";
	
	public static final String BASE_URL = "http://azure33.chinacloudapp.cn/onionc/api.php";
	static JSONObject repon= new JSONObject();
	
	/**
	 * 通过post函数向服务器调取内容的函数
	 * 传入 连接位置 请求map
	 * @param conectPosition
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	private static JSONObject posting(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		
		// 定义发送请求的URL
		Log.d(API, "send request to server| query");
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(BASE_URL,requestMap));
		
	}
	
	
	/*
	 * 将post内容转化成asyncHttpClient可用数据
	 */
	public static RequestParams convertParams(Map<String, String> rawParams)
	{
		RequestParams params = new RequestParams();
		for(String key: rawParams.keySet())
		{
			System.out.println("key name: "+key+" key value: "+rawParams.get(key));
			//封装请求参数
			params.put(key , rawParams.get(key));
		}
		return params;
	}
	
	/**
	 * 程序内部调用连接接口
	 * 会对返回JSON进行先预先处理
	 * @param ctx
	 * @param conectPosition
	 * @param requestMap
	 * @return  JSONObject 正常内容｜"false" 如果响应失败，但是不是验证问题｜"badToken" 响应失败token问题｜"badServer" 服务器无响应
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
				//如果响应失败，但是不是验证问题
				return "badAct "+response.getString("message");
			}
			else if(!response.isNull("status") && response.getInt("status")>=20000)
			{
				//响应失败token问题
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
	 * 将Server返回结果进行响应
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
				//如果响应失败，但是不是验证问题
				DialogUtil.showDialog(ctx, response.getString("message"), false);
				return "badAct "+response.getString("message");
			}
			else if(!response.isNull("status") && response.getInt("status")>=20000)
			{
				//响应失败token问题
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
