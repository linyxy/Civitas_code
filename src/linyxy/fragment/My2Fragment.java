package linyxy.fragment;


import linyxy.civitas.FullscreenActivity;
import linyxy.civitas.LetterTabHostActivity;
import linyxy.civitas.R;
import linyxy.civitas.WorkActivity;
import linyxy.civitas.util.DataRequest;
import linyxy.civitas.util.UpdateData;
import structure.SharedPreferenceUtil;
import urlimageviewhelper.UrlImageViewHelper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class My2Fragment  extends Fragment {
	
	private ImageButton massage;
	private ImageButton workplace;
	private ImageButton storeage;
	private ImageButton domicile;
	private ImageButton recipes;
	private ImageButton estates;
	
	private TextView energy_points;
	private TextView happy_points;
	private TextView health_points;
	private TextView hunger_points;
	
	private ImageView icon;
	
	private String workP_id;
	
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	    } 
	    
	    
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) { 
	    	View view=inflater.inflate(R.layout.my_second_fragment,container, false);
	    	massage = (ImageButton)view.findViewById(R.id.messages);
			massage.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent startMessageActivity = new Intent();
					startMessageActivity.setClass(getActivity(),LetterTabHostActivity.class);
					startActivity(startMessageActivity);
				}
		
			});
			
			workplace = (ImageButton)view.findViewById(R.id.workplace);
			workplace.setOnClickListener(new WorkPlaceListener());//绑定监听
			storeage = (ImageButton)view.findViewById(R.id.storeage);
			domicile = (ImageButton)view.findViewById(R.id.domicile);
			recipes = (ImageButton)view.findViewById(R.id.recipes);
			estates = (ImageButton)view.findViewById(R.id.estates);
	    	
			
			
	    	energy_points = (TextView)view.findViewById(R.id.energy_points);
	    	happy_points  = (TextView)view.findViewById(R.id.happy_points);
	    	health_points = (TextView)view.findViewById(R.id.health_points);
	    	hunger_points = (TextView)view.findViewById(R.id.hunger_points);

	    	icon = (ImageView)view.findViewById(R.id.user_icon);
	    	
	    	
	        return view;
	    }
	    
	    
		@Override
		public void onStart() {
			UpdateData updata = new UpdateData(My2Fragment.this.getActivity());
			updata.execute("getMyStatus");
			//刷新精力等
	    	energy_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity()
	    			, DataRequest.pseronStatus, "stamina"));
	    	happy_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity()
	    			, DataRequest.pseronStatus, "happiness"));
	    	health_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity()
	    			, DataRequest.pseronStatus, "health"));
	    	hunger_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity()
	    			, DataRequest.pseronStatus, "starvation"));
	    	
	    	String url= SharedPreferenceUtil.readSharedPreference(getActivity()
	    			, DataRequest.pseronStatus, "avatar");
	    	//引用了github上面抓来的一个获取图片的包
	    	//地址https://github.com/koush/UrlImageViewHelper
	    	//Author koush 
	    	UrlImageViewHelper.setUrlDrawable(icon, url);
			
	    	workP_id = SharedPreferenceUtil.readSharedPreference(getActivity(), 
	    			DataRequest.pseronStatus, "work_id");
	    	//获取工作地点id
	    	//进行判断&修改外部磁贴
	    	
	    	
			super.onResume();
		}
		
	//工作磁贴的监听器
	class WorkPlaceListener implements View.OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), WorkActivity.class);
			getActivity().startActivity(intent);
		}
	}
}
