package com.example.mapper;

import com.example.entity.ScoreTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 天道酬勤hsy
* @description 针对表【score_table】的数据库操作Mapper
* @createDate 2024-10-14 10:56:28
* @Entity com.example.entity.ScoreTable
*/
public interface ScoreTableMapper extends BaseMapper<ScoreTable> {
    public List<Integer> getP1Points(Integer againstContestId);
    public List<Integer> getP2Points(Integer againstContestId);
}




