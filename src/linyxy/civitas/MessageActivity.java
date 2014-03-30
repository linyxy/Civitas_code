package linyxy.civitas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Toast.makeText(getApplicationContext(),"֪ͨ",Toast.LENGTH_SHORT).show();
		setContentView(R.layout.station_message);
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
	}
	
	
}

