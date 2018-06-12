package com.pluten.user.controller;

import com.alibaba.fastjson.JSON;
import com.pluten.user.service.UserService;
import com.pluten.utils.Constant;
import com.pluten.utils.Globel;
import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.scene.chart.ValueAxis;
import jdk.nashorn.internal.objects.Global;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "api/user")
public class UserController {

    Logger logger = Logger.getLogger("UserController");
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


    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录")
    @ResponseBody
    public String login(@RequestBody @ApiParam(name = "角色", value = "传入json格式{\"name\":\"管理员\",\"code\":\"admin\",\"pwd\":\"123456\"}", required = true) Map user,HttpServletRequest request){
        ResultMsg resultMsg;
        try {
            if(userService.checkUser(user)){
                Map user_rt = userService.findUserByCode((String) user.get("code"));
                request.getSession().setAttribute(Globel.USER_SESSION_KEY, user_rt);
                resultMsg = ResultUtil.success("登录成功",user_rt);
            }else{
                resultMsg = ResultUtil.success("账号不存在或密码错误！",user);
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

    @RequestMapping(value = "findAllUser",method = RequestMethod.GET)
    @ApiOperation(value = "查询员工", notes = "查询员工")
    @ResponseBody
    public ResultMsg findAllUser(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = userService.findAllUser(null);
            resultMsg = ResultUtil.success("查询员工成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findUserByDeptId/{deptId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据部门Id查询员工", notes = "根据部门Id查询员工")
    @ResponseBody
    public ResultMsg findUserByDeptId(@PathVariable Integer deptId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = userService.findUserByDeptId(deptId);
            logger.info("根据部门Id查询员工=========="+maps.toString());
            resultMsg = ResultUtil.success("根据部门Id查询员工成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "findUserByDeptCode/{deptCode}",method = RequestMethod.GET)
    @ApiOperation(value = "根据部门Code查询员工", notes = "根据部门Code查询员工")
    @ResponseBody
    public ResultMsg findUserByDeptCode(@PathVariable String deptCode){
        ResultMsg resultMsg;
        try {
            List<Map> maps = userService.findUserByDeptCode(deptCode);
            resultMsg = ResultUtil.success("根据部门Code查询员工成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }


    @RequestMapping(value = "findWjOfRole/{roleId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询角色可查看的问卷", notes = "查询角色可查看的问卷")
    @ResponseBody
    public ResultMsg findWjOfRole(@PathVariable Integer roleId){
        ResultMsg resultMsg;
        try {
            List<Map> maps = userService.findWjOfRole(roleId);
            resultMsg = ResultUtil.success("查询角色可查看的问卷",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }

    @RequestMapping(value = "deleteUser/{userId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ResponseBody
    public String deleteUser(@PathVariable Integer userId){
        ResultMsg resultMsg;
        try {
            userService.deleteUser(userId);
            //wjdcService.deleteWjById(wjId);
            resultMsg = ResultUtil.success("删除用户成功","");
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),null);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }


    @RequestMapping(value = "updateUserState",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户状态", notes = "修改用户状态")
    @ResponseBody
    public String updateWjState(@RequestBody @ApiParam(name = "题库", value = "传入json格式{\"id\":\"1\",\"visibility\":\"1\"}", required = true) Map user){
        ResultMsg resultMsg;
        try {
            userService.updateUserState(user);
            resultMsg = ResultUtil.success("修改用户状态成功",user);
        } catch (Exception e) {
            e.printStackTrace();
            if(Constant.ARGUMENT_EXCEPTION.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.ARGUMENT_EXCEPTION.getExplanation(),user);
            } else if(Constant.STATE_IS_NOT_VARIBALE.getExplanation().equals(e.getMessage())){
                resultMsg = ResultUtil.success(Constant.STATE_IS_NOT_VARIBALE.getExplanation(),user);
            }else
                resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value = "注册", notes = "注册")
    @ResponseBody
    public String register(@RequestBody @ApiParam(name = "注册",
            value = "传入json格式{\"name\":\"张三\",\"code\":\"zs\",\"pwd\":\"123\",\"orginId\":\"1\"," +
                    "\"deptId\":\"1\",\"sex\":\"1\",\"address\":\"zs\",\"idCard\":\"123\",\"email\":\"1\",\" +\n" +
                    "\"education\":\"1\",\"school\":\"1\"}", required = true) Map user){
        ResultMsg resultMsg;
        try {
            if(userService.exitUserCode(user)){
                resultMsg = ResultUtil.success("账号已存在",user);
            }else{
                logger.info("==========="+user.toString());
                userService.saveUser(user);
                resultMsg = ResultUtil.success("注册成功",user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

    @RequestMapping(value = "impower",method = RequestMethod.POST)
    @ApiOperation(value = "授权", notes = "授权")
    @ResponseBody
    public String impower(@RequestBody @ApiParam(name = "授权",
            value = "传入json格式{\"userId\":\"1\",\"roleId\":\"1\"}", required = true) Map auth){
        ResultMsg resultMsg;
        try {
            userService.impower(auth);
            resultMsg = ResultUtil.success("授权成功",auth);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return JSON.toJSONString(resultMsg);
    }

}
