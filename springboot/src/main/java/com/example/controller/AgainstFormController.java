package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.AgainstForm;
import com.example.entity.Role;
import com.example.service.impl.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static com.example.common.enums.ResultCodeEnum.SUCCESS;

@RestController
@RequestMapping("/against")
public class AgainstFormController {
    @Resource
    private AgainstFormServiceImpl againstFormService;


    @Resource
    private TreeServiceImpl treeService;
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    private CompetitionFormatServiceImpl compFormatService;
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
        againstFormService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        againstFormService.removeByIds(ids);
        return Result.success();
    }



    @PutMapping("/update")
    public Result updateById(@RequestBody AgainstForm againstForm) {
        againstFormService.updateById(againstForm);
        return Result.success();
    }

    @PutMapping("/win")
    public Result updateFatherNode(@RequestBody AgainstForm againstForm) {
        againstForm = againstFormService.getById(againstForm.getAgainstContestId());
        if(againstFormService.isMatchOver(againstForm, compFormatService.getCompFormat(againstForm.getCompetitionId()))){//七局四胜
            againstFormService.updateFatherInTree(againstForm,treeService);
        }
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
        HashMap<String,Object> data=new HashMap<String,Object>();
        Result result=new Result();
        if(params!=null&&!"".equals(params)){
            QueryWrapper<AgainstForm> queryWrapper=new QueryWrapper<>();
            queryWrapper.like("competition_id",params);
            queryWrapper.or();
            queryWrapper.like("against_contest_id",params);
            list = againstFormService.list(queryWrapper);
            data.put("data",list);
            data.put("flag",true);
            result.setCode(SUCCESS.code);
            result.setMsg("查找指定赛事的对阵信息成功");
            result.setData(data);
            return result;
        }
        list = againstFormService.list();
        data.put("data",list);
        data.put("flag",true);
        result.setCode(SUCCESS.code);
        result.setMsg("查找出所有的对阵信息");
        result.setData(data);
        return result;
    }


    @GetMapping("/selectByCompetitionIdAndOrder/{cid}/{ord}")
    public Result selectByCompetitionIdAndOrder(@PathVariable Integer cid,@PathVariable Integer ord) {
        AgainstForm againstForm=null;
        if(cid!=null&&ord!=null) {
            QueryWrapper<AgainstForm> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("competition_id", cid);
            queryWrapper.eq("order_in_competition", ord);
            againstForm = againstFormService.getOne(queryWrapper);
            return Result.success(againstForm);
        }
        return Result.success(againstForm);
    }
    /**
     * 根据role报名表生成对阵表
     * @param id
     * @return
     */
    @PutMapping("/createAgainstForm/{id}")
    public Result createAgainstForm(@PathVariable Integer id) {
        List<Role> referees=roleService.findUserByRole("referee",id);
        List<Role> players=roleService.findUserByRole("player",id);
        for(int i=0;i<players.size()-1;i++){
            AgainstForm againstForm=new AgainstForm();
            againstForm.setCompetitionId(id);
            againstForm.setOrderInCompetition(i+1);
            againstForm.setAgainstState("未开始");
            againstForm.setRefereeId(referees.get(i % referees.size()).getUserId());
            againstFormService.save(againstForm);
        }
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
