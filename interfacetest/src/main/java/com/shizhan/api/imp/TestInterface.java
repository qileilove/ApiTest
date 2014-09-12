package com.shizhan.api.imp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.shizhan.api.tools.EnvSetting;
import com.shizhan.api.tools.LoggerControler;
import com.shizhan.api.tools.TestHttpTools;

/**
 * @author qilei
 * 
 */
public class TestInterface extends TestHttpTools {

	/**
	 * (non-Javadoc) 使用post方式提交带有参数的接口
	 * 
	 * @see TestHttpTools#HttpPost(java.lang.String, java.util.Map,
	 *      org.apache.http.client.HttpClient,
	 *      org.apache.http.client.CookieStore)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String HttpPost(String URL, Map parameterMap, HttpClient client,
			CookieStore cookies) throws Exception {
		// TODO 
		System.out.println("----testCookieStore");

		// 使用cookieStore方式
		client = HttpClients.custom().setDefaultCookieStore(cookies).build();
		//创建HttpClient实例
		HttpPost httppost = new HttpPost(URL);
		LoggerControler.getLogger(TestInterface.class).warn("URL地址:" + URL);

		UrlEncodedFormEntity postEntity1 = new UrlEncodedFormEntity(
				getParam(parameterMap), "UTF-8");
		httppost.setEntity(postEntity1);

		try {
			// 执行post请求
			HttpResponse httpResponse = client.execute(httppost);
			return	printResponse(httpResponse);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放连接。无论执行方法是否成功，都必须释放连接
			client.getConnectionManager().closeExpiredConnections();
		}
		return null;
	}

	/**
	 * (non-Javadoc) 使用get方法去请求不带参数单独的url接口
	 * @return 
	 * 创建某种连接方法的实例，在这里是GetMethod。在 GetMethod 的构造函数中传入待连接的地址
	 * @see TestHttpTools#HttpGet(java.lang.String,
	 *      org.apache.http.client.HttpClient,
	 *      org.apache.http.client.CookieStore)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String HttpGet(String URL, HttpClient client, CookieStore cookies)
			throws Exception {
		// TODO
		
		client = HttpClients.custom().setDefaultCookieStore(cookies).build();
		//Variable "client" 使用cookieStore方式 获得传过来的cookie 获取session
		//创建某种连接方法的实例，在这里是GetMethod。在 GetMethod 的构造函数中传入待连接的地址
		HttpGet httpGet = new HttpGet(URL);
		LoggerControler.getLogger(TestInterface.class).warn("URL地址:" + URL+";");

		try {
			//调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
			HttpResponse httpResponse = client.execute(httpGet);
		return	printResponse(httpResponse);

		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			httpGet.releaseConnection();
			client.getConnectionManager().closeExpiredConnections();

		}
		return null;
	}

	/**
	 * 获得登陆时的session 这样以后就不用登陆了
	 * 
	 * @see TestHttpTools#GetSession(org.apache.http.client.HttpClient)
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public CookieStore GetSession(HttpClient client) throws Exception {

		String URL =  EnvSetting.loginurl;
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("username", EnvSetting.username);
		parameterMap.put("password", EnvSetting.password);
		HttpPost httpPost = new HttpPost(URL);

		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(
				getParam(parameterMap), "UTF-8");
		httpPost.setEntity(postEntity);

		// 执行post请求
		HttpResponse httpResponse = client.execute(httpPost);
		// printResponse(httpResponse);
		// 释放掉登陆请求
		httpPost.releaseConnection();

		// cookie store
		return ((AbstractHttpClient) client).getCookieStore();

	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		TestInterface tt = new TestInterface();
		HttpClient client = new DefaultHttpClient();

		String URL = "http://192.168.60.233:82/service/check/search_tasks_list";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("taskName", "test");
		parameterMap.put("pageNo", "1");
		parameterMap.put("pageSize", "5");
		System.out.println(tt.GetSession(client));
		tt.HttpPost(URL, parameterMap, client, tt.GetSession(client));
		tt.HttpGet("http://192.168.60.233:82/maintenance/index/6", client,
				tt.GetSession(client));

	}

	/** (non-Javadoc)
	 * 测试登陆专用方法
	 * 
	 * @see com.shizhan.api.tools.TestHttpTools#HttpPost(java.lang.String, java.util.Map, org.apache.http.client.HttpClient)
	 */
	@Override
	public String HttpPost(String URL, Map parameterMap, HttpClient client)
			throws Exception {
		// TODO 
		HttpPost httppost = new HttpPost(URL);
		LoggerControler.getLogger(TestInterface.class).warn("URL地址:" + URL);

		UrlEncodedFormEntity postEntity1 = new UrlEncodedFormEntity(
				getParam(parameterMap), "UTF-8");
		httppost.setEntity(postEntity1);

		try {
			// 执行get请求
			HttpResponse httpResponse = client.execute(httppost);
			// System.out.println("cookie store:" + cookies.getCookies());

			return printResponse(httpResponse);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().closeExpiredConnections();
		}
		return null;
		
	}

}
