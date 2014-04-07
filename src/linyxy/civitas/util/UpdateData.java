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
 * 这个一个异步的实现类。 
 * 创建时候传入不同的字符串数据
 * 用来进行不同的数据刷新
 * postExcute用以修改UI
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
		
		else if(params[0].equals("login"))//登陆
		{
			String loginResult ;
			Log.d(FullscreenActivity.Login,"logining in the background");
			loginResult = DataRequest.basiclogin(ctx,params[1],params[2]);//转入后台验证登陆，获取结果
			System.out.println(loginResult);
			return loginResult;//发送结果更新UI
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
			//从主线程带借用Handler来发送消息
			
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
				
		if(result.equals("pong"))//ping成功 服务器通畅
		{
			DialogUtil.showDialog(ctx, "服务器可连接",false);
		}
		else if(result.equals("loginTrue"))//登陆成功
		{
			//从主线程带借用Handler来发送消息
			
			Log.d("H", "Handler sending message");
			UIupdateHandler.sendEmptyMessage(0x1111);
			
		}
		else if(result.equals("notificationTrue"))
		{
			UIupdateHandler.sendEmptyMessage(0x1200);
			
		}
		else if(result.equals("newLetterTrue"))//新站内成功
		{
			
			UIupdateHandler.sendEmptyMessage(0x2468);
		}
		else if(result.equals("myStatusTrue"))
		{
			Log.d(async, result);
			DialogUtil.showDialog(ctx, result, false);
		}

		//服务器无连接
		else if(result.equals("badServer"))
		{
			DialogUtil.showDialog(ctx, "服(wo)务(ye)器(bu)响(zhi)应(dao)异(zen me)常(le)！", false);
		}
		//token error
		else if(result.contains("badToken"))
		{
			DialogUtil.showDialog(ctx, result, false);
			//需要logout
		}
		else if(result.contains("badAct"))
		{
			//行为失败。无其他响应
			Log.d(async, result);
			DialogUtil.showDialog(ctx, result, false);
		}
		
		else //剩余情况输出message
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
