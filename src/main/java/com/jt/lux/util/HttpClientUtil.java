package com.jt.lux.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * HTTP请求相关工具类
 * 
 * @author dongj
 */
public class HttpClientUtil
{
	
	private final static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
	/**
	 * 发送 get请求
	 */
	@SuppressWarnings("rawtypes")
	public static String doHttpGet(Map paramMap)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(paramMap.get("url"));
		
		if(paramMap.get("param") != null)
		{
			String param = paramMap.get("param").toString();
			
			sb.append("?");
			
			if(paramMap.get("isUrlEncoded") != null && Integer.parseInt(paramMap.get("isUrlEncoded").toString()) == 1)
			{
//				sb.append(parameterizeForGet(param));
			}
			else
			{
				sb.append(param);
			}
		}
		
		String url = sb.toString();
//		"Content-Type: text/plain; charset=utf-8[\r][\n]"
//		Content-Type application/x-www-form-urlencoded
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try
		{
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
//			System.out.println(url);
			log.info("executing  request get:" + httpget.getURI());
			
//			httpget.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try
			{
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				log.info("--------------------------------------");
				log.info("响应状态:"+response.getStatusLine());
				if(entity != null)
				{
					String entityString = EntityUtils.toString(entity, "UTF-8").trim();
					log.info("响应内容:"+entityString);
					httpget.releaseConnection();
					
					return entityString;
				}
			}
			finally
			{
				response.close();
			}
		}
		catch(ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭连接,释放资源
			try
			{
				httpclient.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 发送 post请求
	 */
	@SuppressWarnings({"rawtypes"})
	public static Map doHttpPost(Map paramMap)
	{
		String   url = paramMap.get("url").toString();
		String param = paramMap.get("param").toString();
		
		
		if(param == null)
		{
			return null;
		}
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(url);
		log.info("executing  request post:" + httpPost.getURI());
		httpPost.setHeader("Content-Type", "application/json");
		try
		{
			param = URLEncoder.encode(param, "UTF-8");
			StringEntity se = new StringEntity(param);
			se.setContentType("text/json");
			httpPost.setEntity(se);
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try
			{
				HttpEntity entity = response.getEntity();
				if(entity != null)
				{
					log.info("--------------------------------------");
					log.info("响应状态:"+response.getStatusLine());
					String entityString = EntityUtils.toString(entity, "UTF-8");
					log.info("响应内容:" + entityString);
//					System.out.println(entityString);
					Map<String, Object> resultMap = JSONObject.parseObject(entityString);
					return resultMap;
				}
			}
			finally
			{
				response.close();
			}
		}
		catch(ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭连接,释放资源
			try
			{
				httpClient.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	/**
	 * 发送回调请求并返回回调响应
	 * @param url 回调url
	 * @param json  json格式报文字符串
	 * @return	回调响应的报文字符串
	 */
	public static String callPost(String url, String json) {
		URL u = null;
		HttpURLConnection con = null;
		String responseStr = "";
		UUID uuid = UUID.randomUUID();
		String tradeno = uuid.toString().replaceAll("-", "");
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setConnectTimeout(30000);
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(json);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return  "{\"resp_head\":{\"radeno\":\""+tradeno+"\",\"retcode\":\"0\",\"subcode\":\"9001\",\"message\":\"连接异常\"}}";
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取相应内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  "{\"resp_head\":{\"radeno\":\""+tradeno+"\",\"retcode\":\"0\",\"subcode\":\"9002\",\"message\":\"读取内容异常\"}}";
		}
		if ((responseStr=buffer.toString())!= null) {			
		}
		System.out.println(responseStr);
		return responseStr;
	}

	/**
	 * 发送回调请求并返回回调响应
	 * @param url 回调url
	 * @param json  json格式报文字符串
	 * @return	回调响应的报文字符串
	 */
	public static String callPost2(String url, String json,String headerName,String headerValue) {
		URL u = null;
		HttpURLConnection con = null;
		String responseStr = "";
		UUID uuid = UUID.randomUUID();
		String tradeno = uuid.toString().replaceAll("-", "");
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setConnectTimeout(30000);
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			if(headerName != null && !"".equals(headerName) && headerValue != null && !"".equals(headerValue)) {
				con.addRequestProperty(headerName, headerValue);
			}
			con.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(json);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return  "{\"resp_head\":{\"radeno\":\""+tradeno+"\",\"retcode\":\"0\",\"subcode\":\"9001\",\"message\":\"连接异常\"}}";
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取相应内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  "{\"resp_head\":{\"radeno\":\""+tradeno+"\",\"retcode\":\"0\",\"subcode\":\"9002\",\"message\":\"读取内容异常\"}}";
		}
		if ((responseStr=buffer.toString())!= null) {
		}
		System.out.println(responseStr);
		return responseStr;
	}

	/**
	 * 发送 post请求
	 */
	@SuppressWarnings({"rawtypes"})
	public static CloseableHttpResponse doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			//resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return response;
	}

	public static String doPostJson(String url, String json,String headerName,String headerValue) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);

			if(headerName != null && !"".equals(headerName) && headerValue != null && !"".equals(headerValue)){
				httpPost.setHeader(headerName,headerValue);
			}

			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static void main(String[] args) {


		Map paramMap =new HashMap<>();
		paramMap.put("url", "http://5211-test.jiangtai.com/_gw/user-svc//api/v2/kaptcha");
		paramMap.put("param", "comcode=1");
		String resMsg = HttpClientUtil.doHttpGet(paramMap);
		System.out.println(resMsg);
	}
}