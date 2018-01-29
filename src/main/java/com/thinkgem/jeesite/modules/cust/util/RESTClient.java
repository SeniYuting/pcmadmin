package com.thinkgem.jeesite.modules.cust.util;

import com.thinkgem.jeesite.modules.cust.entity.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by julia on 12/16/16.
 */
@Component
public class RESTClient {

    private static String projectUrl = "http://112.74.49.183:8080/Entity/U31b61aa24b0ae/PCM/";

    public String getUserUrl(){
        return projectUrl+"User/";
    }

    @Autowired
    private RestTemplate template;

    public String getRequest(String url) {
//        return template.getForObject(url, String.class, new String[]{});

        return template.getForObject(url, String.class);
    }

//    public String getUserById(String id) {
//        return template.getForObject(url + "get/{id}.do", String.class, id);
//    }

    public String postRequest(String url,String user) {
        return template.postForObject( url , null, String.class, user);
    }

    public String putRequest(String url,String obj) {
        template.put(url , null, obj);
        return obj;
    }

    public String deleteRequest(String url,String id) {
        template.delete(url + "{id}", id);
        return id;
    }
}