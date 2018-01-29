/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cust.dao.CommentDao;
import com.thinkgem.jeesite.modules.cust.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户管理Service
 * @author julia
 * @version 2016-12-15
 */
@Service
@Transactional(readOnly = true)
public class CommentService {

	@Autowired
	private CommentDao custDao;
	
	public List<Comment> findList() {

		return custDao.getAll();
	}
	
	public Page<Comment> findPage(Page<Comment> page, Comment comment) {
		comment.setPage(page);
		List<Comment> lists;
		if (comment.getStar()!= null){
			lists = custDao.getByStar(comment.getStar());
		}
		else{
			lists = custDao.getAll();
		}
		if (lists != null) {
			page.setCount(lists.size());
			int first = (page.getPageNo() - 1) * page.getPageSize();
			int last = (first + page.getPageSize()) > lists.size() ? lists.size() : (first + page.getPageSize());
			page.setList(lists.subList(first, last));
		}
		return page;

	}

	@Transactional(readOnly = false)
	public void delete(Comment comment) {
		custDao.delete(comment.getId());
	}
	
}