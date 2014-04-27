package linyxy.civitas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linyxy.civitas.util.APIUtil;
import linyxy.civitas.util.DataRequest;
import linyxy.civitas.util.DataRequestUtil;
import linyxy.civitas.util.ServerResponseProcess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import structure.DatabaseHelper;
import structure.DialogUtil;
import structure.SharedPreferenceUtil;
import urlimageviewhelper.UrlImageViewHelper;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;

/*
 * ���й�����鿴���ʵ�ҳ��
 */
public class WorkActivity extends Activity {

	AsyncHttpClient client = new AsyncHttpClient();
	static //�����ص����
	String workP_id;//�����ص�id
	static JSONObject info;
	ImageView placeIcon;
	TextView placeName;
	TextView placeInfo;
	
	
	//�����Ƿ���
	public static boolean isWorkToday = false;
	//δ����
	ListView strategyList;
	//��ͬ����ͼ��
	private int[] imageIds = {R.drawable.strategy_0,
			R.drawable.strategy_1,
			R.drawable.strategy_2,
			R.drawable.strategy_1,
			R.drawable.strategy_4,
			R.drawable.strategy_1,
			R.drawable.strategy_6,
			R.drawable.strategy_1,
			R.drawable.strategy_1,
	};
	//�Ѿ�����
	
	//��ְ
	Button quitJob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		workP_id = SharedPreferenceUtil.readSharedPreference(WorkActivity.this, 
    			DataRequest.pseronStatus, "work_id");
		//�Ѿ�������չʾ�Ѿ�����
		//δ������չʾ����ʲô��
		if(!isWorkToday)
			{
				setContentView(R.layout.work_pre);
				strategyList = (ListView)findViewById(R.id.work_strategy_list);
				placeIcon = (ImageView)findViewById(R.id.work_place_icon);
				placeName = (TextView)findViewById(R.id.work_place_name);
				placeInfo = (TextView)findViewById(R.id.work_strategy_info);
				inflateWorkPre();
			}
		else 
			{
				setContentView(R.layout.work_post);
				placeIcon = (ImageView)findViewById(R.id.work_place_icon);
				placeName = (TextView)findViewById(R.id.work_place_name);
				placeInfo = (TextView)findViewById(R.id.work_strategy_info);
				inflateWorkPost();
			}
	}
	
	
	
	/**
	 * �ӷ�������ȡ��Ϣ
	 */
	public static String get_estate_info(Context ctx)
	{
		Map<String,String> raw = DataRequestUtil.getBasic(ctx,"get_estate_info");
		raw = DataRequestUtil.appendUserAuthen(ctx, raw);
		//add work Id
		raw.put("id", workP_id);
		
		String repon = APIUtil.query(ctx, "",raw);
		Log.d(DataRequestUtil.dataR, "conneted to server");
		if(repon.startsWith("bad"))
			{
				DialogUtil.showDialog(ctx, repon, false);
				ServerResponseProcess.proBadResponse(ctx, repon);
				return " ";
			}
		Log.d(DataRequestUtil.dataR, repon);
		return repon;
	}
	
	@TargetApi(19)
	/**
	 * ���ҳ���ú���
	 */
	private void inflateWorkPre()
	{	
		String sample ="";
		String res = get_estate_info(WorkActivity.this);
		if(res.equals(" "))finish();
		
		try {
			
			JSONObject content = new JSONObject(res);
			//��ӷ�����Ϣ
			inflateEstateInfo(content);
			//��ӹ�������
			List<Map<String, Object>> listItems = 
					getContents(new JSONArray(content.getJSONArray("work_strategy")));
			SimpleAdapter simpleAdapter = new SimpleAdapter(this
					, listItems 
					, R.layout.notif_item
					, new String[]{"img", "name", "effects" }//content & decription
					, new int[]{R.id.work_strategy_icon,
								R.id.work_strategy_name , 
								R.id.work_strategy_info});

			strategyList.setAdapter(simpleAdapter);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ���õز���Ϣ
	 * @param content
	 */
	private void inflateEstateInfo(JSONObject content)
	{
		try {
			if(content.has("avatar"))
			{
				String url = content.getString("avatar");
				UrlImageViewHelper.setUrlDrawable(placeIcon, url);
			}
			if(content.has("name"))
			{
				placeName.setText(content.getString("name"));
			}
			if(content.has("type") && content.has("level"))
				placeInfo.setText(content.getString("type")+"  "+content.getString("level"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ΪStrategy��������
	 * @return
	 */
	private ArrayList<Map<String, Object>> getContents(JSONArray stra) {
		
		ArrayList<Map<String, Object>> listItems 
				= new ArrayList<Map<String, Object>>();
		for(int i = 0;i<stra.length();i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			JSONObject obj;
			try {
				obj = stra.getJSONObject(i);
				listItem.put("name",obj.get("name"));
				listItem.put("effects", obj.get("tips"));
				listItem.put("img",imageIds[obj.getInt("id")+1]);
				listItems.add(listItem);
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return listItems;
	}
	
	private void inflateWorkPost()
	{
		
	}
	
	//class straSelctListener extends 
}
