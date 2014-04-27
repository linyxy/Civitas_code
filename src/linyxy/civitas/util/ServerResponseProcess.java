package linyxy.civitas.util;

import linyxy.civitas.FullscreenActivity;
import structure.DialogUtil;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
 * 用来处理服务器返回情况的类
 * 于asyncHttpClient后开始使用
 * 
 */
public class ServerResponseProcess {
	
	private static final String async = "async";
	public  static String proBadResponse(Context ctx,String result)
	{
		if(result.equals("badServer"))
		{
			DialogUtil.showDialog(ctx, "服(wo)务(ye)器(bu)响(zhi)应(dao)异(zen me)常(le)！", false);
		}
		//token error
		else if(result.contains("badToken"))
		{
			DialogUtil.showDialog(ctx, result, false);
			Intent i = new Intent();
			i.setClass(ctx, FullscreenActivity.class);
			ctx.startActivity(i);
			ctx.fileList();
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
		return result;
	}
}
