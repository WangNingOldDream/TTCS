package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    /*
    @RequestBody 自动将前端传来数据转为对应类对象
     */
    @PostMapping("/add")
    public Result add(@RequestBody AgainstForm againstForm) {
        againstFormService.save(againstForm);
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


    /**
     * 修改
     *  @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //againstFormService.deleteBatch(ids);
        return Result.success();
    }
*/


    @PutMapping("/update")
    public Result updateById(@RequestBody AgainstForm againstForm) {
        againstFormService.updateById(againstForm);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AgainstForm againstForm= againstFormService.getById(id);
        return Result.success(againstForm);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(String params) {
        List<AgainstForm> list=null;
        if(params!=null&&!"".equals(params)){
            QueryWrapper<AgainstForm> queryWrapper=new QueryWrapper<>();
            queryWrapper.like("competition_id",params);
            queryWrapper.or();
            queryWrapper.like("against_contest_id",params);
            list=againstFormService.list(queryWrapper);
            return Result.success(list);
        }
        list = againstFormService.list();
        return Result.success(list);
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
