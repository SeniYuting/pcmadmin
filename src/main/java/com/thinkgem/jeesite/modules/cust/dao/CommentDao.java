/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.dao;

import com.google.gson.Gson;
import com.thinkgem.jeesite.modules.cust.entity.Comment;
import com.thinkgem.jeesite.modules.cust.entity.CommentList;
import com.thinkgem.jeesite.modules.cust.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户管理DAO接口
 * @author julia
 * @version 2016-12-15
 */
//@MyBatisDao
//public interface CommentDao extends CrudDao<Comment> {
//
//}
@Component
public class CommentDao {

    @Autowired
    private HttpUtil httpUtil;
    
    public List<Comment> getAll(){
        String res = httpUtil.getRequest(httpUtil.getCommentUrl());

        CommentList users = new Gson().fromJson(res, CommentList.class);
        return users.getComment();
    }


    public void delete(String id){
        httpUtil.deleteRequest(httpUtil.getCommentUrl()+id);
    }


    public List<Comment> getByStar(Integer star) {
        String res = httpUtil.getRequest(httpUtil.getCommentUrl()+"?Comment.star="+star);
        CommentList users = new Gson().fromJson(res, CommentList.class);
        return users.getComment();
    }
}