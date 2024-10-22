package com.example.mapper;

import com.example.entity.AgainstForm;
import com.example.entity.CommonUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.SimpleUserInfo;
import com.example.service.impl.CommonUserServiceImpl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 天道酬勤hsy
* @description 针对表【common_user】的数据库操作Mapper
* @createDate 2024-10-14 10:56:28
* @Entity com.example.entity.CommonUser
*/
public interface CommonUserMapper extends BaseMapper<CommonUser> {
    List<SimpleUserInfo> selectAllInComp(@Param("compId") Integer compId);
}




