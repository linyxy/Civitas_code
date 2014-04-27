package linyxy.civitas.util;

import linyxy.civitas.FullscreenActivity;
import structure.DialogUtil;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
 * ������������������������
 * ��asyncHttpClient��ʼʹ��
 * 
 */
public class ServerResponseProcess {
	
	private static final String async = "async";
	public  static String proBadResponse(Context ctx,String result)
	{
		if(result.equals("badServer"))
		{
			DialogUtil.showDialog(ctx, "��(wo)��(ye)��(bu)��(zhi)Ӧ(dao)��(zen me)��(le)��", false);
		}
		//token error
		else if(result.contains("badToken"))
		{
			DialogUtil.showDialog(ctx, result, false);
			Intent i = new Intent();
			i.setClass(ctx, FullscreenActivity.class);
			ctx.startActivity(i);
			ctx.fileList();
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
		return result;
	}
}
