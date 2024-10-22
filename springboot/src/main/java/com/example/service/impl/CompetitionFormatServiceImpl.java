package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CompetitionFormat;
import com.example.service.CompetitionFormatService;
import com.example.mapper.CompetitionFormatMapper;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

/**
* @author 天道酬勤hsy
* @description 针对表【competition_format】的数据库操作Service实现
* @createDate 2024-10-14 10:56:28
*/
@Service
public class CompetitionFormatServiceImpl extends ServiceImpl<CompetitionFormatMapper, CompetitionFormat>
    implements CompetitionFormatService{

    public Integer getCompFormat(Integer id) {
        CompetitionFormat cf=getById(id);
        if(cf.getFormat()!=null&&!cf.getFormat().isEmpty()){
            if(cf.getFormat().equals("七局四胜"))
                return 7;
            if(cf.getFormat().equals("五局三胜"))
                return 5;
        }
        return 7;
    }
}




