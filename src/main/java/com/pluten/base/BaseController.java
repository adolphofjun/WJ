package com.pluten.base;


import com.pluten.base.service.BaseService;
import com.pluten.utils.Constant;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/basedata/")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "new_qu_bank",method = RequestMethod.POST)
    @ApiOperation(value = "新建题库", notes = "新建题库")
    @ResponseBody
    public String new_qu_bank(@RequestBody @ApiParam(name = "题库", value = "传入json格式{\"name\":\"销售\",\"code\":\"sale\",\"type\":\"1\",\"visibility\":\"0\"}", required = true) Map bank){
        ResultMsg resultMsg;
        try {
            if(baseService.exitBankNameOrCode(bank)){
                resultMsg = ResultUtil.success("题库名或编码已存在",bank);
            }else{
                baseService.saveBank(bank);
                resultMsg = ResultUtil.success("添加题库成功",bank);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "new_dept",method = RequestMethod.POST)
    @ApiOperation(value = "新建部门", notes = "新建部门")
    @ResponseBody
    public String new_dept(@RequestBody @ApiParam(name = "部门", value = "传入json格式{\"name\":\"销售\",\"code\":\"sale\",\"visibility\":\"1\"}", required = true) Map dept){
        ResultMsg resultMsg;
        try {
            if(baseService.exitDeptNameOrCode(dept)){
                resultMsg = ResultUtil.success("部门名或编码已存在",dept);
            }else{
                baseService.saveDept(dept);
                resultMsg = ResultUtil.success("新建部门",dept);
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
            baseService.updateBankState(bank);
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

    @RequestMapping(value = "updateDeptState",method = RequestMethod.POST)
    @ApiOperation(value = "修改部门状态", notes = "修改部门状态")
    @ResponseBody
    public String updateDeptState(@RequestBody @ApiParam(name = "部门", value = "传入json格式{\"id\":\"1\",\"visibility\":\"1\"}", required = true) Map dept){
        ResultMsg resultMsg;
        try {
            baseService.updateDeptState(dept);
            resultMsg = ResultUtil.success("修改部门状态成功",dept);
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),dept);
            }else if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),dept);
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
                baseService.deleteBank(bankId);
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

    @RequestMapping(value = "delete_Dept/{deptId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除部门", notes = "删除部门")
    @ResponseBody
    public String delete_Dept(@PathVariable Integer deptId){
        ResultMsg resultMsg;
        try {
            baseService.deleteDept(deptId);
            resultMsg = ResultUtil.success("删除部门成功","");
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
            List<Map> maps = baseService.findBankList(null);
            resultMsg = ResultUtil.success("查询题库成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "find_dept",method = RequestMethod.GET)
    @ApiOperation(value = "查询部门", notes = "查询部门")
    @ResponseBody
    public ResultMsg find_dept(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = baseService.findDeptList(null);
            resultMsg = ResultUtil.success("查询部门成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findUserOfDeptAndRole/{deptId}/{roleId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询一个部门下一个角色下的员工", notes = "查询一个部门下一个角色下的员工")
    @ResponseBody
    public ResultMsg findUserOfDeptAndRole(@PathVariable Integer deptId,@PathVariable Integer roleId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = baseService.findUserOfRoleAndDept(deptId,roleId);
            resultMsg = ResultUtil.success("查询一个部门下一个角色下的员工成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }
}
