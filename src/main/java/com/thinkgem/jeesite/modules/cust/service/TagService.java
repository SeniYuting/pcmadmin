/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cust.dao.CustDao;
import com.thinkgem.jeesite.modules.cust.dao.TagDao;
import com.thinkgem.jeesite.modules.cust.entity.Tag;
import com.thinkgem.jeesite.modules.cust.entity.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author julia
 * @version 2016-12-15
 */
@Service
public class TagService {

    @Autowired
    private TagDao tagDao;

//    public Tag get(String id) {
//        return tagDao.getById(id);
//    }

    public List<Tag> findList() {

        return tagDao.getAll();
    }

    public Page<Tag> findPage(Page<Tag> page, Tag tag) {
        tag.setPage(page);
        List<Tag> lists;
        if (tag.getTag_content() != null && !tag.getTag_content().equals("")) {
            lists = tagDao.getByContent(tag.getTag_content());
        } else {
            lists = tagDao.getAll();
        }
        if (lists != null) {
            page.setCount(lists.size());
            int first = (page.getPageNo() - 1) * page.getPageSize();
            int last = (first + page.getPageSize()) > lists.size() ? lists.size() : (first + page.getPageSize());

            page.setList(lists.subList(first, last));
        }
        return page;

    }
    //	@Transactional(readOnly = false)
    public void save(Tag tag) {
        if (tag.getId() != null){
            tagDao.update(tag);
        }
        else{
            tagDao.save(tag);
        }

    }

    //	@Transactional(readOnly = false)
    public void delete(Tag tag) {
        tagDao.delete(tag.getId());
    }

    public Tag getById(String id) {
        return tagDao.getById(id);
    }

    public List<UserTag> getUserByTagid(String id) {
        return tagDao.getUserByTagid(id);
    }
}