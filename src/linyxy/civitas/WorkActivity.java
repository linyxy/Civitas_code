package linyxy.civitas;

import java.util.Map;

import linyxy.civitas.model.MyStatus;
import linyxy.civitas.util.APIUtil;
import linyxy.civitas.util.DataRequest;
import linyxy.civitas.util.DataRequestUtil;

import org.json.JSONException;
import org.json.JSONObject;

import structure.DialogUtil;
import structure.SharedPreferenceUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;

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
	//已经工作
	
	//辞职
	Button quitJob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		workP_id = SharedPreferenceUtil.readSharedPreference(WorkActivity.this, 
    			DataRequest.pseronStatus, "work_id");
		
		if(!isWorkToday)
			{
				setContentView(R.layout.work_pre);
				strategyList = (ListView)findViewById(R.id.work_strategy_list);
				
			}
		else 
			{
				setContentView(R.layout.work_post);
			}
		
		placeIcon = (ImageView)findViewById(R.id.work_place_icon);
		placeName = (TextView)findViewById(R.id.work_place_name);
		placeInfo = (TextView)findViewById(R.id.work_strategy_info);
		
		
		
		
	}

	public static void get_estate_info(Context ctx)
	{
		Map<String,String> raw = DataRequestUtil.getBasic(ctx,"get_estate_info");
		raw = DataRequestUtil.appendUserAuthen(ctx, raw);
		//add work Id
		raw.put("id", workP_id);
		
		String repon = APIUtil.query(ctx, "",raw);
		Log.d(DataRequestUtil.dataR, "conneted to server");
		if(repon.startsWith("bad"))
			DialogUtil.showDialog(ctx, repon, false);
		Log.d(DataRequestUtil.dataR, repon);
		
		try {
			info = new JSONObject(repon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
