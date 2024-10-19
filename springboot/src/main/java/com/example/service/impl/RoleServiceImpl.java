package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.entity.AgainstForm;
import com.example.entity.CommonUser;
import com.example.entity.Role;
import com.example.service.RoleService;
import com.example.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
* @author 天道酬勤hsy
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-10-14 10:56:28
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

//  查找指定角色的所有用户
    public List<Role> findUserByRole(String role,Integer competitonId){
        List<Role> list=null;
        if(role!=null&&!"".equals(role)){
            QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
            queryWrapper.like("role_in_competition",role);
            queryWrapper.eq("competition_id",competitonId);
            list=list(queryWrapper);
            return list;
        }
        list = list();
        return list;
    }

}




