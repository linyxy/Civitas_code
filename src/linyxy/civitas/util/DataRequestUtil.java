package linyxy.civitas.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linyxy.civitas.FullscreenActivity;
import linyxy.civitas.R;
import linyxy.civitas.SQLiteActivity;
import linyxy.civitas.model.Notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import structure.DatabaseHelper;
import structure.DialogUtil;
import structure.HttpUtil;
import structure.Md5Util;
import structure.SharedPreferenceUtil;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
	
	
	//--------------基础设置和授权---------------
	/**
	 * 获取version码
	 * @param ctx
	 * @return
	 */
	public static Map<String, String> getV(Context ctx)
	{
		Map<String,String> v = new HashMap<String,String>();
		
		v.put("v", ctx.getString(R.string.v));
		
		return v;
	}
	/**
	 * 获取v＋m
	 * @param ctx
	 * @param model 目标模组名字
	 * @return
	 */
	public static Map<String,String> getBasic(Context ctx,String model)
	{
		Map<String,String> basic = getV(ctx);
		basic.put("m", model);
		return basic;
	}
	

	
	/**
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
	
	/**
	 * 获取授权方式：user
	 * @param ctx
	 * @param raw 需要追加的map
	 * @return
	 */
	public static Map<String,String> appendUserAuthen(Context ctx,Map<String,String> raw)
	{
		raw.put("token", getToken(ctx));
		return raw;
	}
	
	/**
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
	
	/**
	 * 获取一个nonce
	 * @return
	 */
	private static String getNonce()
	{
		int in = (int)(Math.random()*10000);
		return String.valueOf(in);
	}
	
	/**
	 * ping一下服务器
	 * @param ctx
	 * @return "pong" | "badServer"
	 */
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
	
	/*
	 * 登陆相关---------------------
	 */
	

	/**
	 * 基础登陆
	 * @param ctx
	 * @param name
	 * @param password
	 * @return
	 */
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
				Log.d(dataR, "get token fro	m success login");
				SharedPreferenceUtil.updateSharedPreference(ctx, 
						DataRequestUtil.pseronStatus, "token",r.getJSONObject("data").getString("token"));
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
	

	/**
	 * 获取token的函数
	 * @param ctx
	 * @return
	 */
	private static String getToken(Context ctx)
	{
		return SharedPreferenceUtil.readSharedPreference(ctx, pseronStatus, "token");
	}
	
	
	/**
	 * 获取通知
	 * @param ctx
	 * @return
	 */
	public static String get_notifications_S(Context ctx)
	{
		Map<String,String> raw = getBasic(ctx, "get_notifications");
		raw = appendUserAuthen(ctx, raw);
		raw.put("page","1" );
		
		try {
			JSONObject response = query(null, raw);
			if(response.isNull("data"))
				return "badServer";
			
			JSONArray notifList = response.getJSONArray("data");
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();
			SQLiteActivity sql = new SQLiteActivity(ctx);
			for(int i =0 ;i<notifList.length();i++)
			{
				Log.d("SQL",notifList.getJSONObject(i).toString());
				list.add(notifList.getJSONObject(i));
			}
			sql.refreshCrudeData("notifications", list, "content");
			
			return "notificationTrue";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "badServer";
	}
	
	public static List<Notification> get_notifications(Context ctx)
	{
		DatabaseHelper dbHelper = new DatabaseHelper(ctx,DatabaseHelper.dataBaseCivi);
		ArrayList<Notification> notifs = new ArrayList<Notification>();
		SQLiteDatabase dd = dbHelper.getReadableDatabase();
		Cursor cursor = dd.query("notifications", null, null, null, null, null, null);
		
		
		while(cursor.moveToNext())
		{
			String raw = cursor.getString(cursor.getColumnIndex("content"));
			try {
					JSONObject obj = new JSONObject(raw);
					Map<String,String> related_links = new HashMap<String,String>();
					//related_links.put(key, value)TODO
					for(int i = 0;i<Notification.links.length;i++)
					{
						String name = Notification.links[i];
						
						if(!obj.isNull(name))
						{
							related_links.put(name, obj.getString(name));
						}
					}
					
					Notification notif = new Notification(obj.optString("id"), obj.optBoolean("is_unread"),
							obj.optString("message"),obj.optString("related_entity_id"), obj.optString("related_entity_name"),
							related_links, obj.optString("comment"));
					notifs.add(notif);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return notifs;
	}
	


//--------------------Old Sever Code------------------	
//--------------------Old Sever Code------------------	
	//--------------------Old Sever Code------------------	
	//--------------------Old Sever Code------------------	
	//--------------------Old Sever Code------------------	
	//--------------------Old Sever Code------------------	
	//--------------------Old Sever Code------------------	

	
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
		
		
		
	/*	Map<String,String> letter = getToken(ctx);
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
		*/
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
