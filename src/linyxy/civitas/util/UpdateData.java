package linyxy.civitas.util;

import android.os.AsyncTask;
import android.util.Log;


/*
 * ���һ���첽��ʵ���ࡣ 
 * ����ʱ���벻ͬ���ַ�������
 * �������в�ͬ������ˢ��
 * postExcute�����޸�UI
 */
public class UpdateData extends AsyncTask<String, Void, String> {

	String async = "async task";
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		DataRequestUtil DR = new DataRequestUtil();
		
		if(params.equals("getStatus")) DR.getStatus();
		if(params.equals("getChat")) DR.getChat();
		
		return params[0];
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// ˢ��UI����
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
