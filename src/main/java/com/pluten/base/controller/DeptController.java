package com.pluten.base.controller;


import com.alibaba.fastjson.JSON;
import com.pluten.base.service.DeptService;
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
@RequestMapping(value = "api/dept/")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "new_dept",method = RequestMethod.POST)
    @ApiOperation(value = "新建部门", notes = "新建部门")
    @ResponseBody
    public String new_dept(@RequestBody @ApiParam(name = "部门", value = "传入json格式{\"name\":\"销售\",\"code\":\"sale\",\"visibility\":\"1\"}", required = true) Map dept){
        ResultMsg resultMsg;
        try {
            if(deptService.exitDeptNameOrCode(dept)){
                resultMsg = ResultUtil.success("部门名或编码已存在",dept);
            }else{
                deptService.saveDept(dept);
                resultMsg = ResultUtil.success("新建部门",dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            deptService.updateDeptState(dept);
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

    @RequestMapping(value = "delete_Dept/{deptId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除部门", notes = "删除部门")
    @ResponseBody
    public String delete_Dept(@PathVariable Integer deptId){
        ResultMsg resultMsg;
        try {
            deptService.deleteDept(deptId);
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

    @RequestMapping(value = "find_dept",method = RequestMethod.GET)
    @ApiOperation(value = "查询部门", notes = "查询部门")
    @ResponseBody
    public ResultMsg find_dept(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = deptService.findDeptList(null);
            resultMsg = ResultUtil.success("查询部门成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }


}
