package linyxy.civitas.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONAnalysis {

	public JSONAnalysis() {
		// TODO Auto-generated constructor stub
	}

	public static List<JSONObject> JSONArrayDivider(JSONArray arr)
	{
		List<JSONObject> list = new ArrayList<JSONObject>();
		for(int i =0;i<arr.length();i++)
		{
			try {
				list.add(arr.getJSONObject(i));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
