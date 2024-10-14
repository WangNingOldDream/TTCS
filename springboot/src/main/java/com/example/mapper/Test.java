package com.example.mapper;

import com.example.entity.AgainstForm;
import com.example.mapper.AgainstFormMapper;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    测试类 编写的Mapper的相关接口需要进行测试
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MapperInterfaceTest {
    @Autowired
    private AgainstFormMapper againstFormMapper;
    @Test
    void selectById(){
        AgainstForm againstFormList=againstFormMapper.selectByAgainstContestId(1);
        System.out.println("selectById");
        System.out.println("againstFormList = " + againstFormList);
    }

    @Test
    void insert(){
        AgainstForm againstForm=againstFormMapper.selectByAgainstContestId(1);
        againstForm.setAgainstContestId(2);
        againstForm.setRecord("japan");
        againstFormMapper.insert(againstForm);
    }
    @Test
    void selectAll(){
        List<AgainstForm> againstFormList=againstFormMapper.selectAll(new AgainstForm());
    }
    @Test
    void deleteById(){
        againstFormMapper.delByAgainstContestId(2);
    }
}
