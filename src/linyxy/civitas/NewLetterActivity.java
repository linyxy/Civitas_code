package linyxy.civitas;

import structure.SharedPreferenceUtil;
import linyxy.civitas.util.UpdateData;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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
	@SuppressLint("ShowToast")
	public Handler NLAHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x2468)
			{
				Log.d("H", "Handler is in use");
				
				
				Intent intent = new Intent();  
	            intent.setClass(NewLetterActivity.this,MainActivity.class);  
	            startActivity(intent);
	            
				//如果发送成功
				//清除SharedP中缓存
				//关闭这个activity
				//TODO 使用handler处理
				SharedPreferenceUtil.updateSharedPreference(NewLetterActivity.this, "letter", "receiver"
						,"");
				SharedPreferenceUtil.updateSharedPreference(getApplicationContext(), "letter", "content"
						, "");
				
				receiver.setText("");
				content.setText("");
				Toast.makeText(NewLetterActivity.this,"站内信发送成功", Toast.LENGTH_SHORT).show();
				
				
				
	            NewLetterActivity.this.onStop();
			}
			if(msg.what == 0x2467)
			{
				Toast.makeText(getApplicationContext(), "站内信发送失败!", Toast.LENGTH_SHORT);
			}
			super.handleMessage(msg);
		}
		
	};
	public static final String letter = "letter";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
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
				
				Toast.makeText(getApplicationContext(),"正在发送中", Toast.LENGTH_SHORT).show();
				//进入后台发送站内
				UpdateData update = new UpdateData(NewLetterActivity.this,NLAHandler);
				update.execute("letter",receiver.getText().toString(),content.getText().toString());
				
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

				
				NewLetterActivity.this.onStop();
			}
			
		});
		
	}
	
	
	
	@Override
	protected void onStop() {
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
		super.onStop();
	}



	public void intiView()
	{
		Log.d(letter, "init new letter view");
		String Sreceiver="";
		String Scontent="";
		
		try {
			Sreceiver = SharedPreferenceUtil.readSharedPreference(NewLetterActivity.this,"letter","receiver");
			Scontent  = SharedPreferenceUtil.readSharedPreference(getApplicationContext(), "letter", "content");
		} catch (NullPointerException e) {
		
			e.printStackTrace();
		}
		
		
		receiver.setText(Sreceiver);
		content.setText(Scontent);
	}
	
	

}
