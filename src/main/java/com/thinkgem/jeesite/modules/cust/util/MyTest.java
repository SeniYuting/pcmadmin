package com.thinkgem.jeesite.modules.cust.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * Created by julia on 12/16/16.
 */
public class MyTest {

    private static String projectUrl = "http://112.74.49.183:8080/Entity/U31b61aa24b0ae/PCM/User/";


    public static void main(String[] args){

        String user ="{\"id\":1481104288858,\"createdate\":\"2016-12-010 07:04:34\",\"account\":\"seni\",\"password\":\"seni\",\"name\":\"Cathy\",\"gender\":1,\"address\":\"上海\",\"mobile\":\"18787654\",\"lastlogindate\":\"2016-12-18 14:59:19\"}";
        HttpPut httpRequest = new HttpPut(projectUrl+"1481104288858");
        httpRequest.setHeader("Content-type",
                "application/json");
        try {
            StringEntity entity = new StringEntity(user, "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpRequest.setEntity(entity);
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    static void testRest(){
//        User user = new User();
//        user.setAccount("你说的");
//        user.setPassword("jj");
//        restClient
//    }
}
