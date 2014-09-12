package com.shizhan.api.tools;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.shizhan.api.imp.TestInterface;

/**
 * @author qilei
 * 
 */
public abstract class TestHttpTools {

	/**
	 * @param URL
	 * @param parameterMap
	 * @param client
	 * @param cookies
	 * @return 
	 * @throws Exception
	 * 带参数的接口
	 */
	public abstract String HttpPost(String URL, Map parameterMap,
			HttpClient client, CookieStore cookies) throws Exception;
	/**
	 * @param URL
	 * @param parameterMap
	 * @param client
	 * @return 
	 * @throws Exception
	 * 测试登陆的方法
	 */
	public abstract String HttpPost(String URL, Map parameterMap,
			HttpClient client) throws Exception;
	/**
	 * @param client
	 * @return
	 * @throws Exception
	 * 返回登陆时的session值
	 */
	public abstract CookieStore GetSession(HttpClient client)
			throws Exception;

	/**
	 * @param URL
	 * @param client
	 * @param cookies
	 * @return 
	 * @throws Exception
	 * 不带参数的接口
	 */
	public abstract String HttpGet(String URL, HttpClient client, CookieStore cookies)
			throws Exception;
	/**
	 * @param httpResponse
	 * @throws ParseException
	 * @throws IOException
	 * 打印的响应信息
	 * 读 response
	 */
	public String printResponse(HttpResponse httpResponse) throws ParseException,
			IOException {
		String responsemessage = null;
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		//System.out.println("status:" + httpResponse.getStatusLine());
		/*System.out.println("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}*/
		// 判断响应实体是否为空
		if (entity != null) {
			 System.err.println("内容编码: " + entity.getContentEncoding());
             System.err.println("响应状态: " + httpResponse.getStatusLine());
             System.err.println("响应长度: " + entity.getContentLength());
             //6. 对得到后的内容进行处理
             responsemessage=EntityUtils.toString(entity,"UTF-8");
             //System.err.println("响应内容: \r\n" + EntityUtils.toString(entity, "UTF-8"));//得到返回数据的长度  
//LoggerControler.getLogger(TestHttpTools.class).warn("响应内容: \r\n"+EntityUtils.toString(entity,"UTF-8"));

		}
		LoggerControler.getLogger(TestInterface.class).warn("响应内容: \r\n"+responsemessage);
		return responsemessage;

	}

	/**将map转换为bean
	 * @param parameterMap
	 * @return
	 */
	public List<NameValuePair> getParam(Map<?, ?> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<?> it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<?, ?> parmEntry = (Entry<?, ?>) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(),
					(String) parmEntry.getValue()));
		}
		return param;
	}

}
