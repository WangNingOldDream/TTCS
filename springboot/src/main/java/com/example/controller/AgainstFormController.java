package com.example.controller;

import com.example.common.Result;
import com.example.entity.AgainstForm;
import com.example.service.impl.AgainstFormServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/against")
public class AgainstFormController {
    @Resource
    private AgainstFormServiceImpl againstFormService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody AgainstForm againstForm) {
        //againstFormService.add(againstForm);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        //againstFormService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //againstFormService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AgainstForm againstForm) {
        //againstFormService.updateById(againstForm);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
//        AgainstForm againstForm = againstFormService.selectById(id);
//        return Result.success(againstForm);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AgainstForm againstForm ) {
//        List<AgainstForm> list = againstFormService.selectAll(againstForm);
//        return Result.success(list);
        return Result.success();
    }

    @GetMapping("/createAgainstForm")
    public Result createAgainstForm() {
//        againstFormService.createAgainstForm();
//        return Result.success();
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AgainstForm againstForm,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
//        PageInfo<AgainstForm> page = againstFormService.selectPage(againstForm, pageNum, pageSize);
//        return Result.success(page);
        return Result.success();
    }
}
