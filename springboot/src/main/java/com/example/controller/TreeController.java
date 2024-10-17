package com.example.controller;


import com.example.common.Result;
import com.example.entity.Tree;
import com.example.service.impl.TreeServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {
    @Resource
    private TreeServiceImpl treeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Tree treeNode) {
       // treeService.add(treeNode);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        //treeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //treeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Tree treeNode) {
        treeService.updateById(treeNode);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
//        Tree treeNode = treeService.selectById(id);
//        return Result.success(treeNode);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Tree treeNode ) {
//        List<Tree> list = treeService.selectAll(treeNode);
//        return Result.success(list);
        return Result.success();
    }

    /**
     * 根据role表随机构造tree树
     */
    @GetMapping("/createTree")
    public Result createTree( ) {
//        List<Tree> list = treeService.createTree();
//        return Result.success(list);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Tree treeNode,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
//        PageInfo<Tree> page = treeService.selectPage(treeNode, pageNum, pageSize);
//        return Result.success(page);
        return Result.success();
    }
}
