package linyxy.civitas.util;


/*����һ����mars��ʦ�̳��������������࣬
 * ��databaseHelper�����࣬���ڸ���SQLiteʹ�á�
 * ����ĵ������ݻ����޸ģ���δ���ơ�
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/*
 * Update Feb18
 * ��ʼ����SQL���ݿ��ţ� 
 * �����Ź������SQL��Ź���.txt��
 */

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static String dataBaseCivi = "civitasData"; //���ڴ�����ݿ������
	public static String DBtag = "databse";
	
	private static final int VERSION = 1;
	//��SQLiteOepnHelper�����൱�У������иù��캯��
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		//����ͨ��super���ø��൱�еĹ��캯��
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public DatabaseHelper(Context context,String name){
		this(context,name,VERSION);
	}
	public DatabaseHelper(Context context,String name,int version){
		this(context, name,null,version);
	}

	//�ú������ڵ�һ�δ������ݿ��ʱ��ִ��,ʵ�������ڵ�һ�εõ�SQLiteDatabse�����ʱ�򣬲Ż�����������
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("create a Database");
		Log.d(DBtag, "��ʼ�������ݿ�");
		//execSQL��������ִ��SQL���
		
		  db.execSQL("CREATE TABLE IF NOT EXISTS notifications (num integer primary key autoincrement, content TEXT, notificationType INTEGER)"); 
		  //��������notifications�ı��
		  db.execSQL("CREATE TABLE IF NOT EXISTS chats (name TEXT, content TEXT,num INTEGER, send INTEGER)");
		  //��������chats�ı��
		  db.execSQL("CREATE TABLE IF NOT EXISTS educations (skillName TEXT, skillLevel TEXT,comprehension TEXT, " +
		  		" breakingProp TEXT,description TEXT," +
		  		" subSkillName TEXT,subSkillPercentage TEXT)");
		  //��������educationExperience�ı��
		  
		//db.execSQL("create table user(id int,name varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a Database");
	}

}
