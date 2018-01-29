/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cust.entity.Customer;
import com.thinkgem.jeesite.modules.cust.service.CustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 客户管理Controller
 *
 * @author julia
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cust/user")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService custService;

    @ModelAttribute
    public Customer get(@RequestParam(required = false) String id) {
        Customer entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = custService.get(id);
//		}
        if (entity == null) {
            entity = new Customer();
        }
        return entity;
    }

    @RequiresPermissions("cust:user:view")
    @RequestMapping(value = {"list", ""})
    public String list(Customer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Customer> page = custService.findPage(new Page<Customer>(request, response), customer);
        model.addAttribute("page", page);

        return "modules/cust/userList";
    }

    @RequiresPermissions("cust:user:view")
    @RequestMapping(value = "form")
    public String form(Customer user, Model model) {
        model.addAttribute("customer", user);
        return "modules/cust/userForm";
    }

    @RequiresPermissions("cust:user:edit")
    @RequestMapping(value = "save")
    public String save(Customer user, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, user)) {
            return form(user, model);
        }
        custService.save(user);
        addMessage(redirectAttributes, "保存User成功");
        return "redirect:" + Global.getAdminPath() + "/cust/user/?repage";
    }

    @RequiresPermissions("cust:user:edit")
    @RequestMapping(value = "delete")
    public String delete(Customer user, RedirectAttributes redirectAttributes) {
        custService.delete(user);
        addMessage(redirectAttributes, "删除User成功");
        return "redirect:" + Global.getAdminPath() + "/cust/user/?repage";
    }

}