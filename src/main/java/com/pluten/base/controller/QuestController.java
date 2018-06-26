package com.pluten.base.controller;


import com.alibaba.fastjson.JSON;
import com.pluten.base.service.QuestService;
import com.pluten.utils.Constant;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "api/quest/")
public class QuestController {

    @Autowired private QuestService questService;
    @RequestMapping(value = "editQuest",method = RequestMethod.POST)
    @ApiOperation(value = "修改题目", notes = "修改题目")
    @ResponseBody
    public String updateWjState(@RequestBody @ApiParam(name = "修改题目", value = "传入json格式{\"id\":\"1\",\"visibility\":\"1\"}", required = true) Map quest){
        ResultMsg resultMsg;
        try {
            System.out.println("===="+quest.toString());
            questService.editQuest(quest);
            resultMsg = ResultUtil.success("修改题目成功",quest);
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),quest);
            } else if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),quest);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }
}
