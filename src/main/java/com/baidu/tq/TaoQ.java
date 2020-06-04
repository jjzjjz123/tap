package com.baidu.tq;


import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.json.JSONObject;
@Component
public class TaoQ {

	@Scheduled(fixedDelay = 24*60*1000*60)
//	@Scheduled(cron= "0 00 6,12,18 * * ?") 
	public void sm()  {
		//Get接口调用        
        String url="http://api.xuandan.com/DataApi/Top100?AppKey=o059f3ilat&type=4";
        String responseInfo = RestTemplateUtil.get(url, null);
        JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(responseInfo);
		JsonArray array= object.get("data").getAsJsonArray();
		System.out.println(array.size()+"长度");
		System.out.println(array.size()+"长度");
		System.out.println(array.size()+"长度");
		for (int i = 1; i <56; i++) {
			JsonObject subObject=array.get(i).getAsJsonObject();
            String id = subObject.get("ID").getAsString();
            String goodsname =subObject.get("GoodsName").getAsString();
            String imgurl = subObject.get("ImgUrl").getAsString();
            String actmoney = subObject.get("ActMoney").getAsString();
            String lastprice = subObject.get("LastPrice").getAsString();
            String tjremark = subObject.get("TjRemark").getAsString();
            String gettkl = null;
			try {
				gettkl = TklChange.gettkl( Integer.parseInt(id));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
    		JsonObject object1 = (JsonObject) parser.parse(gettkl);
    		if (object1.get("error").getAsString().equals("0")){
	    		String tkl = object1.get("tkl").getAsString();
	            try {
					send(imgurl, goodsname, actmoney, lastprice, tkl, tjremark);
					Thread.sleep(5*1000*60);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    		
    		
            
		}
	}
	
	
	public static  void send(String imgurl,String goodsname,String actmoney,String lastprice,String tkl,String tjremark) {
		
		String qqgroup[]= {"182679277","309955795","513272653","480021275","555973050","571789829","572668595","200073308","390602060","887292111"};
		for (String str: qqgroup) {
			try {
				Thread.sleep(3600);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://127.0.0.1:5700//send_group_msg");
		    JSONObject jsonParam = new JSONObject();  
		    jsonParam.put("group_id", str);
		    jsonParam.put("message", "[CQ:image,file="+imgurl+"]\n"+goodsname+"\n[优惠券："+actmoney+"元]\n[券后"+lastprice+"元包邮]\n\n"+tjremark+"\n\n淘口令→{"+tkl+"}\n[复制口令到淘宝若没反应]\n[请重新复制再打开！]\n更多： http://t.cn/A6ZVV9A8");
		    StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题  
		    entity.setContentEncoding("UTF-8");    
		    entity.setContentType("application/json");
		    httpPost.setEntity(entity);
		    CloseableHttpResponse response = null;
		    try {
		        //使用HttpClient发起请求
		        response = httpClient.execute(httpPost);

		        //判断响应状态码是否为200
		        if (response.getStatusLine().getStatusCode() == 200) {
		            //如果为200表示请求成功，获取返回数据
		            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
		            //打印数据长度
		            System.out.println(content);
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
		}
		
	}
}
