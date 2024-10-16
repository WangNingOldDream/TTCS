package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CommonUser;
import com.example.entity.Role;
import com.example.service.RoleService;
import com.example.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 天道酬勤hsy
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-10-14 10:56:28
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

//  查找指定角色的所有用户
    public List<CommonUser> findUserByRole(String role){
        List<CommonUser> commonUsers=null;

        return commonUsers;
    }

}




