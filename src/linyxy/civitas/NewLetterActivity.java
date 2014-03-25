package linyxy.civitas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NewLetterActivity extends Activity {
	TextView receiver;
	TextView content;
	Button send;
	Button cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_letter_new);
		
		receiver = (TextView)findViewById(R.id.letter_receiver);
		content = (TextView)findViewById(R.id.letter_content);
		send = (Button)findViewById(R.id.letter_send);
		cancel = (Button)findViewById(R.id.letter_cancel);
	}

}
