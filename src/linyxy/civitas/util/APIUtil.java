package linyxy.civitas.util;

import java.util.Map;

import org.json.JSONObject;

import structure.DialogUtil;
import structure.HttpUtil;
import structure.SharedPreferenceUtil;
import android.content.Context;
import android.util.Log;

/*
 * APIUtil
 * 承接API的处理接口
 */
public class APIUtil {

	public static final String API = "API";
	
	private static final String BASE_URL = "http://azure33.chinacloudapp.cn/onionc/api.php";
	
	/**
	 * 通过post函数向服务器调取内容的函数
	 * 传入 连接位置 请求map
	 * @param conectPosition
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static JSONObject posting(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// 定义发送请求的URL
		Log.d(API, "send request to server| query");
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(BASE_URL,requestMap));
	}
	
	
	public static JSONObject query(Context ctx,String conectPosition,Map<String, String> requestMap)
	{
		JSONObject response = new JSONObject();
		
		try {
			response = posting(conectPosition, requestMap);
			if(!response.isNull("status") && response.getInt("status")==0)
			{
				//if(response.isNull("data"))
				Log.d(API, "correct request");
				return response.getJSONObject("data");
			}
			else if(!response.isNull("status") && response.getInt("status")<20000)
			{
				DialogUtil.showDialog(ctx, response.getString("message"), false);
				return new JSONObject().put("status", "false");
			}
			else
			{
				DialogUtil.showDialog(ctx, response.getString("message"), false);
				SharedPreferenceUtil.updateSharedPreference(ctx, DataRequestUtil.pseronStatus,"token","");
				return new JSONObject().put("status", "badToken");
			}
			
		} catch (Exception e) {
			
			Log.e(API, "<!--Server response FAILED! -->"+response.toString());
			
			e.printStackTrace();
		}
		
		return response;
	}
}
