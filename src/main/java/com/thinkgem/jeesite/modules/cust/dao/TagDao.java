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
//@MyBatisDao
//public interface TagDao extends CrudDao<Tag> {
//
//}
@Component
public class TagDao {
    @Autowired
    private RESTClient restClient;

    @Autowired
    private HttpUtil httpUtil;


    public List<Tag> getAll() {
        String res;
        res = httpUtil.getRequest(httpUtil.getTagUrl());
        TagList tags = new Gson().fromJson(res, TagList.class);
        return tags.getTag();
    }


    public void save(Tag tag) {
        TagEntity tagTmp = new TagEntity(tag.getTag_content(),tag.getTag_description());
        String obj = new Gson().toJson(tagTmp);
        httpUtil.postRequest(httpUtil.getTagUrl(), obj);
    }

    public void delete(String id) {
        httpUtil.deleteRequest(httpUtil.getTagUrl() + id);
    }


    public List<Tag> getByContent(String name) {
        String res = httpUtil.getRequest(httpUtil.getTagUrl() + "?Tag.tag_content=(like)" + name);
        TagList tags = new Gson().fromJson(res, TagList.class);
        return tags.getTag();
    }


    public void update(Tag tag) {
        TagEntity tagTmp = new TagEntity(Long.parseLong(tag.getId()),tag.getTag_content(),tag.getTag_description());
        String obj = new Gson().toJson(tagTmp);
        httpUtil.putRequest(httpUtil.getTagUrl() + tag.getId(), obj);
    }

    public Tag getById(String id) {
        String res = httpUtil.getRequest(httpUtil.getTagUrl()+id);
        Tag tag = new Gson().fromJson(res, Tag.class);
        return tag;
    }

    public List<UserTag> getUserByTagid(String id) {
        String res = httpUtil.getRequest(httpUtil.getUserTagUrl()+"?User_tag.tag_id="+id);
        UserTagList lists =  new Gson().fromJson(res, UserTagList.class);
        return lists.getUser_tag();
    }
}