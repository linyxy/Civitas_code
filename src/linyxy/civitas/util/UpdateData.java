package linyxy.civitas.util;

import android.os.AsyncTask;
import android.util.Log;


/*
 * 这个一个异步的实现类。 
 * 创建时候传入不同的字符串数据
 * 用来进行不同的数据刷新
 * postExcute用以修改UI
 */
public class UpdateData extends AsyncTask<String, Void, String> {

	String async = "async task";
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.d(async, "doing something in an new thread");
		//DataRequestUtil DR = new DataRequestUtil();
		
		
		
//		if(params.equals("getStatus")) DR.getStatus();
//		if(params.equals("getChat")) DR.getChat();
		if(params[0].equals("UrlTest"))
		{
			System.out.println("try HTTPUTILX");
			try {
				String ace = HttpUtilX.getRequest(HttpUtilX.BASE_URL);
				Log.i(async, ace);
				System.out.println(ace);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return params[0];
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// 刷新UI进程
		super.onPostExecute(result);
		
		if(result.equals("getStatus"))
				System.out.println("refresh UI"	);
		
		
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
