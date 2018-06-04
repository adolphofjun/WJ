package com.pluten.test;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/")
public class TestClass {
    @Autowired
    TestDao testDao;
    @RequestMapping(value = "test")
    @ApiOperation(value = "根据用户获取会见申请列表——", notes = "根据用户获取会见申请列表")
    @ResponseBody
    public void test(){
        List<Map> maps = testDao.getTest();
        if(maps!=null){
            System.out.println("===="+maps.toString());
        }else{
        } System.out.println("====k");

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(){
        System.out.println("===login=k");
        return  "user";
    }

    @RequestMapping(value = "basedata", method = RequestMethod.GET)
    public String basedata(){
        System.out.println("===login=k");
        return  "basedata";
    }

    @RequestMapping(value = "T", method = RequestMethod.POST)
    public String t(){
        System.out.println("===login=k");
        return  "index";
    }
}
