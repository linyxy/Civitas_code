package linyxy.civitas;

import linyxy.civitas.util.SharedPreferenceUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 用于发送新的站内信的activity
 * @author linyxy
 *
 */
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
	
		//初始化内容
		intiView();
		
		send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO 进行站内信发送
				
			}
			
		});
		
		/*
		 * 取消按钮监听器
		 * 保存已经写入的后
		 * 返回上一个activity
		 */
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				/*
				 * 向SharedP中存入
				 * "letter"
				 * @receiver
				 * @content
				 */
				SharedPreferenceUtil.updateSharedPreference(getApplicationContext(), "letter", "receiver"
						,receiver.getText().toString());
				SharedPreferenceUtil.updateSharedPreference(getApplicationContext(), "letter", "content"
						, content.getText().toString());
				
				NewLetterActivity.this.onDestroy();
			}
			
		});
		
	}
	
	
	
	public void intiView()
	{
		String Sreceiver;
		String Scontent;
		
		Sreceiver = SharedPreferenceUtil.readSharedPreference(NewLetterActivity.this,"letter","receiver");
		Scontent  = SharedPreferenceUtil.readSharedPreference(getApplicationContext(), "letter", "content");
		
		receiver.setText(Sreceiver);
		content.setText(Scontent);
	}
	
	

}
