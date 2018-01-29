package com.thinkgem.jeesite.modules.cust.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cust.entity.Comment;
import com.thinkgem.jeesite.modules.cust.service.CommentService;
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
@RequestMapping(value = "${adminPath}/cust/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService custService;

    @ModelAttribute
    public Comment get(@RequestParam(required = false) String id) {
        Comment entity = new Comment();
        return entity;
    }

    @RequiresPermissions("cust:comment:view")
    @RequestMapping(value = {"list", ""})
    public String list(Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Comment> page = custService.findPage(new Page<Comment>(request, response), comment);
        model.addAttribute("page", page);
        return "modules/cust/commentList";
    }


    @RequiresPermissions("cust:comment:edit")
    @RequestMapping(value = "delete")
    public String delete(Comment comment, RedirectAttributes redirectAttributes) {
        custService.delete(comment);
        addMessage(redirectAttributes, "删除User成功");
        return "redirect:" + Global.getAdminPath() + "/cust/comment/?repage";
    }

}