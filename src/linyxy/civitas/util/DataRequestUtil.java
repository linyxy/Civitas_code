package linyxy.civitas.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class DataRequestUtil {

	public DataRequestUtil() {
		// TODO Auto-generated constructor stub
		
	}
	public static JSONObject query(String conectPosition,Map<String, String> requestMap) throws Exception
	{
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + conectPosition;
		// ��������
		return new JSONObject(HttpUtil.postRequest(url,requestMap));
	}
	
	

}
