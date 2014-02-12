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
 * 这是一个用来访问网络数据类，
 * 包含基础的数据请求函数，
 * 和用来刷新数据库，sharedP里面的内容的函数
 * 相当于Http＋数据库操作
 */
public class DataRequestUtil extends Activity{

	public static final String pseronStatus = "personStatus";
	static String dataR = "DataRequest";
	
	public DataRequestUtil() {
		// TODO Auto-generated constructor stub
		
	}
	public static JSONObject query(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// 定义发送请求的URL
		Log.d(dataR, "send request to server| query");
		String url = HttpUtil.BASE_URL + conectPosition+".jsp";
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url,requestMap));
	}
	
	public static JSONArray requestData(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// 定义发送请求的URL
		Log.d(dataR, "send request to server | requestData");
		String url = HttpUtil.BASE_URL + conectPosition+".jsp";
		// 发送请求
		return new JSONArray(HttpUtil.postRequest(url,requestMap));
	}
	
	
	/**获取当前玩家的属性值
	@input 玩家帐号
	@return 一个包含玩家名字，等级，经验，精力，快乐，健康，饥饿的列表
	 */
	@SuppressWarnings("null")
	public void getStatus()
	{
		String username = SharedPreferenceUtil.readSharedPreference(DataRequestUtil.this,pseronStatus, "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		//读取sharedP中的用户名，以用户名作为参数，去服务器request status
		
		List<String> key = null;
		key.add("name");
		key.add("level");
		key.add("Exp");
		key.add("energy");
		key.add("happy");
		key.add("health");
		key.add("hunger");
		//数据列表
		
		
		//将得到数据存入sharedP
		try {
			JSONObject status = query("getStatus",map);
			
			for(String in:key)
			{
				SharedPreferenceUtil.updateSharedPreference(DataRequestUtil.this,pseronStatus, in, status.getString(in));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("在获取status时出现错误");
			DialogUtil.showDialog(DataRequestUtil.this, "服(wo)务(ye)器(bu)响(zhi)应(dao)异(zen me)常(le)！", false);
			e.printStackTrace();
		}
		
		return;
		
	}
	
	/**获取工作地址
	@input 用户名
	@return String 工作地点，没有工作return null

	 */
	public void getWorkPlace()
	{
		String username = SharedPreferenceUtil.readSharedPreference(DataRequestUtil.this,pseronStatus, "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		//读取sharedP中的用户名，以用户名作为参数，去服务器request workpalce
		
		
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
