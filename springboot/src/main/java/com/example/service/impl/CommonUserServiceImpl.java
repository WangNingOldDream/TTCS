package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CommonUser;
import com.example.service.CommonUserService;
import com.example.mapper.CommonUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 天道酬勤hsy
* @description 针对表【common_user】的数据库操作Service实现
* @createDate 2024-10-14 10:56:27
*/
@Service
public class CommonUserServiceImpl extends ServiceImpl<CommonUserMapper, CommonUser>
    implements CommonUserService{

}




