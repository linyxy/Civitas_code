package linyxy.civitas.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

/*
 * ����һ�������������������࣬
 * ����������������������
 * ������ˢ�����ݿ⣬sharedP��������ݵĺ���
 */
public class DataRequestUtil {

	
	public DataRequestUtil() {
		// TODO Auto-generated constructor stub
		
	}
	public static JSONObject query(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + conectPosition+".jsp";
		// ��������
		return new JSONObject(HttpUtil.postRequest(url,requestMap));
	}
	
	public static JSONArray requestData(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + conectPosition+".jsp";
		// ��������
		return new JSONArray(HttpUtil.postRequest(url,requestMap));
	}
	
	
	/**��ȡ��ǰ��ҵ�����ֵ
	@input ����ʺ�
	@return һ������������֣��ȼ������飬���������֣��������������б�
	 */
	@SuppressWarnings("null")
	public static void getStatus(Context ctx)
	{
		
		String username = SharedPreferenceUtil.readSharedPreference(ctx,"personStatus", "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		//��ȡsharedP�е��û��������û�����Ϊ������ȥ������request status
		
		List<String> key = null;
		key.add("name");
		key.add("level");
		key.add("Exp");
		key.add("energy");
		key.add("happy");
		key.add("health");
		key.add("hunger");
		//�����б�
		
		
		//���õ����ݴ���sharedP
		try {
			JSONObject status = query("getStatus",map);
			
			for(String in:key)
			{
				SharedPreferenceUtil.updateSharedPreference(ctx,"personStatus", in, status.getString(in));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("�ڻ�ȡstatusʱ���ִ���");
			DialogUtil.showDialog(ctx, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
			e.printStackTrace();
		}
		
		return;
		
	}
	
	/**��ȡ������ַ
	@input �û���
	@return String �����ص㣬û�й���return null

	 */
	public static void getWorkPlace(Context ctx)
	{
		String username = SharedPreferenceUtil.readSharedPreference(ctx,"personStatus", "unserNmae");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", username);
		//��ȡsharedP�е��û��������û�����Ϊ������ȥ������request workpalce
		
		
		try {
			JSONObject w = query("getWorkPlace",map);
			SharedPreferenceUtil.updateSharedPreference(ctx, "personStatus", "workPlace", w.getString("workPlace"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	


}
