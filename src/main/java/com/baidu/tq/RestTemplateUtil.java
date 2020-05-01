package com.baidu.tq;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * *接口调用工具类
 * @author clove
 * @create: 2020-01-15
 */

public class RestTemplateUtil {
 
    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplate();
    }
 
    private RestTemplateUtil() {
     
    }
 
    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }
    
    /**
     * * GET请求
     * @param url 请求路径
     * @param jwt所需的Token不需要可传null
     * @return
     */
    public static String get(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("token", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = RestTemplateUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, String.class);
        String responseInfo = response.getBody();
        return responseInfo;
    }
 
    /**
     * * POST请求
     * @param url 请求路径
     * @param data body数据
     * @param token jwt所需的Token不需要可传null
     * @return
     */
    public static String post(String url, String data, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        return RestTemplateUtil.getInstance().postForObject(url, requestEntity, String.class);
    }
 
}