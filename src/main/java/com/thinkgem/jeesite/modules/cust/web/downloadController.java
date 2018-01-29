package com.thinkgem.jeesite.modules.cust.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cust.entity.Comment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


/**
 * 客户管理Controller
 *
 * @author julia
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cust/download")
public class downloadController extends BaseController {


    @ModelAttribute
    public Comment get(@RequestParam(required = false) String id) {
        Comment entity = new Comment();
        return entity;
    }

    @RequiresPermissions("cust:comment:view")
    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<Comment> page = custService.findPage(new Page<Comment>(request, response), comment);
        Page<Comment> page = new Page();
        page.setCount(0);
        page.setList(new ArrayList<Comment>());

        model.addAttribute("page", page);
        return "modules/cust/downloadList";
    }


}