package linyxy.civitas.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linyxy.civitas.R;
import linyxy.civitas.SQLiteActivity;
import linyxy.civitas.model.Notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import structure.DatabaseHelper;
import structure.Md5Util;
import structure.SharedPreferenceUtil;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
 * ����һ�������������������࣬
 * ����������������������
 * ������ˢ�����ݿ⣬sharedP��������ݵĺ���
 * �൱��Http�����ݿ����
 */

public class DataRequest {

	public static final String pseronStatus = "personStatus";
	public static final String dataR = "DataRequest";

	/*
	 * ���´�����ݷ�����API��д
	 * û��ע�͵ĺ����붼�ο�API
	 */
	
	
	//--------------�������ú���Ȩ---------------
	/**
	 * ��ȡversion��
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
	 * ��ȡv��m
	 * @param ctx
	 * @param model Ŀ��ģ������
	 * @return
	 */
	public static Map<String,String> getBasic(Context ctx,String model)
	{
		Map<String,String> basic = getV(ctx);
		basic.put("m", model);
		return basic;
	}
	

	
	/**
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
	
	/**
	 * ��ȡ��Ȩ��ʽ��user
	 * @param ctx
	 * @param raw ��Ҫ׷�ӵ�map
	 * @return
	 */
	public static Map<String,String> appendUserAuthen(Context ctx,Map<String,String> raw)
	{
		raw.put("token", getToken(ctx));
		return raw;
	}
	
	/**
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
	
	/**
	 * ��ȡһ��nonce
	 * @return
	 */
	private static String getNonce()
	{
		int in = (int)(Math.random()*10000);
		return String.valueOf(in);
	}
	
	/**
	 * pingһ�·�����
	 * @param ctx
	 * @return "pong" | "badServer"
	 */
	public static String ping(Context ctx)
	{
		Map<String,String> pi = getBasic(ctx,"ping");
		try {
			String repon = APIUtil.query(ctx, "",pi);
			if(repon.contains("ActFalse"))return repon;
			if(repon.contains("badServer"))return "badServer";
			if(repon.contains("badToken"))return repon;
			
			Log.d(dataR, repon);
			
			JSONObject response = new JSONObject(repon);
			Log.d(dataR, response.optString("ping"));
			return response.optString("ping");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "badServer";
	}
	
	
	/*
	 * -------------��½���---------------------
	 */
	

	/**
	 * ������½
	 * @param ctx
	 * @param name
	 * @param password
	 * @return
	 */
	public static String basiclogin(Context ctx,String name,String password)
	{
		
		Map<String,String> baslogin = getBasic(ctx, "basiclogin");
		baslogin.put("login", name);
		baslogin.put("password", password);
		
		Map<String,String> ba = appendAppAuthen(ctx,baslogin);
		Log.d(dataR, "start to login!");
		
		String repon = APIUtil.query(ctx, "",ba);
		Log.d(dataR, "conneted to server");
		if(repon.contains("ActFalse"))return repon;
		if(repon.contains("badServer"))return "badServer";
		if(repon.contains("badToken"))return repon;

		Log.d(dataR,repon);
		try {		
			JSONObject response = new JSONObject(repon);
			//�����token�򷵻�token
			if(!response.isNull("token"))
			{
				Log.d(dataR, "get token fro	m success login");
				SharedPreferenceUtil.updateSharedPreference(ctx, 
						DataRequestUtil.pseronStatus, "token",response.getString("token"));
				return "loginTrue";
			}			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "badServer";
	}
	

	/**
	 * ��ȡtoken�ĺ���
	 * @param ctx
	 * @return
	 */
	private static String getToken(Context ctx)
	{
		return SharedPreferenceUtil.readSharedPreference(ctx, pseronStatus, "token");
	}
	
	//----------------֪ͨ���----------------
	/**
	 * ��ȡ֪ͨ
	 * @param ctx
	 * @return
	 */
	public static String get_notifications_S(Context ctx)
	{
		Map<String,String> raw = getBasic(ctx, "get_notifications");
		raw = appendUserAuthen(ctx, raw);
		
		try {
			String since_id = SharedPreferenceUtil.readSharedPreference(ctx, pseronStatus, "notif_since");
			raw.put("since_id", since_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String repon = APIUtil.query(ctx, "",raw);
		Log.d(dataR, "conneted to server");
		if(repon.contains("ActFalse"))return repon;
		if(repon.contains("badServer"))return "badServer";
		if(repon.contains("badToken"))return repon;
		
		Log.d(dataR, repon);
		
		return "badServer";
	}
	
	public static List<Notification> get_notifications(Context ctx)
	{
		DatabaseHelper dbHelper = new DatabaseHelper(ctx,DatabaseHelper.dataBaseCivi);
		ArrayList<Notification> notifs = new ArrayList<Notification>();
		SQLiteDatabase dd = dbHelper.getReadableDatabase();
		Cursor cursor = dd.query(true,"notifications", null, null, null, null, null, null, null);
		
		
		Log.d(dataR,"length of cursor--->"+cursor.getCount());
		Log.d(dataR,"length of columns--->"+cursor.getColumnCount());

		
		while(cursor.moveToNext())	{
			String raw = cursor.getString(cursor.getColumnIndex("content"));
			try {
					JSONObject obj = new JSONObject(raw);
					Map<String,String> related_links = new HashMap<String,String>();
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
}