package linyxy.fragment;


import structure.SharedPreferenceUtil;
import linyxy.civitas.LetterTabHostActivity;
import linyxy.civitas.R;
import linyxy.civitas.util.DataRequest;
import linyxy.civitas.util.UpdateData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
			storeage = (ImageButton)view.findViewById(R.id.storeage);
			domicile = (ImageButton)view.findViewById(R.id.domicile);
			recipes = (ImageButton)view.findViewById(R.id.recipes);
			estates = (ImageButton)view.findViewById(R.id.estates);
	    	
	    	energy_points = (TextView)view.findViewById(R.id.energy_points);

	    	
	    	happy_points  = (TextView)view.findViewById(R.id.happy_points);

	    	
	    	health_points = (TextView)view.findViewById(R.id.health_points);

	    	
	    	hunger_points = (TextView)view.findViewById(R.id.hunger_points);

			
	        return view;
	    }
		@Override
		public void onResume() {
			UpdateData updata = new UpdateData(My2Fragment.this.getActivity());
			updata.execute("getMyStatus");
			
	    	energy_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity(), DataRequest.pseronStatus, "stamina"));
	    	happy_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity(), DataRequest.pseronStatus, "happiness"));
	    	health_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity(), DataRequest.pseronStatus, "health"));
	    	hunger_points.setText(SharedPreferenceUtil.readSharedPreference(getActivity(), DataRequest.pseronStatus, "starvation"));
			super.onResume();
		}
	}

