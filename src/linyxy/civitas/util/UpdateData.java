package linyxy.civitas.util;

import linyxy.civitas.FullscreenActivity;
import linyxy.fragment.My2Fragment;
import structure.DialogUtil;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


/*
 * ���һ���첽��ʵ���ࡣ 
 * ����ʱ���벻ͬ���ַ�������
 * �������в�ͬ������ˢ��
 * postExcute�����޸�UI
 */
public class UpdateData extends AsyncTask<String, Void, String> {

	public final String async = "async task";
	private Context ctx;
			Handler UIupdateHandler;
	
	public UpdateData(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	public UpdateData(Context ctx,Handler h) {
		super();
		this.ctx = ctx;
		this.UIupdateHandler = h;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.d(async, "doing something in an new thread");
		
		

		System.out.println("SharedTest");
		Log.d(async, "creating DataRequestUtil activity");
			
			
		if(params[0].equals("login"))//��½
		{
			String loginResult ;
			Log.d(FullscreenActivity.Login,"logining in the background");
			loginResult = DataRequestUtil.login(ctx,params[1],params[2]);//ת���̨��֤��½����ȡ���
			System.out.println(loginResult);
			return loginResult;//���ͽ������UI
		}
		

		if(params[0].equals("letter"))
		{
			String newLetterResult;
			Log.d("letter", "sending a new letter");
			newLetterResult = DataRequestUtil.sendNewLetter(ctx, params[1], params[2]);
			return newLetterResult;
		}

//----------------------TEST------------------------	
		if(params[0].equals("handlerTest"))
		{
			Message msg = new Message();
			msg.what = 0x1111;
			//�����̴߳�����Handler��������Ϣ
			
			Log.d("H", "Handler sending message");
			UIupdateHandler.sendMessage(msg);
		}
/*			
		if(params[0].equals("SQLiteTest"))
		{
			Log.d(async, "DataRequestUtil created");
			DataRequestUtil.SQLiteTest(ctx);
		}
			
		if(params[0].equals("SharedPTest"))
		{
			DataRequestUtil.sharePTest(ctx);
		}
			
		if(params[0].equals("UrlTest"))
		{
			System.out.println("try HTTPUTILX");
			try {
				String urlPattern = HttpUtilX.BASE_URL+"/login.jsp" ;
				//HttpUtilX.getRequest(HttpUtilX.BASE_URL);
				HashMap<String, String> content = new HashMap<String,String>();
				content.put("userName", "linyxy.art@gmail.com");
				content.put("password", "xx19970305");
				Log.d(async, "feng zhuang");
				String result = HttpUtilX.postRequest(urlPattern, content);
				if(result != null)
					Log.d(async, result);
			} catch (Exception e) {
				// 
				e.printStackTrace();
			}
		}
*/
		
		return params[0];
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		
//		if(result.equals("getStatus"))
//				System.out.println("refresh UI"	);
		Log.d(async, "on Post Execute");
		
		//��½�ɹ�
		if(result.equals("loginTrue"))
		{
			Message msg = new Message();
			msg.what = 0x1111;
			//�����̴߳�����Handler��������Ϣ
			
			Log.d("H", "Handler sending message");
			UIupdateHandler.sendMessage(msg);
			/*
			Log.d(async, "tring to start my_second activity");
			Intent startMySecond = new Intent();
			startMySecond.setClass(ctx, My2Fragment.class);
			Log.d(async, "building up the intent");
			ctx.startActivity(startMySecond);
			 */
			
		}
		//��½ʧ��
		if(result.equals("loginFalse"))
		{
			DialogUtil.showDialog(ctx, "����ʺ�����д����", false);
		}
		
		//��վ�ڳɹ�
		if(result.equals("newLetterTrue"))
		{
			
			UIupdateHandler.sendEmptyMessage(0x2468);
		}
		//��վ��ʧ��
		if(result.equals("newLetterFalse"))
		{
			Toast.makeText(ctx,"վ���ŷ���ʧ��", Toast.LENGTH_SHORT).show();
			
		}

		
		//������������
		if(result.equals("badServer"))
		{
			DialogUtil.showDialog(ctx, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
		}
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		Log.d(async, "an async task started!");
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
