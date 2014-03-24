package linyxy.civitas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LetterActivity extends Activity{
	 private ImageButton writeBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);					
		setContentView(R.layout.station_letter);
		
		writeBtn = (ImageButton) findViewById(R.id.write);
		writeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}
}

