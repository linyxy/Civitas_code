package linyxy.civitas.util;

import java.util.ArrayList;

import linyxy.civitas.FullscreenActivity;
import linyxy.civitas.model.Notification;
import structure.DialogUtil;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
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

		Log.d(async, "doing something in an new thread");
		


		
		System.out.println("SharedTest");
		Log.d(async, "creating DataRequestUtil activity");
			
		if(params[0].equals("ping"))
		{
			return DataRequest.ping(ctx);
			
		}
		
		else if(params[0].equals("login"))//��½
		{
			String loginResult ;
			Log.d(FullscreenActivity.Login,"logining in the background");
			loginResult = DataRequest.basiclogin(ctx,params[1],params[2]);//ת���̨��֤��½����ȡ���
			System.out.println(loginResult);
			return loginResult;//���ͽ������UI
		}
		

		else if(params[0].equals("letter"))
		{
			String newLetterResult;
			Log.d("letter", "sending a new letter");
			newLetterResult = DataRequestUtil.sendNewLetter(ctx, params[1], params[2]);
			return newLetterResult;
		}
		else if(params[0].equals("get_notifications"))
		{
			String result = DataRequest.get_notifications_S(ctx);
			return result;
			
		}
		else if(params[0].equals("getMyStatus"))
		{
			String result = DataRequest.getMyStatus_S(ctx);
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
		else if(result.equals("myStatusTrue"))
		{
			Log.d(async, result);
			DialogUtil.showDialog(ctx, result, false);
		}

		//������������
		else if(result.equals("badServer"))
		{
			DialogUtil.showDialog(ctx, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
		}
		//token error
		else if(result.contains("badToken"))
		{
			DialogUtil.showDialog(ctx, result, false);
			//��Ҫlogout
		}
		else if(result.contains("badAct"))
		{
			//��Ϊʧ�ܡ���������Ӧ
			Log.d(async, result);
			DialogUtil.showDialog(ctx, result, false);
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
