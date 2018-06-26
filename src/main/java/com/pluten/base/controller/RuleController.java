package com.pluten.base.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.base.service.RuleService;
import com.pluten.utils.Constant;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import com.pluten.wjdc.controller.WjdcController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/rule/")
public class RuleController {

    private static Logger logger = Logger.getLogger(RuleController.class);
    @Autowired
    private RuleService ruleService;

    @RequestMapping(value = "new_rule",method = RequestMethod.POST)
    @ApiOperation(value = "新建规则", notes = "新建规则")
    @ResponseBody
    public ResultMsg newRule(@RequestBody @ApiParam(name = "规则", value = "传入json格式{\"name\": \"规则2\", \"code\": \"gz2\", \"belong_role\": \"1\", \"visibility\": \"1\", \"bankInfo\": {\"must\": [{\"id\": \"1\", \"per\": \"20\"}, {\"id\": \"2\", \"per\": 30}], \"random\": [{\"id\": \"1\", \"per\": \"20\"}, {\"id\": \"2\", \"per\": \"30\"}]}, \"num\": \"10\"}", required = true) Map rule){
        ResultMsg resultMsg;
        try {
            logger.info("新建规则参数====="+rule.toString());
            ruleService.saveRule(rule);
            resultMsg = ResultUtil.success("新建规则成功",rule);
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),null);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findRule",method = RequestMethod.GET)
    @ApiOperation(value = "查询规则", notes = "查询规则")
    @ResponseBody
    public ResultMsg findRule(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = ruleService.findRule(null);
            resultMsg = ResultUtil.success("查询规则成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "updateRuleState",method = RequestMethod.POST)
    @ApiOperation(value = "修改规则状态", notes = "修改规则状态")
    @ResponseBody
    public String updateRuleState(@RequestBody @ApiParam(name = "题库", value = "传入json格式{\"id\":\"1\",\"visibility\":\"1\"}", required = true) Map rule){
        ResultMsg resultMsg;
        try {
            ruleService.updateRuleState(rule);
            resultMsg = ResultUtil.success("修改规则状态成功",rule);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "deleteRule/{ruleId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除规则", notes = "删除规则")
    @ResponseBody
    public String deleteRule(@PathVariable Integer ruleId){
        ResultMsg resultMsg;
        try {
            ruleService.deleteRule(ruleId);
            resultMsg = ResultUtil.success("删除规则成功",ruleId);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }
}
