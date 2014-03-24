package linyxy.fragment;


import linyxy.civitas.LetterTabHostActivity;
import linyxy.civitas.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class My2Fragment  extends Fragment {
	
	private ImageButton massage;
	private ImageButton workplace;
	private ImageButton storeage;
	private ImageButton domicile;
	private ImageButton recipes;
	private ImageButton estates;
	
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
	    	
	        return view;
	    }
	}

