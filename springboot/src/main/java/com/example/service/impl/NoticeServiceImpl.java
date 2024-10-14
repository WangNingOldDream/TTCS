package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Notice;
import com.example.service.NoticeService;
import com.example.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author 天道酬勤hsy
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2024-10-14 10:56:28
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




