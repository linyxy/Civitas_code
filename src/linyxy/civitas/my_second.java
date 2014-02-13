package linyxy.civitas;

import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/*
 * 这个activity只是用来测试activity效果的
 */

public class my_second extends Activity {
	private ImageButton messageBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.my_second);
		messageBtn=(ImageButton)findViewById(R.id.messages);
		messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

            	Intent intent = new Intent();		       
    	        intent.setClass(my_second.this, LetterTabHostActivity.class);
    	        my_second.this.startActivity(intent);	

            }

     });
	}

}
