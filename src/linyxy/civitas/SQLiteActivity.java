package linyxy.civitas;

import java.util.List;

import linyxy.civitas.util.DatabaseHelper;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
/*
 * ����һ����mars��ʦ�̳��������������࣬
 * ��databaseAcitivity��������ʾ���࣬
 * ����չʾSQLiteʹ�á�
 * ����ĵ�����������ָʹSQLiteʹ�ô��������ʵ���ô��롣
 */


public class SQLiteActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,DatabaseHelper.dataBaseCivi);

    }
    
    
    public  void  refreshData (String tableName,List<JSONObject> models,String... keys){
    	//����һ��DatabaseHelper����
		DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,DatabaseHelper.dataBaseCivi);
		//ֻ�е�����DatabaseHelper�����getReadableDatabase()������������getWritableDatabase()����֮�󣬲Żᴴ�������һ�����ݿ�
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		
		//���潫���ߴ����model����������ݿ�
		for(JSONObject model: models)
		{
			ContentValues values = new ContentValues();
			
			for(String key: keys)
			{
				try {
					values.put(key,model.getString(key));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//����insert�������Ϳ��Խ����ݲ��뵽���ݿ⵱��
			db.insert(tableName, null, values);
		}
    }
    
    //��������������������������������������������������������������������

    class InsertListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			//����ContentValues����
			ContentValues values = new ContentValues();
			//��ö����в����ֵ�ԣ����м���������ֵ��ϣ�����뵽��һ�е�ֵ��ֵ��������ݿ⵱�е���������һ��
			values.put("id", 1);
			values.put("name","zhangsan");
			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db",2);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//����insert�������Ϳ��Խ����ݲ��뵽���ݿ⵱��
			db.insert("user", null, values);
		}
    }
    //���²������൱��ִ��SQL��䵱�е�update���
    //UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
    class UpdateRecordListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//�õ�һ����д��SQLiteDatabase����
			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", "zhangsanfeng");
			//��һ��������Ҫ���µı���
			//�ڶ���������һ��ContentValeus����
			//������������where�Ӿ�
			db.update("user", values, "id=?", new String[]{"1"});
		}
    }
    class QueryListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			System.out.println("aaa------------------");
			Log.d("myDebug", "myFirstDebugMsg");
			
			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor cursor = db.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"}, null, null, null);
			while(cursor.moveToNext()){
				String name = cursor.getString(cursor.getColumnIndex("name"));
				System.out.println("query--->" + name);
			}
		}
    }
    
}