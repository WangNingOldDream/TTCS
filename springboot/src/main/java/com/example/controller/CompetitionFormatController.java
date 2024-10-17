package com.example.controller;

import com.example.common.Result;
import com.example.entity.CompetitionFormat;
import com.example.entity.TtcLevel;
import com.example.service.impl.CompetitionFormatServiceImpl;
import com.example.service.impl.TtcLevelServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/competition-format")
public class CompetitionFormatController {
    @Resource
    private CompetitionFormatServiceImpl competitionFormatService;

    /*
    查找所有
     */
    @GetMapping("/selectALl")
    public Result selectAll(CompetitionFormat competitionFormat){
//        List<CompetitionFormat> list = competitionFormatService.selectAll(competitionFormat);
//        return Result.success(list);
        return Result.success();
    }
}


