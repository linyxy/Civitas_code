/**
 * 
 */
package linyxy.civitas.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;
/**
 * Description:
 * <br/>Copyright (C), 2012-2022, linyxy
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  linyxy
 * @version  1.5
 */
public class HttpUtilX
{
	public static final String HttpTag = "HTTP_TASK";
	
	// 创建HttpClient对象
	public static HttpClient httpClient = new DefaultHttpClient();
	public static final String BASE_URL = 
		"http://192.168.1.107:8080/nhjd'";//需要修改
	
	/**
	 * 
	 * @param url 发送请求的URL
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String getRequest(String url)
		throws Exception
	{
		Log.d(HttpTag, "starting a new HTTP request");
		// 创建HttpGet对象。
		HttpGet get = new HttpGet(url);
		// 发送GET请求
		HttpResponse httpResponse = httpClient.execute(get);
		Log.d(HttpTag, "connecting the server");
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			Log.d(HttpTag, "Server response successfully!");
			// 获取服务器响应字符串
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}

	/**
	 * 
	 * @param url 发送请求的URL
	 * @param params 请求参数
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String postRequest(String url
		, Map<String ,String> rawParams)throws Exception
	{
		Log.d(HttpTag, "starting a post request");
		
		System.out.println("size of raw Params"+rawParams.size());
		
		
		
		// 创建HttpPost对象。
		HttpPost post = new HttpPost(url);
		// 如果传递参数个数比较多的话可以对传递的参数进行封装
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : rawParams.keySet())
		{
			//封装请求参数
			Log.d(HttpTag, key);
			params.add(new BasicNameValuePair(key , rawParams.get(key)));
		}
		// 设置请求参数
		post.setEntity(new UrlEncodedFormEntity(
			params, "gbk"));
		// 发送POST请求
		
		Log.d(HttpTag, "encoded the params");
/*		HttpResponse httpResponse = httpClient.execute(post);
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			Log.d(HttpTag, "server responsed correctly");
			// 获取服务器响应字符串
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			
			return result;
		}
*/		return null;
	}
	
	public static void main(String[] args)
	{
		try {
			String abe = getRequest(BASE_URL);
			System.out.println(abe);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
