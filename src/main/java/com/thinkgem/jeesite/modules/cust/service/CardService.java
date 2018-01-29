/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cust.dao.CardDao;
import com.thinkgem.jeesite.modules.cust.entity.CardExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author julia
 * @version 2016-12-15
 */
@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

//    public CardExchange get(String id) {
//        return cardDao.getById(id);
//    }

    public List<CardExchange> findCardExchangeList() {

        return cardDao.getAllCardExchage();
    }



    //	@Transactional(readOnly = false)
//    public void delete(CardExchange tag) {
//        cardDao.delete(tag.getId());
//    }

//    public CardExchange getById(String id) {
//        return cardDao.getById(id);
//    }

//    public List<CardExchange> getUserByCardExchangeid(String id) {
//        return cardDao.getUserByCardExchangeid(id);
//    }
}