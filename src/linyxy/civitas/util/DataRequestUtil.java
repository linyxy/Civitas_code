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
 * 这是一个用来访问网络数据类，
 * 包含基础的数据请求函数，
 * 和用来刷新数据库，sharedP里面的内容的函数
 * 相当于Http＋数据库操作
 */
public class DataRequestUtil extends Activity{

	public static final String pseronStatus = "personStatus";
	public static final String dataR = "DataRequest";
	private static final String BASE_URL = "http://azure33.chinacloudapp.cn/onionc/api.php";
		

	/**
	 * 通过post函数向服务器调取内容的函数
	 * 传入 连接位置 请求map
	 * @param conectPosition
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static JSONObject query(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// 定义发送请求的URL
		Log.d(dataR, "send request to server| query");
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(BASE_URL,requestMap));
	}
	
	public static JSONArray requestData(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// 定义发送请求的URL
		Log.d(dataR, "send request to server | requestData");
		// 发送请求
		
		return new JSONArray(HttpUtil.postRequest(BASE_URL,requestMap));
	}
	
	
//------------------New Sever Code-------------
	/*
	 * 以下代码根据服务器API编写
	 * 没有注释的函数请都参考API
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
			//如果又token则返回token
			if(!r.getJSONObject("data").isNull("token"))
			{
				Log.d(dataR, "get token from success login");
				SharedPreferenceUtil.updateSharedPreference(ctx, DataRequestUtil.pseronStatus, "token",r.getJSONObject("data").getString("token"));
				return "loginTrue";
			}
			//没有token返回message
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
	 * 模块授权方式：app
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
	 * 获取一个时间戳
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
	 * 获取一个nonce
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
	 * 用来进行登陆的函数
	 * 传入用户名密码，后台进行登陆
	 * @param ctx
	 * @param userName 用户名
	 * @param password 密码
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
			// 用户名结果返回的不是“false”

			
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
			//DialogUtil.showDialog(ctx, "服(wo)务(ye)器(bu)响(zhi)应(dao)异(zen me)常(le)！", false);
			e.printStackTrace();
		}
		
	
		return "badSever";
	}
	
	/**获取当前玩家的属性值
	@input 玩家帐号
	@return 一个包含玩家名字，等级，经验，精力，快乐，健康，饥饿的列表
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
	@return String 工作地点，没有工作return null（将存入SharedP）

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
	 * 从服务器获取chats的函数
	 */
	public void getChat(Context ctx)
	{
		Map<String, String> map = getName(ctx);
		
		try {
			JSONArray arr = requestData("getChat",map);
			//从服务器获取的JSONArray需要进行解析，然后存入SQLite中等待调取
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从服务器获取Notifications的函数
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
	 * 用于读取sharedP中的用户名
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
	 * 发送一个新的站内信
	 * @param ctx
	 * @param receiver
	 * @param content
	 * @return
	 */
	public static String sendNewLetter(Context ctx,String receiver,String content)
	{
		String result="badServer";
		
		//测试用tag
		String cus ="newLetterTrue";
		
		
		
		Map<String,String> letter = getName(ctx);
		letter.put("TODO", "TODO");//TODO
		letter.put("receiver", receiver);
		letter.put("content", content);
		
		try {
			System.out.println("this part is runnable");
			JSONObject re = query("",letter);
			System.out.println("得到的"+re.get(result));
			
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
