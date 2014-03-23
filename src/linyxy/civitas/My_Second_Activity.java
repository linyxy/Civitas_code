package linyxy.civitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/*
 * 这个activity只是用来测试activity效果的
 */

public class My_Second_Activity extends Activity {

	private ImageButton massage;
	private ImageButton workplace;
	private ImageButton storeage;
	private ImageButton domicile;
	private ImageButton recipes;
	private ImageButton estates;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.my_second_fragment);
		
		massage = (ImageButton)findViewById(R.id.messages);
		massage.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startMessageActivity = new Intent();
				startMessageActivity.setClass(My_Second_Activity.this, Messages.class);
				My_Second_Activity.this.startActivity(startMessageActivity);
			}
			
		});
		
		workplace = (ImageButton)findViewById(R.id.workplace);
		storeage = (ImageButton)findViewById(R.id.storeage);
		domicile = (ImageButton)findViewById(R.id.domicile);
		recipes = (ImageButton)findViewById(R.id.recipes);
		estates = (ImageButton)findViewById(R.id.estates);
		
		
		
	}

}
