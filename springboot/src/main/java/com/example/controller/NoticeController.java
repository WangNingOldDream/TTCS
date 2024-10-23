package com.example.controller;

import com.example.common.Result;
import com.example.entity.Message;
import com.example.entity.Notice;
import com.example.service.impl.MessageService;
import com.example.service.impl.NoticeServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeServiceImpl noticeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        //noticeService.add(notice);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        //noticeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //noticeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
//        Notice notice = noticeService.selectById(id);
//        return Result.success(notice);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice) {
//        List<Notice> list = noticeService.selectAll(notice);
//        return Result.success(list);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Notice notice,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
//        PageInfo<Notice> page = noticeService.selectPage(notice, pageNum, pageSize);
//        return Result.success(page);
        return Result.success();
    }


    /**
     * 获取所有新闻公告(ax)
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Notice> notices = noticeService.list();
        return Result.success("获取所有公告成功", notices);
    }
}
