package com.thinkgem.jeesite.modules.cust.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Component
public class HttpUtil {

    private static String projectUrl = "http://112.74.49.183:8080/Entity/U31b61aa24b0ae/PCM/";

    public String getUserUrl(){
        return projectUrl+"User/";
    }

    public String getCommentUrl(){
        return projectUrl+"Comment/";
    }


    public String getTagUrl() {
        return projectUrl+"Tag/";
    }

    public String getUserTagUrl() {
        return projectUrl+"User_tag/";
    }

    public String getCardUrl() {
        return projectUrl+"User_tag/";
    }
    public String getCardExchangeUrl() {
        return projectUrl+"Card_exchange/";
    }

    /**
     * 执行request请求
     *
     * @param url 请求的url地址
     * @return 返回请求的结果
     */
    public String getRequest(String url) {

        HttpGet httpRequest = new HttpGet(url);
        String resp = "";
        try {
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);

            InputStream inputStream = httpResponse.getEntity()
                    .getContent();
            if (inputStream != null)
                resp = convertInputStreamToString(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
//        return result_array.toString();
    }

    /**
     * 执行post请求
     *
     * @param url 请求的url地址
     * @return 返回请求的结果
     */
    public void postRequest(String url, JSONObject obj) {
        String uriAPI = url;
        HttpPost httpRequest = new HttpPost(uriAPI);
        httpRequest.setHeader("Content-type",
                "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            StringEntity entity = new StringEntity(obj.toString(), "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpRequest.setEntity(entity);
            HttpResponse res = httpClient.execute(httpRequest);
            ;
			String responseContent = null; // 响应内容
			HttpEntity httpEntity = res.getEntity();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
//			return httpResponse.toString();
        }
//    return resultStr;
    }


    /**
     * 执行post请求
     *
     * @param url 请求的url地址
     * @return 返回请求的结果
     */
    public void putRequest(String url, JSONObject obj) {
        String uriAPI = url;
        HttpPut httpRequest = new HttpPut(uriAPI);
        httpRequest.setHeader("Content-type",
                "application/json");

        try {
            StringEntity entity = new StringEntity(obj.toString(), "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpRequest.setEntity(entity);
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public  void postRequest(String url, String obj) {
        String uriAPI = url;
        HttpPost httpRequest = new HttpPost(uriAPI);
        httpRequest.setHeader("Content-type",
                "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            StringEntity entity = new StringEntity(obj, "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpRequest.setEntity(entity);
            HttpResponse res = httpClient.execute(httpRequest);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public void putRequest(String url, String obj) {
        String uriAPI = url;
        HttpPut httpRequest = new HttpPut(uriAPI);
        httpRequest.setHeader("Content-type",
                "application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            StringEntity entity = new StringEntity(obj, "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpRequest.setEntity(entity);
            HttpResponse res = httpClient.execute(httpRequest);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }



    public void deleteRequest(String url) {
        HttpDelete httpRequest = new HttpDelete(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            HttpResponse res = httpClient.execute(httpRequest);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }


    private static String convertInputStreamToString(InputStream inputStream)
            throws Exception {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

}
