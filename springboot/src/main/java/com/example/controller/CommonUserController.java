package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.CommonUser;
import com.example.entity.SimpleUserInfo;
import com.example.service.impl.CommonUserServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/common-user")
public class CommonUserController {
    @Resource
    private CommonUserServiceImpl commonUserService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody CommonUser commonUser) {
        commonUserService.add(commonUser);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commonUserService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commonUserService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody CommonUser commonUser) {
        commonUserService.updateById(commonUser);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        CommonUser commonUser = commonUserService.selectById(id);
        return Result.success(commonUser);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(CommonUser commonUser ) {
        List<CommonUser> list = commonUserService.selectAll(commonUser);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<CommonUser> page = commonUserService.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectAllUserInComp/{compId}")
    public Result selectAllByCompId(@PathVariable Integer compId) {
        List<SimpleUserInfo> simpleUserInfo = commonUserService.selectAllByCompId(compId);
        return Result.success(simpleUserInfo);
    }
}
