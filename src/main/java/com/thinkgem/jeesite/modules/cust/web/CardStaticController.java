package com.thinkgem.jeesite.modules.cust.web;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Series;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cust.entity.CardExchange;
import com.thinkgem.jeesite.modules.cust.entity.Customer;
import com.thinkgem.jeesite.modules.cust.service.CardService;
import com.thinkgem.jeesite.modules.cust.service.CustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 客户管理Controller
 *
 * @author julia
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cust/cardStatistic")
public class CardStaticController extends BaseController {

    @Autowired
    private CustomerService custService;

    @Autowired
    private CardService cardService;

    @ModelAttribute
    public CardExchange get(@RequestParam(required = false) String id) {
        CardExchange entity = new CardExchange();
        return entity;
    }

    @RequiresPermissions("cust:statistic:view")
    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {

        List<Customer> users = custService.findList();

        List<CardExchange> cards = cardService.findCardExchangeList();
        Map<Long, Integer> exchages = new HashMap();
        List<Integer> genders = new ArrayList<>();
        for (int i= 0; i< 3; i++){
            genders.add(0);
        }
        for (CardExchange t : cards) {

            Long uid1 = t.getSend_user_id();
            Long uid2 = t.getReceive_user_id();
            if (uid1 > 0){
                if (exchages.containsKey(uid1)){
                    exchages.put(uid1, exchages.get(uid1) +1);
                }
                else{
                    exchages.put(uid1, 1);
                }
//                Integer gender1 = getGenderForUser(uid1, users);
//                genders.add(gender1,genders.get(gender1) +1);
            }
            if (uid2 > 0){
                if (exchages.containsKey(uid2)){
                    exchages.put(uid2, exchages.get(uid2) +1);
                }
                else{
                    exchages.put(uid2, 1);
                }
//                Integer gender2 = getGenderForUser(uid2, users);
//                genders.add(gender2,genders.get(gender2) +1);
            }
        }
        for (Customer user: users){
            Long uid = Long.parseLong(user.getId());
            if (!exchages.containsKey(uid)){
                exchages.put(uid, 0);
            }
        }

        // 交换次数: 人数
        Map<Integer, Integer>[] changes = new Map[3] ;
        changes[0] = new HashMap<>();
        changes[1] = new HashMap<>();
        changes[2] = new HashMap<>();

        for (Long uid : exchages.keySet()){
            Integer gender = getGenderForUser(uid, users);
            genders.add(gender,genders.get(gender) +1);

            int num = exchages.get(uid);
            if (changes[gender].containsKey(num)){
                changes[gender].put(num, changes[gender].get(num)+1);
            }
            else{
                changes[gender].put(num,1);
            }
        }
        List<Integer> xvalue = new ArrayList<>();

        List<Integer>[] yvalue = new ArrayList[3];
        yvalue[0] = new ArrayList<>();
        yvalue[1] = new ArrayList<>();
        yvalue[2] = new ArrayList<>();

        Integer max = 0;
        for (Map<Integer, Integer> chag : changes){
            Set<Integer> keySet = chag.keySet();
            for (Integer t: keySet){
                if (t> max)
                    max =t;
            }
        }

        for (int i = 0; i<= max; i++){
            xvalue.add(i);
//            if(keySet.contains(i))
//                yvalue.add(changes.get(i));
//            else
//                yvalue.add(0);
        }

        for (int i =0; i< 3; i++){
            for (int j = 0; j<= max; j++) {
                Set<Integer> keySet = changes[i].keySet();
                if (keySet.contains(j))
                    yvalue[i].add(changes[i].get(j));
                else
                    yvalue[i].add(0);
            }
        }

        GsonOption option = new GsonOption();
        option.title().text("用户交换名片情况").x("center");

        option.tooltip().setTrigger(Trigger.axis);

        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.bar, Magic.line), Tool.restore, Tool.saveAsImage);
        option.calculable(true);

        String[] legend = {"男","女","不详"};
        option.legend().data(legend).y("bottom");

//        ValueAxis xAxis = new ValueAxis();
//        xAxis.setMax(max);
//        xAxis.setMin(0);
//        option.xAxis(xAxis);
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setData(xvalue);
        xAxis.setName("交换名片次数");
        xAxis.splitLine().show(false);
        option.xAxis(xAxis);

        ValueAxis yAxis = new ValueAxis();
        yAxis.show(true);
        yAxis.setName("人数");
//        yAxis.splitLine().show(false);
        option.yAxis(yAxis);

        Bar bar1 = new Bar();
        bar1.name(legend[0]).setData( yvalue[0]);

        Bar bar2 = new Bar();
        bar2.name(legend[1]).setData( yvalue[1]);

        Bar bar3 = new Bar();
        bar3.name(legend[2]).setData( yvalue[2]);
//        option.series(bar);

//        Pie pie = new Pie();
//        pie.setName("用户性别比例");
//        pie.itemStyle().normal().labelLine().length(20);
//        pie.tooltip().trigger(Trigger.item);
////        Integer[] cat= {180,150};
////        pie.center(cat);
////        Integer[] rad = {0,70};
////        pie.radius(rad);
//        List<PieData> pd = new ArrayList<>();
//        for (int i =0 ; i< 3 ; i++){
//            PieData pdata = new PieData(legend[i], genders.get(i));
//            pd.add(pdata);
//        }
//        pie.data(pd);

        List<Series> serieses = new ArrayList<>();
        serieses.add(bar1);
        serieses.add(bar2);
        serieses.add(bar3);
//        serieses.add(pie);

        option.series(serieses);
        System.out.println(new Gson().toJson(option));
        model.addAttribute("option", option);
        return "modules/cust/cardStatisticList";
    }

    public Integer getGenderForUser(Long uid, List<Customer> users){
        for (Customer user :users){
            if (user.getId().equals(uid.toString())){
                if (user.getGender()!= null)
                    return user.getGender();
                else
                    return 2;
            }

        }
        return 2;
    }


}