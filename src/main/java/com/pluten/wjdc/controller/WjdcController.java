package com.pluten.wjdc.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.utils.ReadExcel;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import com.pluten.wjdc.service.WjdcService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

   /* @RequestMapping(value = "importExcel",method = RequestMethod.POST)
    @ApiOperation(value = "新建规则", notes = "新建规则")
    @ResponseBody
    public ResultMsg importExcel(@RequestBody @ApiParam(name = "角色", value = "传入json格式{\n" +
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
    }*/


    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    @ApiOperation(value = "excel导入", notes = "excel导入")
    @ResponseBody
    public String batchimport(MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        //log.info("AddController ..batchimport() start");
        //判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        System.out.println("========文件名"+name);
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;

        //批量导入。参数：文件名，文件。
        boolean b = batchImport(name,file);
        if(b){
            String Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            String Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }
        return "Customer/addCustomer3";
    }

    public boolean batchImport(String name,MultipartFile file){
        boolean b = false;
        //创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
         readExcel.getExcelInfo(name ,file);



        //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）

        return b;
    }




}


