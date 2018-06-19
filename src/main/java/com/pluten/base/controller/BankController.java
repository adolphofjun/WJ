package com.pluten.base.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.base.service.BankService;
import com.pluten.base.service.BaseService;
import com.pluten.utils.Constant;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/bank/")
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping(value = "new_qu_bank",method = RequestMethod.POST)
    @ApiOperation(value = "新建题库", notes = "新建题库")
    @ResponseBody
    public String new_qu_bank(@RequestBody @ApiParam(name = "题库", value = "传入json格式{\"name\":\"销售\",\"code\":\"sale\",\"type\":\"1\",\"visibility\":\"0\"}", required = true) Map bank){
        ResultMsg resultMsg;
        try {
            if(bankService.exitBankNameOrCode(bank)){
                resultMsg = ResultUtil.success("题库名或编码已存在",bank);
            }else{
                bankService.saveBank(bank);
                resultMsg = ResultUtil.success("添加题库成功",bank);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "updateBankState",method = RequestMethod.POST)
    @ApiOperation(value = "修改题库状态", notes = "修改题库状态")
    @ResponseBody
    public String updateBankState(@RequestBody @ApiParam(name = "题库", value = "传入json格式{\"id\":\"1\",\"visibility\":\"1\"}", required = true) Map bank){
        ResultMsg resultMsg;
        try {
            bankService.updateBankState(bank);
            resultMsg = ResultUtil.success("修改题库状态成功",bank);
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),bank);
            }else if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),bank);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "delete_qu_bank/{bankId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除题库", notes = "删除题库")
    @ResponseBody
    public String delete_qu_bank(@PathVariable Integer bankId){
        ResultMsg resultMsg;
        try {
            bankService.deleteBank(bankId);
            resultMsg = ResultUtil.success("删除题库成功","");
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),null);
            }else if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),null);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "find_qu_bank",method = RequestMethod.GET)
    @ApiOperation(value = "查询题库", notes = "查询题库")
    @ResponseBody
    public ResultMsg find_qu_bank(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = bankService.findBankList(null);
            resultMsg = ResultUtil.success("查询题库成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }
}
