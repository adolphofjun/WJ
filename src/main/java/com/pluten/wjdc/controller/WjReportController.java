package com.pluten.wjdc.controller;


import com.pluten.utils.ResultMsg;
import com.pluten.utils.ResultUtil;
import com.pluten.wjdc.service.WjJfService;
import com.pluten.wjdc.service.WjReportService;
import com.pluten.wjdc.service.WjService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/wj/report")
public class WjReportController {

    @Autowired private WjService wjService;
    @Autowired private WjJfService wjJfService;
    @Autowired private WjReportService wjReportService;


    @RequestMapping(value = "findWjReportTitleList",method = RequestMethod.GET)
    @ApiOperation(value = "", notes = "获取问卷报表头信息")
    @ResponseBody
    public ResultMsg findWjReportTitleList(){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjReportService.findWjTitleReportList();
            resultMsg = ResultUtil.success("获取题库对应的题目成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }


    @RequestMapping(value = "findWjReportRecordList",method = RequestMethod.POST)
    @ApiOperation(value = "查询答题记录", notes = "查询答题记录")
    @ResponseBody
    public ResultMsg findWjReportRecordList(@RequestBody @ApiParam(name = "查询答题记录", value = "传入json格式{\"wjId\":\"1\",\"targetId\":\"1\"}", required = true) Map map){
        ResultMsg resultMsg;
        try {
            List<Map> maps = wjJfService.findAnswerRecord(map);
            resultMsg = ResultUtil.success("查询答题记录成功",maps);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = ResultUtil.systemError();
        }
        return resultMsg;
    }
}
