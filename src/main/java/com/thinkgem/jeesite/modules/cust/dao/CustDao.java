/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.dao;

import com.google.gson.Gson;
import com.thinkgem.jeesite.modules.cust.entity.Customer;
import com.thinkgem.jeesite.modules.cust.entity.UserList;
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
//@MyBatisDao
//public interface UserDao extends CrudDao<User> {
//
//}
@Component
public class CustDao {
    @Autowired
    private RESTClient restClient;

    @Autowired
    private HttpUtil httpUtil;

    public Customer getById(String id) {
        String res = restClient.getRequest(restClient.getUserUrl() + id);
        UserList users = new Gson().fromJson(res, UserList.class);
        if (users != null && users.getUser().size() > 0)
            return users.getUser().get(0);
        return null;
    }


    public List<Customer> getAll() {
        String res;
        res = httpUtil.getRequest(httpUtil.getUserUrl());
        UserList users = new Gson().fromJson(res, UserList.class);
        return users.getUser();
    }


    public void save(Customer user) {
        String obj = new Gson().toJson(user);
//        restClient.putRequest(restClient.getUserUrl(),obj);
        httpUtil.putRequest(httpUtil.getUserUrl() + user.getId(), obj);
    }

    public void delete(String id) {
//        restClient.deleteRequest(restClient.getUserUrl(),id);
        httpUtil.deleteRequest(httpUtil.getUserUrl() + id);
    }


    public List<Customer> getByName(String name) {
        String res = httpUtil.getRequest(httpUtil.getUserUrl() + "?User.name=(like)" + name);
        UserList users = new Gson().fromJson(res, UserList.class);
        return users.getUser();
    }

    public List<Customer> getByAccountAndName(String account, String name) {
        String res = httpUtil.getRequest(httpUtil.getUserUrl() + "?User.name=(like)" + name + "&User.account=(like)" + account);
        UserList users = new Gson().fromJson(res, UserList.class);
        return users.getUser();
    }

    public List<Customer> getByAccount(String account) {
        String res = httpUtil.getRequest(httpUtil.getUserUrl() + "?User.account=(like)" + account);
        UserList users = new Gson().fromJson(res, UserList.class);
        return users.getUser();
    }
}