/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cust.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cust.entity.Tag;
import com.thinkgem.jeesite.modules.cust.service.TagService;
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
 * 
 *
 * @author julia
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cust/tag")
public class MyTagController extends BaseController {

    @Autowired
    private TagService tagService;

    @ModelAttribute
    public Tag get(@RequestParam(required = false) String id) {
        Tag entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tagService.getById(id);
		}
        if (entity == null) {
            entity = new Tag();
        }
        return entity;
    }

    @RequiresPermissions("cust:tag:view")
    @RequestMapping(value = {"list", ""})
    public String list(Tag tag, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Tag> page = tagService.findPage(new Page<Tag>(request, response), tag);
        model.addAttribute("page", page);

        return "modules/cust/tagList";
    }

    @RequiresPermissions("cust:tag:view")
    @RequestMapping(value = "form")
    public String form(Tag tag, Model model) {
        model.addAttribute("tag", tag);
        return "modules/cust/tagForm";
    }

    @RequiresPermissions("cust:tag:edit")
    @RequestMapping(value = "save")
    public String save(Tag tag, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, tag)) {
            return form(tag, model);
        }
        tagService.save(tag);
        addMessage(redirectAttributes, "保存Tag成功");
        return "redirect:" + Global.getAdminPath() + "/cust/tag/?repage";
    }

    @RequiresPermissions("cust:tag:edit")
    @RequestMapping(value = "delete")
    public String delete(Tag tag, RedirectAttributes redirectAttributes) {
        tagService.delete(tag);
        addMessage(redirectAttributes, "删除Tag成功");
        return "redirect:" + Global.getAdminPath() + "/cust/tag/?repage";
    }

}