package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.mapper.MessageMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 公告信息表业务处理
 **/
@Service
public class MessageService {

    @Resource
    private MessageMapper messageMapper;

    /**
     * 新增
     */
    public void add(Message message) {
        message.setTime(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        message.setUser(currentUser.getUsername());
        messageMapper.insert(message);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        messageMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            messageMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Message message) {
        messageMapper.updateById(message);
    }

    /**
     * 根据ID查询
     */
    public Message selectById(Integer id) {
        return messageMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Message> selectAll(Message message) {
        return messageMapper.selectAll(message);
    }

    /**
     * 分页查询
     */
    public PageInfo<Message> selectPage(Message message, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageMapper.selectAll(message);
        return PageInfo.of(list);
    }

}