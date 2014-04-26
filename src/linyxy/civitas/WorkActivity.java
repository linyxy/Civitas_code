package linyxy.civitas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/*
 * 进行工作或查看工资的页面
 */
public class WorkActivity extends Activity {

	//工作地点情况
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

}
