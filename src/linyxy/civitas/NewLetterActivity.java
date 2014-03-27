package linyxy.civitas;

import structure.SharedPreferenceUtil;
import linyxy.civitas.util.UpdateData;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ���ڷ����µ�վ���ŵ�activity
 * @author linyxy
 *
 */
public class NewLetterActivity extends Activity {
	TextView receiver;
	TextView content;
	Button send;
	Button cancel;
	
	public static final String letter = "letter";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_letter_new);
		
		receiver = (TextView)findViewById(R.id.letter_receiver);
		content = (TextView)findViewById(R.id.letter_content);
		send = (Button)findViewById(R.id.letter_send);
		cancel = (Button)findViewById(R.id.letter_cancel);
	
		//��ʼ������
		intiView();
		
		send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO ����վ���ŷ���
				Toast.makeText(getApplicationContext(),"���ڷ�����", Toast.LENGTH_SHORT).show();
				//�����̨����վ��
				UpdateData update = new UpdateData(NewLetterActivity.this);
				update.execute("letter",receiver.getText().toString(),content.getText().toString());
				
				
				//������ͳɹ�
				//���SharedP�л���
				//�ر����activity
				//TODO ʹ��handler����
				SharedPreferenceUtil.updateSharedPreference(getApplicationContext(), "letter", "receiver"
						,"");
				SharedPreferenceUtil.updateSharedPreference(getApplicationContext(), "letter", "content"
						, "");
				Toast.makeText(NewLetterActivity.this,"վ���ŷ��ͳɹ�", Toast.LENGTH_SHORT).show();
				NewLetterActivity.this.onDestroy();
			}
			
		});
		
		/*
		 * ȡ����ť������
		 * �����Ѿ�д��ĺ�
		 * ������һ��activity
		 */
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				/*
				 * ��SharedP�д���
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
		Log.d(letter, "init new letter view");
		String Sreceiver="";
		String Scontent="";
		
		try {
			Sreceiver = SharedPreferenceUtil.readSharedPreference(NewLetterActivity.this,"letter","receiver");
			Scontent  = SharedPreferenceUtil.readSharedPreference(getApplicationContext(), "letter", "content");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		receiver.setText(Sreceiver);
		content.setText(Scontent);
	}
	
	

}
