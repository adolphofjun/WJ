package com.pluten.auth.controller;


import com.pluten.auth.service.AuthService;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "findUserOfRole/{roleId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询该角色下对应的员工", notes = "查询该角色下对应的员工")
    @ResponseBody
    public ResultMsg findUserOfRole(@PathVariable Integer roleId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = authService.findUserOfRole(roleId);
            resultMsg = ResultUtil.success("查询该角色下对应的员工成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findRoleOfUser/{userId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询员工拥有的角色", notes = "查询员工拥有的角色")
    @ResponseBody
    public ResultMsg findRoleOfUser(@PathVariable Integer userId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = authService.findRoleOfUser(userId);
            resultMsg = ResultUtil.success("查询员工拥有的角色",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }



}
