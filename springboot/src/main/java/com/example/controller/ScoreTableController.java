package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.AgainstForm;
import com.example.entity.ScoreTable;
import com.example.entity.Tree;
import com.example.service.impl.*;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static com.example.common.enums.ResultCodeEnum.*;

@RestController
@RequestMapping("/score")
public class ScoreTableController {
    @Resource
    private ScoreTableServiceImpl scoreTableService;

    @Resource
    private AgainstFormServiceImpl againstFormService;

    @Resource
    private TreeServiceImpl treeService;
    @Resource
    private CompetitionFormatServiceImpl compService;
    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ScoreTable scoreTable) {
        scoreTableService.save(scoreTable);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        scoreTableService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        scoreTableService.removeByIds(ids);
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
        ScoreTable scoreTable = scoreTableService.getById(id);
        return Result.success(scoreTable);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAllByCId/{params}")
    public Result selectAllByCId( @PathVariable String  params ) {
        List<ScoreTable> list=null;
        if(params!=null&&!"".equals(params)){
            QueryWrapper<ScoreTable> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("against_contest_id",params);
            list=scoreTableService.list(queryWrapper);
            return Result.success(list);
        }
        list = scoreTableService.list();
        return Result.success(list);
    }

    /**
     * 小比分大比分的增加、对局是否结束的判断
     */
    @PostMapping("/addScore/{againstId}/{contestNumId}/{player}")
    public Result addScore(@PathVariable Integer againstId, @PathVariable Integer contestNumId,@PathVariable String player) {
        Result result=new Result();
        HashMap<String,Object> data=new HashMap<String,Object>();

        ScoreTable scoreTable = new ScoreTable();
        boolean matchOver=false,gameOver=false;
        if (contestNumId != null) {
            QueryWrapper<ScoreTable> queryWrapper = new QueryWrapper<ScoreTable>();
            queryWrapper.eq("contest_num_id", contestNumId);
            scoreTable = scoreTableService.getOne(queryWrapper);
            if (player != null && !player.isEmpty()) {
                if (player.equals("player1"))
                    scoreTable.setPlayer1SmallScore(scoreTable.getPlayer1SmallScore() + 1);
                else if (player.equals("player2")) {
                    scoreTable.setPlayer2SmallScore(scoreTable.getPlayer2SmallScore() + 1);
                }
            }
            scoreTableService.updateById(scoreTable);
            if (scoreTableService.isGameOver(scoreTable)) {
                gameOver=true;
                AgainstForm againstForm = againstFormService.getById(againstId);
                if (scoreTable.getPlayer1SmallScore() > scoreTable.getPlayer2SmallScore()) {
                    againstForm.setPlayer1LargeScore(againstForm.getPlayer1LargeScore() + 1);
                } else {
                    againstForm.setPlayer2LargeScore(againstForm.getPlayer2LargeScore() + 1);
                }
                againstFormService.updateById(againstForm);
                Integer roundNum = compService.getCompFormat(againstForm.getCompetitionId());
                if (againstFormService.isMatchOver(againstForm, roundNum)) {
                    matchOver=true;
                    againstFormService.updateFatherInTree(againstForm,treeService);
                }
            }

            data.put("小局结束",gameOver);
            data.put("大局结束",matchOver);
            result.setCode(SUCCESS.code);
            if(matchOver)
                result.setMsg("比赛结束");
            else if (gameOver) {
                result.setMsg("对局结束");
            }else
                result.setMsg("加分成功");
            result.setData(data);
            return result;
        }else{
            data.put("小局结束",gameOver);
            data.put("大局结束",matchOver);
            result.setCode(PARAM_LOST_ERROR.code);
            result.setMsg("加分失败");
            result.setData(data);
            return result;
        }
    }
    @GetMapping("/getScore/{againstId}")
    public Result getCurScoreDetails(@PathVariable Integer againstId){
        Result result=new Result();
        HashMap<String,Object> data=new HashMap<String,Object>();

        ScoreTable scoreTable = new ScoreTable();

        if(againstId!=null){
            AgainstForm againstForm=againstFormService.getById(againstId);
            if(againstForm!=null){
                data.put("p1LargeScore",againstForm.getPlayer1LargeScore());
                data.put("p2LargeScore",againstForm.getPlayer2LargeScore());
                List<Integer> p1SmallScore=scoreTableService.getPlayerPoints(againstId,"player1");
                List<Integer> p2SmallScore=scoreTableService.getPlayerPoints(againstId,"player2");
                data.put("p1SmallScore",p1SmallScore);
                data.put("p2SmallScore",p2SmallScore);
                data.put("flag",true);
                result.setCode(SUCCESS.code);
                result.setMsg("成功获取比分信息");
                result.setData(data);
                return result;
            }else{
                return Result.error(PARAM_ERROR);
            }
        }else {
            data.put("flag", false);
            result.setCode(PARAM_LOST_ERROR.code);
            result.setMsg("获取失败");
            result.setData(data);
            return result;
        }
    }
}
