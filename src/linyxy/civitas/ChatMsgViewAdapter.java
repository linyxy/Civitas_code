package linyxy.civitas;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatMsgViewAdapter extends BaseAdapter {
	
	public static interface IMsgViewType
	{
		int IMVT_COM_MSG = 0;  //对方发送
		int IMVT_TO_MSG = 1;   //自己发送
	}
	
    private static final String TAG = ChatMsgViewAdapter.class.getSimpleName();

    private List<ChatMsgEntity> chat;

    private Context context;
    
    private LayoutInflater Inflater;

    public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> chat) {
        this.context = context;
        this.chat = chat;
        Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return chat.size();
    }

    public Object getItem(int position) {
        return chat.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    


	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
	 	ChatMsgEntity entity = chat.get(position);
	 	
	 	if (entity.getMsgType())
	 	{
	 		return IMsgViewType.IMVT_COM_MSG;
	 	}else{
	 		return IMsgViewType.IMVT_TO_MSG;
	 	}
	 	
	}


	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ChatMsgEntity entity = chat.get(position);
    	boolean isComMsg = entity.getMsgType();
    		
    	ViewHolder viewHolder = null;	
	    if (convertView == null)
	    {
	    	  if (isComMsg)
			  {
				  convertView = Inflater.inflate(R.layout.chat_msg_left, null);
			  }else{
				  convertView = Inflater.inflate(R.layout.chat_msg_right, null);
			  }

	    	  viewHolder = new ViewHolder();
			  viewHolder.SendTime = (TextView) convertView.findViewById(R.id.sendtime);
			  viewHolder.UserName = (TextView) convertView.findViewById(R.id.username);
			  viewHolder.Content = (TextView) convertView.findViewById(R.id.chat_content);
			  viewHolder.ComMsg = isComMsg;
			  
			  convertView.setTag(viewHolder);
	    }else{
	        viewHolder = (ViewHolder) convertView.getTag();
	    }
		    	    
	    viewHolder.SendTime.setText(entity.getDate());
	    viewHolder.UserName.setText(entity.getName());
	    viewHolder.Content.setText(entity.getText());
	    
	    return convertView;
    }
    
    static class ViewHolder { 
        public TextView SendTime;
        public TextView UserName;
        public TextView Content;
        public boolean ComMsg = true;
    }


}
