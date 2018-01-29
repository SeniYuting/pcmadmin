/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.dao;

import com.google.gson.Gson;
import com.thinkgem.jeesite.modules.cust.entity.*;
import com.thinkgem.jeesite.modules.cust.util.HttpUtil;
import com.thinkgem.jeesite.modules.cust.util.RESTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户管理DAO接口
 *
 * @author julia
 * @version 2016-12-15
 */
@Component
public class CardDao {

    @Autowired
    private HttpUtil httpUtil;


    public List<CardExchange> getAllCardExchage() {
        String res;
        res = httpUtil.getRequest(httpUtil.getCardExchangeUrl());
        CardExchangeList tags = new Gson().fromJson(res, CardExchangeList.class);
        return tags.getCard_exchange();
    }




    public void delete(String id) {
        httpUtil.deleteRequest(httpUtil.getCardExchangeUrl() + id);
    }


//    public List<CardExchange> getByContent(String name) {
//        String res = httpUtil.getRequest(httpUtil.getCardExchangeUrl() + "?CardExchange.tag_content=(like)" + name);
//        CardExchangeList tags = new Gson().fromJson(res, CardExchangeList.class);
//        return tags.getCardExchange();
//    }



    public CardExchange getById(String id) {
        String res = httpUtil.getRequest(httpUtil.getCardExchangeUrl()+id);
        CardExchange tag = new Gson().fromJson(res, CardExchange.class);
        return tag;
    }

}