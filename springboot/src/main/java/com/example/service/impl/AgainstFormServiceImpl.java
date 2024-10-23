package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.AgainstForm;
import com.example.entity.Tree;
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

    public boolean isMatchOver(AgainstForm againstForm,Integer roundNum) {
        Integer p1LS= (againstForm.getPlayer1LargeScore()==null)?0:againstForm.getPlayer1LargeScore(),
                p2LS= (againstForm.getPlayer1LargeScore()==null)?0:againstForm.getPlayer2LargeScore();
        if(Math.max(p1LS,p2LS)>roundNum/2){
            return true;
        }
        return false;
    }

    /**
     * 根据对阵表更新对阵树获胜者结点的值
     * @return
     */
    public boolean updateFatherInTree(AgainstForm againstForm,TreeServiceImpl treeService){
        Integer p1LS= (againstForm.getPlayer1LargeScore()==null)?0:againstForm.getPlayer1LargeScore(),
                p2LS= (againstForm.getPlayer1LargeScore()==null)?0:againstForm.getPlayer2LargeScore();
        int treeOrder=0;
        if(p1LS>p2LS){
            treeOrder=againstForm.getOrderInCompetition()*2;
        }else{
            treeOrder=againstForm.getOrderInCompetition()*2+1;
        }
        QueryWrapper<Tree> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("competition_id", againstForm.getCompetitionId());
        queryWrapper.eq("node_order", treeOrder);
        Tree tree = treeService.getOne(queryWrapper);

        QueryWrapper<Tree> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("competition_id", againstForm.getCompetitionId());
        queryWrapper2.eq("node_order", againstForm.getOrderInCompetition());
        Tree fatherTree=treeService.getOne(queryWrapper2);

        fatherTree.setPlayerId(tree.getPlayerId());
        treeService.updateById(fatherTree);
        return true;
    }
}




