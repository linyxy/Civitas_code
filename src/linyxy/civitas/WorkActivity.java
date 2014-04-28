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
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 进行工作或查看工资的页面
 */
public class WorkActivity extends Activity {

	AsyncHttpClient client = new AsyncHttpClient();
	static //工作地点情况
	String workP_id;//工作地点id
	static JSONObject info;
	ImageView placeIcon;
	TextView placeName;
	TextView placeInfo;
	
	
	//区分是否工作
	public static boolean isWorkToday = false;
	//未工作
	ListView strategyList;
	//不同策略图标
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
	//已经工作
	
	//辞职
	Button quitJob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		workP_id = SharedPreferenceUtil.readSharedPreference(WorkActivity.this, 
    			DataRequest.pseronStatus, "work_id");
		//已经工作就展示已经工作
		//未工作就展示策略什么的
		if(!isWorkToday)
			{
				setContentView(R.layout.work_pre);
				strategyList = (ListView)findViewById(R.id.work_strategy_list);
				placeIcon = (ImageView)findViewById(R.id.work_place_icon);
				placeName = (TextView)findViewById(R.id.work_place_name);
				placeInfo = (TextView)findViewById(R.id.work_place_des);
				inflateWorkPre();
			}
		else 
			{
				setContentView(R.layout.work_post);
				placeIcon = (ImageView)findViewById(R.id.work_place_icon);
				placeName = (TextView)findViewById(R.id.work_place_name);
				placeInfo = (TextView)findViewById(R.id.work_place_des);
				inflateWorkPost();
			}
	}
	
	
	
	/**
	 * 从服务器获取消息
	 */
	public void get_estate_info(Context ctx)
	{
		Map<String,String> raw = DataRequestUtil.getBasic(ctx,"get_estate_info");
		raw = DataRequestUtil.appendUserAuthen(ctx, raw);
		//add work Id
		raw.put("id", workP_id);
		
		// 发送请求
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = APIUtil.convertParams(raw);
		Log.d(APIUtil.API, "start posting ");
		
		client.post(APIUtil.BASE_URL,params,new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				Log.d(APIUtil.API, "onSuccess");
				try {
					Log.d(APIUtil.API,"---->"+response.getString("status"));
					String a= APIUtil.responseDeal(WorkActivity.this, response.toString());
					inflateWorkStra(new JSONObject(a));
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				super.onSuccess(statusCode, response);
			}

			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				//DialogUtil.showDialog(ctx, "do not know failure", true);
				Log.d(APIUtil.API, "onFailure");
				super.onFailure(e, errorResponse);
			}
			
		});
	}
	
	@TargetApi(19)
	/**
	 * 填充页面用函数
	 */
	private void inflateWorkPre()
	{	
		get_estate_info(WorkActivity.this);
	}
	
	@TargetApi(19)
	private void inflateWorkStra(JSONObject content)
	{
		try {
			//添加房产信息
			inflateEstateInfo(content);
			//添加工作策略
			List<Map<String, Object>> listItems = 
					getContents(content.getJSONArray("work_strategy"));
			SimpleAdapter simpleAdapter = new SimpleAdapter(this
					, listItems 
					, R.layout.strategy_item
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
	
	private void aa(){
		
	}
	
	/**
	 * 设置地产信息
	 * @param content
	 */
	private void inflateEstateInfo(JSONObject content)
	{
		Log.d("API", content.toString());
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
			if(!content.isNull("type") && !content.isNull("level"))
			{
				Log.d("API", content.getString("type")+"  "+content.getString("level"));
				placeInfo.setText(content.getString("type")+"  "+content.getString("level"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 为Strategy填入内容
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
