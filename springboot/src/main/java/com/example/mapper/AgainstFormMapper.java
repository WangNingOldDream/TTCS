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
/**
 通过继承BaseMapper可以避免在Mapper中手动编写接口方法，只需要通过动态代理的方式使用父类的方法
 */
public interface AgainstFormMapper extends BaseMapper<AgainstForm> {


    int insert(AgainstForm entity);

    int delByAgainstContestId(@Param("againstContestId") Integer againstContestId);

    AgainstForm selectByAgainstContestId(@Param("againstContestId") Integer againstContestId);


    List<AgainstForm> selectAll(AgainstForm againstForm);
}




