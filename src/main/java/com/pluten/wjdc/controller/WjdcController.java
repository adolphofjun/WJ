package com.pluten.wjdc.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import com.pluten.wjdc.service.WjdcService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/dw")
public class WjdcController {

    @Autowired
    private WjdcService wjdcService;

    @RequestMapping(value = "new_rule",method = RequestMethod.POST)
    @ApiOperation(value = "新建规则", notes = "新建规则")
    @ResponseBody
    public ResultMsg newRule(@RequestBody @ApiParam(name = "角色", value = "传入json格式{\n" +
            "\t\"name\": \"规则1\",\n" +
            "\t\"code\": \"入了1\",\n" +
            "\t\"belong_role\": \"1\",\n" +
            "\t\"must_bank\": [\"1\", \"2\"],\n" +
            "\t\"random_bank\": [\"1\", \"2\"],\n" +
            "\t\"visibility\": \"0\"\n" +
            "}", required = true) Map rule){
        ResultMsg resultMsg;
        try {
            wjdcService.saveRule(rule);
            resultMsg = ResultUtil.success("新建规则成功",rule);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }


    public static void main(String[] str){
        Map map = new HashMap();
        List<String> must = new ArrayList<String>();
        must.add("1");
        must.add("2");
        List<String> random = new ArrayList<String>();
        random.add("1");
        random.add("2");
        map.put("name","规则一");
        map.put("code","rule1");
        map.put("visibility","1");
        map.put("must_bank",must);
        map.put("random_bank",random);
        System.out.println("==="+map.toString());
    }

}


