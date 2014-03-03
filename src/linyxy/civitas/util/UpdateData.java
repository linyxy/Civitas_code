package linyxy.civitas.util;

import java.util.HashMap;
import java.util.Map;

import projectTesting.HttpUtilX;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
	
	public UpdateData(Context ctx) {
		super();
		this.ctx = ctx;
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
	
			
			Log.d(async, "DataRequestUtil created");
			
		if(params[0].equals("SharedPTest"))
		{
			DataRequestUtil.sharePTest(ctx);
		}
			
		if(params[0].equals("UrlTest"))
		{
			System.out.println("try HTTPUTILX");
			try {
				String urlPattern = HttpUtilX.BASE_URL+"/lgoin.jsp" ;
				HashMap<String, String> content = new HashMap<String,String>();
				content.put("userName", "linyxy");
				content.put("password", "1992");
				Log.d(async, "feng zhuang");
				String result = HttpUtilX.postRequest(urlPattern, content);
				if(result != null)
					Log.d(async, result);
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
