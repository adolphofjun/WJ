package com.pluten.wjdc.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.utils.*;
import com.pluten.wjdc.service.WjJfService;
import com.pluten.wjdc.service.WjService;
import com.pluten.wjdc.service.WjdcService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(WjdcController.class);

    @Autowired
    private WjdcService wjdcService;
    @Autowired
    private WjService wjService;
    @Autowired
    private WjJfService wjJfService;

    @RequestMapping(value = "findQuestionByBanId/{bankId}",method = RequestMethod.GET)
    @ApiOperation(value = "获取题库对应的题目", notes = "获取题库对应的题目")
    @ResponseBody
    public ResultMsg findQuestionByBanId(@PathVariable Integer bankId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjdcService.findQuestionByBankId(bankId);
            resultMsg = ResultUtil.success("获取题库对应的题目成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findWjTitleUsable",method = RequestMethod.GET)
    @ApiOperation(value = "获取启用的问卷", notes = "获取启用的问卷")
    @ResponseBody
    public ResultMsg findWjTitleUsable(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjService.findWjTitleUsableList();
            resultMsg = ResultUtil.success("获取启用的问卷成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "updateQuestState",method = RequestMethod.POST)
    @ApiOperation(value = "修改题目状态", notes = "修改题目状态")
    @ResponseBody
    public String updateQuestState(@RequestBody @ApiParam(name = "题目", value = "传入json格式{\"quId\":\"1\",\"state\":\"1\"}", required = true) Map quest){
        ResultMsg resultMsg;
        try {
            wjdcService.updateQuestState(quest);
            resultMsg = ResultUtil.success("修改题目状态成功",quest);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }
    @RequestMapping(value = "newWj",method = RequestMethod.POST)
    @ApiOperation(value = "新建问卷", notes = "新建问卷")
    @ResponseBody
    public ResultMsg newWj(@RequestBody @ApiParam(name = "新建问卷", value = "传入json格式{\n" +
            "\t\"name\": \"问卷名称\",\n" +
            "\t\"code\": \"问卷编码\",\n" +
            "\t\"ruleId\": \"1\",\n" +
            "\t\"creator\": \"1\",\n" +
            "\t\"visibility\": \"0\",\n" +
            "\t\"roleIds\":[\"1\", \"2\"],\n" +
            "\t\"startTime\": \"2014-06-12 12:12:00\",\n" +
            "\t\"endTime\": \"2014-06-12 13:12:00\"\n" +
            "}", required = true) Map wj){
        ResultMsg resultMsg;
        try {
            logger.info("========"+wj.toString());
            wjService.newWj(wj);
            resultMsg = ResultUtil.success("新建问卷成功",wj);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====新建问卷成功==");
            if(Constant.QUESTION_NOT_ENOUGH.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.QUESTION_NOT_ENOUGH.getExplanation(),null);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findWjTitleList",method = RequestMethod.GET)
    @ApiOperation(value = "查询问卷头", notes = "查询问卷头")
    @ResponseBody
    public ResultMsg findWjTitleList(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjService.findWjTitleList();
            resultMsg = ResultUtil.success("查询问卷头成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findTargetOfWj/{wjId}",method = RequestMethod.GET)
    @ApiOperation(value = "获取问卷对象", notes = "获取问卷对象")
    @ResponseBody
    public ResultMsg findTargetOfWj(@PathVariable Integer wjId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjService.findWjTarget(wjId);
            resultMsg = ResultUtil.success("获取问卷对象成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findWjQuest/{wjId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询问卷题目", notes = "查询问卷题目")
    @ResponseBody
    public ResultMsg findWjQuest(@PathVariable Integer wjId){
        ResultMsg resultMsg;
        try {
            Map map = wjService.findQuestByWjId(wjId);
            resultMsg = ResultUtil.success("查询问卷题目成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    //{data=[{select=C, quId=1}, {select=C, quId=2}], userId=1}
    @RequestMapping(value = "submit_wj",method = RequestMethod.POST)
    @ApiOperation(value = "提交问卷", notes = "提交问卷")
    @ResponseBody
    public ResultMsg submit_wj(@RequestBody  Map wj){
        ResultMsg resultMsg;
        try {
            logger.info("====="+wj.toString());
          /* logger.info("====="+wj.get("data"));
            logger.info("====="+wj.get("userId"));
            logger.info("====="+wj.get("targetId"));*/
            wjJfService.saveEveryOneRecord(wj);
            resultMsg = ResultUtil.success("提交成功",wj);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "isExisted",method = RequestMethod.POST)
    @ApiOperation(value = "查询是否答题过", notes = "查询是否答题过")
    @ResponseBody
    public ResultMsg isExisted(@RequestBody  Map wj){
        ResultMsg resultMsg;
        try {
            logger.info("====="+wj.toString());
            Integer is = wjService.isExisted(wj);
            resultMsg = ResultUtil.success("成功",is);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findWjTarget/{quId}",method = RequestMethod.GET)
    @ApiOperation(value = "获取问卷的所有对象", notes = "获取问卷的所有对象")
    @ResponseBody
    public ResultMsg findWjTarget(@PathVariable Integer quId){
        ResultMsg resultMsg;
        try {
            logger.info("========="+quId);
            //List<Map> maps = wjdcService.findWjTarget(quId);
            resultMsg = ResultUtil.success("获取问卷的所有对象成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

   /* @RequestMapping(value = "findWjResult",method = RequestMethod.GET)
    @ApiOperation(value = "查询调查问卷试卷卷头", notes = "查询调查问卷试卷卷头")
    @ResponseBody
    public ResultMsg findWjResult(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjdcService.findWj();
            resultMsg = ResultUtil.success("查询调查问卷试卷卷头成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }*/

   /* @RequestMapping(value = "findWj",method = RequestMethod.GET)
    @ApiOperation(value = "查询调查问卷试卷卷头", notes = "查询调查问卷试卷卷头")
    @ResponseBody
    public ResultMsg findWj(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjdcService.findWj();
            resultMsg = ResultUtil.success("查询调查问卷试卷卷头成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findResultTitle",method = RequestMethod.POST)
    @ApiOperation(value = "查询结果卷头", notes = "查询结果卷头")
    @ResponseBody
    public ResultMsg findResultTitle(@RequestBody @ApiParam(name = "查询问卷结果", value = "传入json格式{\"quId\":\"1\",\"targetId\":\"1\"}", required = true) Map wj){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjdcService.findResultTile(wj);
            resultMsg = ResultUtil.success("查询结果卷头成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }*/

    @RequestMapping(value = "countWjResult",method = RequestMethod.POST)
    @ApiOperation(value = "统计问卷结果", notes = "统计问卷结果")
    @ResponseBody
    public ResultMsg countWjResult(@RequestBody @ApiParam(name = "统计问卷结果", value = "传入json格式{\"wjId\":\"1\",\"targetId\":\"1\"}", required = true) Map wj){
        ResultMsg resultMsg;
        try {
            wjJfService.saveSumRecord(wj);
            resultMsg = ResultUtil.success("统计问卷结果成功",wj);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }


   /* @RequestMapping(value = "updateWjState",method = RequestMethod.POST)
    @ApiOperation(value = "修改问卷状态", notes = "修改问卷状态")
    @ResponseBody
    public String updateWjState(@RequestBody @ApiParam(name = "题库", value = "传入json格式{\"id\":\"1\",\"visibility\":\"1\"}", required = true) Map wj){
        ResultMsg resultMsg;
        try {
            wjdcService.updateWjState(wj);
            resultMsg = ResultUtil.success("修改规则状态成功",wj);
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),wj);
            } else if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),wj);
            }else
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "deleteWj/{wjId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除问卷", notes = "删除问卷")
    @ResponseBody
    public String deleteWj(@PathVariable Integer wjId){
        ResultMsg resultMsg;
        try {
            wjdcService.deleteWjById(wjId);
            resultMsg = ResultUtil.success("删除规则成功","");
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),null);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "findQuestionOfWj/{wjId}/{wjNum}",method = RequestMethod.GET)
    @ApiOperation(value = "获取问卷下的所有题目", notes = "获取问卷下的所有题目")
    @ResponseBody
    public ResultMsg findQuestionOfWj(@PathVariable Integer wjId,@PathVariable Integer wjNum){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjdcService.findQuestionOfWj(wjId,wjNum);
            resultMsg = ResultUtil.success("获取问卷下的所有题目",maps);
        } catch (Exception e) {
            e.printStackTrace();
            if("题库中必选题数量不够".endsWith(e.getMessage())){
                resultMsg = ResultUtil.success("题库中必选题数量不够",null);
            }else if("题库中随机题数量不够".endsWith(e.getMessage())){
                resultMsg = ResultUtil.success("题库中随机题数量不够",null);
            }
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }*/

   @RequestMapping(value = "findQuestion",method = RequestMethod.GET)
    @ApiOperation(value = "查询题目", notes = "查询题目")
    @ResponseBody
    public ResultMsg findQuestion(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjdcService.findQuestion(null);
            resultMsg = ResultUtil.success("查询题目成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    @ApiOperation(value = "excel导入", notes = "excel导入")
    @ResponseBody
    public ResultMsg batchimport(MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultMsg resultMsg;
        try {
            String Msg ="";
            //判断文件是否为空
            if(file==null) return ResultUtil.success("文件为空","null");
            //获取文件名
            String name=file.getOriginalFilename();
            logger.info("========文件名"+name);
            //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
            long size=file.getSize();
            if(name==null || ("").equals(name) && size==0) return ResultUtil.success("文件为空","null");;
            //批量导入。参数：文件名，文件。
            boolean b = batchImport(name,file);
            if(b){
                Msg ="批量导入EXCEL成功！";
            }else{
                Msg ="批量导入EXCEL失败！";
            }
            resultMsg = ResultUtil.success(Msg,name);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }
    public boolean batchImport(String name,MultipartFile file){
        boolean b = false;
        //创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
         List<Map> maps = readExcel.getExcelInfo(name ,file);
             wjdcService.saveQuestion(maps);
             b=true;
        logger.info("文件数据=========="+maps.toString());
        //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
        return b;
    }




}


