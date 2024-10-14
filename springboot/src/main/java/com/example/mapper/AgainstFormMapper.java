package com.example.mapper;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;

import com.example.entity.AgainstForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 天道酬勤hsy
* @description 针对表【against_form】的数据库操作Mapper
* @createDate 2024-10-14 10:56:27
* @Entity com.example.entity.AgainstForm
*/
public interface AgainstFormMapper extends BaseMapper<AgainstForm> {


    int insert(AgainstForm entity);

    int delByAgainstContestId(@Param("againstContestId") Integer againstContestId);

    AgainstForm selectByAgainstContestId(@Param("againstContestId") Integer againstContestId);
    int updateById(@Param("entity") AgainstForm entity);

    List<AgainstForm> selectAll(AgainstForm againstForm);
}




