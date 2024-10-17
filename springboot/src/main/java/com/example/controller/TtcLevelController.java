package com.example.controller;

import com.example.common.Result;
import com.example.entity.CommonUser;
import com.example.entity.TtcLevel;
import com.example.service.impl.TtcLevelServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ttc-level")
public class TtcLevelController {
    @Resource
    TtcLevelServiceImpl ttcLevelService;

    /*
    查找所有
     */
    @GetMapping("/selectALl")
    public Result selectAll(TtcLevel ttcLevel){
//        List<CommonUser> list = ttcLevelService.selectAll(ttcLevel);
//        return Result.success(list);
        return Result.success();
    }
}
