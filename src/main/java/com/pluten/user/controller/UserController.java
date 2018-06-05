package com.pluten.user.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.user.service.UserService;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "new_sys_role",method = RequestMethod.POST)
    @ApiOperation(value = "新建角色", notes = "新建角色")
    @ResponseBody
    public String new_sys_role(@RequestBody @ApiParam(name = "角色", value = "传入json格式{\"name\":\"管理员\",\"code\":\"admin\",\"power\":\"拥有最高权限\",\"orginId\":\"0\",\"userType\":\"0\"}", required = true) Map role){
        ResultMsg resultMsg;
        try {
            if(userService.exitRoleNameOrCode(role)){
                resultMsg = ResultUtil.success("角色名或编码已存在",role);
            }else{
                userService.saveRole(role);
                resultMsg = ResultUtil.success("添加角色成功",role);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "findRole",method = RequestMethod.GET)
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @ResponseBody

    public ResultMsg findRole(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = userService.findRoleList(null);
            resultMsg = ResultUtil.success("查询角色成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

   /* @RequestMapping(value = "find_sys_role",method = RequestMethod.GET)
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @ResponseBody
    public ResultMsg find_sys_role(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = userService.findRoleList(null);
            resultMsg = ResultUtil.success("查询角色库成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }*/
}
