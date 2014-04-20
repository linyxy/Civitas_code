package linyxy.civitas;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * 站内信类
 * @author Linyxy & Duanxin
 *
 */
public class LetterActivity extends Activity  {
	 private ImageButton writeBtn;
	 private ListView listview;
	 private LetterListAdapter letterListAdapter;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
						
		super.onCreate(savedInstanceState);	
		//requestWindowFeature(Window.FEATURE_NO_TITLE);   
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
	
	 //显示listview
	 listview=(ListView)findViewById(R.id.letterlistview);
	 
	 showlist();
	 
	 listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
	 {
		 @Override
		 public void onItemClick(AdapterView<?> adapterview,View view,int position,long arg0)
		 {
			
			    Intent intent = new Intent();
				intent.setClass(LetterActivity.this, ChatActivity.class);
				startActivity(intent); 
			
		 }
	 });
							 
	}
	
	private void showlist(){
		
		 letterListAdapter = new LetterListAdapter(this);  
	     listview.setAdapter(letterListAdapter);
	}
	
}

