package linyxy.civitas.util;

import java.util.ArrayList;

import linyxy.civitas.FullscreenActivity;
import linyxy.civitas.model.Notification;
import structure.DialogUtil;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


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
			
		if(params[0].equals("ping"))
		{
			return DataRequestUtil.ping(ctx);
			
		}
		
		if(params[0].equals("login"))//��½
		{
			String loginResult ;
			Log.d(FullscreenActivity.Login,"logining in the background");
			loginResult = DataRequestUtil.basiclogin(ctx,params[1],params[2]);//ת���̨��֤��½����ȡ���
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
		if(params[0].equals("get_notifications"))
		{
			String result = DataRequestUtil.get_notifications_S(ctx);
			return result;
			
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

*/
//----------------------TEST-------------------------
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
				
		if(result.equals("pong"))//ping�ɹ� ������ͨ��
		{
			DialogUtil.showDialog(ctx, "������������",false);
		}
		else if(result.equals("loginTrue"))//��½�ɹ�
		{
			//�����̴߳�����Handler��������Ϣ
			
			Log.d("H", "Handler sending message");
			UIupdateHandler.sendEmptyMessage(0x1111);
			
		}
		else if(result.equals("notificationTrue"))
		{
			UIupdateHandler.sendEmptyMessage(0x1200);
			
		}
		else if(result.equals("newLetterTrue"))//��վ�ڳɹ�
		{
			
			UIupdateHandler.sendEmptyMessage(0x2468);
		}

		//������������
		else if(result.equals("badServer"))
		{
			DialogUtil.showDialog(ctx, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
		}
		else //ʣ��������message
		{
			DialogUtil.showDialog(ctx, result,false);
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
