package com.thinkgem.jeesite.modules.cust.web;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cust.entity.Tag;
import com.thinkgem.jeesite.modules.cust.entity.UserTag;
import com.thinkgem.jeesite.modules.cust.service.CommentService;
import com.thinkgem.jeesite.modules.cust.service.TagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 客户管理Controller
 *
 * @author julia
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cust/tagStatistic")
public class tagStaticController extends BaseController {

    @Autowired
    private CommentService custService;

    @Autowired
    private TagService tagService;

    @ModelAttribute
    public Tag get(@RequestParam(required = false) String id) {
        Tag entity = new Tag();
        return entity;
    }

    @RequiresPermissions("cust:statistic:view")
    @RequestMapping(value = {"list", ""})
    public String list( HttpServletRequest request, HttpServletResponse response, Model model) {

        List<Tag> tags = tagService.findList();
        List<String> tagNAme = new ArrayList<>();
        List<Integer> tagNum = new ArrayList<>();
        for (Tag t: tags){
            tagNAme.add(t.getTag_description());
            List<UserTag> lists = tagService.getUserByTagid(t.getId());
            if (lists != null)
                tagNum.add(lists.size());
            else
                tagNum.add(0);
        }

        GsonOption option = new GsonOption();
        option.title().text("Tag与用户关系").x("center");

        option.tooltip().setTrigger( Trigger.item);

        option.toolbox().show(true).feature( Tool.dataView, Tool.restore, Tool.saveAsImage);

//        option.grid().borderWidth(0).y(80).y2(60);
        option.calculable(true);
//        option.tooltip().trigger(Trigger.axis).formatter("人数 : <br/> {c} :{b}人");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setData(tagNAme);
        option.xAxis(xAxis);

        ValueAxis yAxis = new ValueAxis();
//        yAxis.show(false);
        option.yAxis(yAxis);


        Bar bar = new Bar();
        bar.itemStyle().normal().label().position("top").formatter("{c}").show(true);
        bar.barWidth(40);
        bar.setData(tagNum);

        option.series(bar);
        model.addAttribute("option", option);
        return "modules/cust/tagStatisticList";
    }

    @RequiresPermissions("cust:statistic:view")
    @RequestMapping("/removecauses")
    public
    @ResponseBody
    GsonOption removecauses() throws Exception {
//		WebResult result = new WebResult();
        GsonOption option = new GsonOption();

        try {
            option.legend("高度(km)与气温(°C)变化关系");

            option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);

            option.calculable(true);
            option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");

            ValueAxis valueAxis = new ValueAxis();
            valueAxis.axisLabel().formatter("{value} °C");
            option.xAxis(valueAxis);

            CategoryAxis categoryAxis = new CategoryAxis();
            categoryAxis.axisLine().onZero(false);
            categoryAxis.axisLabel().formatter("{value} km");
            categoryAxis.boundaryGap(false);
            categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
            option.yAxis(categoryAxis);

            Line line = new Line();
            line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
            option.series(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return option;
    }

}