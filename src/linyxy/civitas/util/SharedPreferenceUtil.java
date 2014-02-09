package linyxy.civitas.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {

	
	public static final String userInfo = "userInof";
	
	public SharedPreferenceUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 一个用来更新SharedPreference的函数
	 * @param ctx
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void updateSharedPreference (Context ctx,String fileName, String key,String value)
	{
		SharedPreferences S  = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = S.edit();
		
		editor.putString(key, value);
		editor.commit();
	}
	
	
	/**
	 * 用来读取某特定键值
	 * @param ctx
	 * @param fileName
	 * @param key
	 * @return str
	 */
	public static String readSharedPreference(Context ctx,String fileName,String key)
	{
		SharedPreferences S = ctx.getSharedPreferences(fileName, 0);
		String str = S.getString(key, "");
		return str;
	}
	
	
}
