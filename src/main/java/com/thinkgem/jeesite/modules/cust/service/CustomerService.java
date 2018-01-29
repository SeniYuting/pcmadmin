/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cust.dao.CustDao;
import com.thinkgem.jeesite.modules.cust.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户管理Service
 *
 * @author julia
 * @version 2016-12-15
 */
@Service
//@Transactional(readOnly = true)
//@Component
public class CustomerService {

    @Autowired
    private CustDao custDao;

    public Customer get(String id) {
        return custDao.getById(id);
    }

    public List<Customer> findList() {

        return custDao.getAll();
    }

    public Page<Customer> findPage(Page<Customer> page, Customer user) {
        user.setPage(page);
        List<Customer> lists;
        if (user.getAccount() != null && user.getName() != null && !user.getAccount().equals("") && !user.getName().equals("")) {
            lists = custDao.getByAccountAndName(user.getAccount(), user.getName());

//            page.setList(custDao.getByAccountAndName(user.getAccount(), user.getName()));
        } else if (user.getAccount() != null && !user.getAccount().equals("")) {
            lists =custDao.getByAccount(user.getAccount());

        } else if (user.getName() != null && !user.getName().equals("")) {
            lists =custDao.getByName(user.getName());

        } else {
            lists = custDao.getAll();
        }
        if (lists != null) {
            page.setCount(lists.size());
//        page.setP;
            int first = (page.getPageNo() - 1) * page.getPageSize();
            int last = (first + page.getPageSize()) > lists.size() ? lists.size() : (first + page.getPageSize());

            page.setList(lists.subList(first, last));
        }
        return page;

    }

    //	@Transactional(readOnly = false)
    public void save(Customer user) {
        custDao.save(user);
    }

    //	@Transactional(readOnly = false)
    public void delete(Customer user) {
        custDao.delete(user.getId());
    }

}