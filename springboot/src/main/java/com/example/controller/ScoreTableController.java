package com.example.controller;


import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.ScoreTable;
import com.example.service.impl.AdminService;
import com.example.service.impl.ScoreTableServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreTableController {
    @Resource
    private ScoreTableServiceImpl scoreTableService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ScoreTable scoreTable) {
//        scoreTableService.add(scoreTable);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
//        scoreTableService.deleteById(id);
//        return Result.success();
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
//        scoreTableService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody ScoreTable scoreTable) {
        scoreTableService.updateById(scoreTable);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
//        ScoreTable scoreTable = scoreTableService.selectById(id);
//        return Result.success(scoreTable);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(ScoreTable scoreTable ) {
//        List<ScoreTable> list = scoreTableService.selectAll(scoreTable);
//        return Result.success(list);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(ScoreTable scoreTable,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
//        PageInfo<scoreTable> page = scoreTableService.selectPage(admin, pageNum, pageSize);
//        return Result.success(page);
        return Result.success();
    }

}
