package linyxy.civitas;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;



public class ChatActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */

	private Button BtnSend;
	private Button BtnBack;
	private EditText EditTextContent;
	private ListView listview;
	private ChatMsgViewAdapter Adapter;
	private List<ChatMsgEntity> DataArrays = new ArrayList<ChatMsgEntity>();
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_user);
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        
        initView();
        
        initData();
    }
    
    
    public void initView()
    {
    	listview = (ListView) findViewById(R.id.listview);
    	
    	BtnSend = (Button) findViewById(R.id.btn_send);
    	BtnSend.setOnClickListener(this);
    	BtnBack = (Button) findViewById(R.id.btn_back);
    	BtnBack.setOnClickListener(this);
    	
    	EditTextContent = (EditText) findViewById(R.id.send_message_edit);
    }
    
    private String[] msgArray = new String[]{"天地一蜉蝣","不识晦朔，无意春秋。取足一日，尚又何求？戏渟淹而委余，何必江湖而是游。"};
    
    private String[] dataArray = new String[]{"2014-04-07 9:00", "2014-04-07 9:01", }; 
    private final static int COUNT = 2;
    
    public void initData()
    {
    	for(int i = 0; i < COUNT; i++)
    	{
    		ChatMsgEntity entity = new ChatMsgEntity();
    		entity.setDate(dataArray[i]);
    		if (i % 2 == 0)
    		{
    			entity.setName("天地");
    			entity.setMsgType(true);
    		}else{
    			entity.setName("一蜉蝣");
    			entity.setMsgType(false);
    		}
    		
    		entity.setText(msgArray[i]);
    		DataArrays.add(entity);
    	}

    	Adapter = new ChatMsgViewAdapter(this, DataArrays);
		listview.setAdapter(Adapter);
		
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_send:
			send();
			break;
		case R.id.btn_back:
			finish();
			break;
		}
	}
	
	private void send()
	{
		String contString = EditTextContent.getText().toString();
		if (contString.length() > 0)
		{
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(getDate());
			entity.setName("一蜉蝣");
			entity.setMsgType(false);
			entity.setText(contString);
			
			DataArrays.add(entity);
			Adapter.notifyDataSetChanged();
			
			EditTextContent.setText("");
			
			listview.setSelection(listview.getCount() - 1);
		}
	}
	
    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));
        
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
        						
        						
        return sbBuffer.toString();
    }
    
    
   
}