package linyxy.civitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Õ¾ÄÚÐÅÀà
 * @author Linyxy & Duanxin
 *
 */
public class LetterActivity extends Activity{
	 private ImageButton writeBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);					
		setContentView(R.layout.station_letter);
		
		writeBtn = (ImageButton) findViewById(R.id.write);
		writeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(LetterActivity.this, NewLetterActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	
}

