package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Tree;
import com.example.service.TreeService;
import com.example.mapper.TreeMapper;
import org.springframework.stereotype.Service;

/**
* @author 天道酬勤hsy
* @description 针对表【tree】的数据库操作Service实现
* @createDate 2024-10-14 10:56:28
*/
@Service
public class TreeServiceImpl extends ServiceImpl<TreeMapper, Tree>
    implements TreeService{

}




