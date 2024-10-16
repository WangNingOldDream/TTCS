package com.example.controller;


import com.example.common.Result;
import com.example.entity.CommonUser;
import com.example.entity.Competition;
import com.example.entity.Role;
import com.example.service.impl.CompetitionServiceImpl;
import com.example.service.impl.RoleServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RoleController {
    @Resource
    private RoleServiceImpl roleService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        //roleService.add(role);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        //role.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //roleService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Role role) {
        //roleService.updateById(role);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        //Role role = roleService.selectById(id);
        //return Result.success(role);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Role role ) {
//        List<Role> list = roleService.selectAll(role);
//        return Result.success(list);
        return Result.success();
    }

     /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Competition competition,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<CommonUser> page=null;
        //PageInfo<CommonUser> page = competitionSerice.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }

}
