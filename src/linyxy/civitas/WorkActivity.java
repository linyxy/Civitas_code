package linyxy.civitas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/*
 * ���й�����鿴���ʵ�ҳ��
 */
public class WorkActivity extends Activity {

	//�����ص����
	ImageView placeIcon;
	TextView placeName;
	TextView placeInfo;
	
	//�����Ƿ���
	public static boolean isWorkToday = false;
	//δ����
	ListView strategyList;
	//�Ѿ�����
	
	//��ְ
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
