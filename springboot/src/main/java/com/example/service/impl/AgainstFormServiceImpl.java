package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.AgainstForm;
import com.example.service.AgainstFormService;
import com.example.mapper.AgainstFormMapper;
import org.springframework.stereotype.Service;

/**
* @author 天道酬勤hsy
* @description 针对表【against_form】的数据库操作Service实现
* @createDate 2024-10-14 10:56:27
*/

/*
通过继承ServiceImpl(CommonUserMapper,CommonUser)避免手动实现接口
 */
@Service
public class AgainstFormServiceImpl extends ServiceImpl<AgainstFormMapper, AgainstForm>
    implements AgainstFormService{

}




