package linyxy.civitas;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public  class LetterListAdapter extends BaseAdapter{
private Context context;
//private ArrayList<String> data;

public LetterListAdapter(Context context){
	
	//data=list;
	this.context=context;
	
}

@Override 
public int getCount(){
	
	return 12;
}
@Override
public Object getItem(int position) {
     
    return position;
}

@Override
public long getItemId(int position) {
     
    return position;
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    convertView = LayoutInflater.from(context).inflate(R.layout.station_letter_item,
            null);  
    
    final TextView username = (TextView) convertView
			.findViewById(R.id.user_name_text);
	final TextView usermsg = (TextView) convertView
			.findViewById(R.id.user_msg_text);
	
	final TextView msgtime = (TextView) convertView
			.findViewById(R.id.msg_time_text);
    return convertView;   
}
}
