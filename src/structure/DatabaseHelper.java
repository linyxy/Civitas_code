package structure;


/*这是一个从mars老师教程里面拉出来的类，
 * 是databaseHelper的子类，用于辅助SQLite使用。
 * 这个文档中内容还需修改，并未完善。
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/*
 * Update Feb18
 * 开始进行SQL数据库存放， 
 * 具体存放规则见“SQL存放规则.txt”
 */

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static String dataBaseCivi = "civitasData"; //用于存放数据库的名字
	public static String DBtag = "database";
	
	private static final int VERSION = 1;
	//在SQLiteOepnHelper的子类当中，必须有该构造函数
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		//必须通过super调用父类当中的构造函数
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public DatabaseHelper(Context context,String name){
		this(context,name,VERSION);
	}
	public DatabaseHelper(Context context,String name,int version){
		this(context, name,null,version);
	}

	//该函数是在第一次创建数据库的时候执行,实际上是在第一次得到SQLiteDatabse对象的时候，才会调用这个方法
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("create a Database");
		Log.d(DBtag, "开始创建数据库&表格");
		//execSQL函数用于执行SQL语句
		
		  db.execSQL("CREATE TABLE IF NOT EXISTS notifications (content TEXT,id TEXT,PRIMARY KEY (id))"); 
		  //创建关于notifications的表格
		  db.execSQL("CREATE TABLE IF NOT EXISTS chats (name TEXT, content TEXT,num INTEGER, send INTEGER)");
		  //创建关于chats的表格
		  db.execSQL("CREATE TABLE IF NOT EXISTS educations (skillName TEXT, skillLevel TEXT,comprehension TEXT, " +
		  		" breakingProp TEXT,description TEXT," +
		  		" subSkillName TEXT,subSkillPercentage TEXT)");
		  //创建关于educationExperience的表格
		  Log.d(DBtag, "完成了table创建");
		 if( db.isOpen()) Log.d(DBtag, "db running on");
		 
		//db.execSQL("create table user(id int,name varchar(20))");
		 if( db.isOpen()) Log.d(DBtag, "db running on2222");
		  Log.d(DBtag,"is db close");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a Database");
	}

}
