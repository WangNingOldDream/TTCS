package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.AgainstForm;
import com.example.entity.Role;
import com.example.entity.Tree;
import com.example.service.impl.RoleServiceImpl;
import com.example.service.impl.TreeServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/tree")
public class TreeController {
    @Resource
    private TreeServiceImpl treeService;

    @Resource
    private RoleServiceImpl roleService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Tree treeNode) {
       treeService.save(treeNode);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        treeService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        treeService.removeByIds(ids);
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
        Tree treeNode = treeService.getById(id);
        return Result.success(treeNode);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(String params) {
        List<Tree> list=null;
        if(params!=null&&!"".equals(params)){
            QueryWrapper<Tree> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("competition_id",params);
            list=treeService.list(queryWrapper);
            return Result.success(list);
        }
        list = treeService.list();
        return Result.success(list);
    }

    /**
     * 根据role表随机构造tree树
     */
    @PutMapping("/createTree/{compId}")
    public Result createTree(@PathVariable Integer compId ) {
        List<Role> players=roleService.findUserByRole("player",compId);
        int size=players.size();
        ArrayList<Integer> boxes =new ArrayList<>();
        for(int i=size;i<=2*size-1;i++){
            boxes.add(i);
        }
        Random random=new Random();
        //创建根节点
        for(int i=1;i<size;i++){
            Tree tree =new Tree();
            tree.setCompetitionId(compId);
            tree.setNodeOrder(i);
            treeService.save(tree);
        }
        //创建叶子结点
        for(int i=0;i<size;i++){
            // 抽号
            int index=random.nextInt(boxes.size());
            int drawNumber=boxes.get(index);
            Tree tree =new Tree();
            tree.setCompetitionId(compId);
            tree.setPlayerId(players.get(i).getUserId());
            tree.setNodeOrder(drawNumber);
            treeService.save(tree);
            boxes.remove(index);
        }
        return Result.success();
    }
    @GetMapping("/selectFatherNode/{cid}/{ord}")
    public Result selectFatherNode(@PathVariable Integer cid,@PathVariable Integer ord) {
        Tree tree=null;
        if(cid!=null&&ord!=null) {
            QueryWrapper<Tree> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("competition_id", cid);
            queryWrapper.eq("node_order", ord/2);
            tree = treeService.getOne(queryWrapper);
            return Result.success(tree);
        }
        return Result.success(tree);
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
