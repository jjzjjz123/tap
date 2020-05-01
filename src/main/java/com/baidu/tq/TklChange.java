package com.baidu.tq;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class TklChange {
	
	public static final String COOKIE = PropertyConstants.getPropertiesKey("Cookie");
	
	public static String gettkl(int id)  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		 HttpGet httpGet = new HttpGet("http://www.xuandan.com/Home/GetLinkOne?id="+id+"&ysyl=undefined");
		 httpGet.setHeader("Cookie", COOKIE);  
		    
		CloseableHttpResponse response = null;
	    try {
	        //使用HttpClient发起请求
	        response = httpClient.execute(httpGet);

	        //判断响应状态码是否为200
	        if (response.getStatusLine().getStatusCode() == 200) {
	            //如果为200表示请求成功，获取返回数据
	            String content = EntityUtils.toString(response.getEntity(), "utf8");
	            //打印数据长度
	            System.out.println(content);
	        	return content;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        //释放连接
	        if (response == null) {
	            try {
	                response.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
					httpClient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
		return null;
	}
}
