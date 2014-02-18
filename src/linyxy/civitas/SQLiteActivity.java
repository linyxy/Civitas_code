package linyxy.civitas;

import java.util.List;

import linyxy.civitas.util.DatabaseHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * 这是一个从mars老师教程里面拉出来的类，
 * 是databaseAcitivity的用于演示的类，
 * 用于展示SQLite使用。
 * 这个文档中内容用来指使SQLite使用代码而非真实可用代码。
 */


public class SQLiteActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,DatabaseHelper.dataBaseCivi);

    }
    
    
    public void refreshData (List<Object> models){
    	//创建一个DatabaseHelper对象
		DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,DatabaseHelper.dataBaseCivi);
		//只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		//下面将更具传入的model种类更新数据库
    }
    
    

    class InsertListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			//生成ContentValues对象
			ContentValues values = new ContentValues();
			//想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
			values.put("id", 1);
			values.put("name","zhangsan");
			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db",2);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//调用insert方法，就可以将数据插入到数据库当中
			db.insert("user", null, values);
		}
    }
    //更新操作就相当于执行SQL语句当中的update语句
    //UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
    class UpdateRecordListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//得到一个可写的SQLiteDatabase对象
			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", "zhangsanfeng");
			//第一个参数是要更新的表名
			//第二个参数是一个ContentValeus对象
			//第三个参数是where子句
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