package linyxy.civitas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import structure.DatabaseHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;


/*
 * 通知类
 */
public class MessageActivity extends Activity{
	
	ListView list;
	
	private int[] imageIds = {R.drawable.notifications_1,
			R.drawable.notifications_2,
			R.drawable.notifications_3,
			R.drawable.notifications_4,
			R.drawable.notifications_5};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Toast.makeText(getApplicationContext(),"通知",Toast.LENGTH_SHORT).show();
		setContentView(R.layout.messages);
		list = (ListView)findViewById(R.id.notif_list);
		//为设置adapter获取内容
		List<Map<String, Object>> listItems = getContents();
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this
			, listItems 
			, R.layout.notif_item
			, new String[]{"img", "c", "d" }//content & decription
			, new int[]{R.id.notif_img,R.id.notif_content , R.id.notif_descrip});

		list.setAdapter(simpleAdapter);

	}

	private ArrayList<Map<String, Object>> getContents() {
		
		DatabaseHelper dbHelper = new DatabaseHelper(this,DatabaseHelper.dataBaseCivi);
		//只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT* from notifications", null);
		
		ArrayList<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		while(cursor.moveToNext())
		{
			try {
			Map<String, Object> listItem = new HashMap<String, Object>();
			String item = cursor.getString(cursor.getColumnIndex("content"));
			JSONObject it = new JSONObject(item);
			String comment = it.optString("comment");
			listItem.put("c",it.opt("message"));
			listItem.put("d",comment);
			listItem.put("img", imageIds[0]);
			String img = "img";
			if(comment.contains("个人相关"))
				listItem.put(img,imageIds[0]);
			else if(comment.contains("个人相关"))
				listItem.put(img, imageIds[1]);
			else if(comment.contains("交易相关"))
				listItem.put(img, imageIds[2]);
			else if(comment.contains("工作与资产"))
				listItem.put(img, imageIds[3]);
			else if(comment.contains("教育"))
				listItem.put(img, imageIds[4]);
			listItems.add(listItem);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listItems;
	}

	@Override
	protected void onResume() {
		
		super.onResume();
	}
	
	
}

