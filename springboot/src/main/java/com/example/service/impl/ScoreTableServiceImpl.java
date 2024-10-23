package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ScoreTable;
import com.example.service.ScoreTableService;
import com.example.mapper.ScoreTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 天道酬勤hsy
* @description 针对表【score_table】的数据库操作Service实现
* @createDate 2024-10-14 10:56:28
*/
@Service
public class ScoreTableServiceImpl extends ServiceImpl<ScoreTableMapper, ScoreTable>
    implements ScoreTableService{
    @Resource
    private ScoreTableMapper scoreTableMapper;
    public boolean isGameOver(ScoreTable sc){
        int sub=Math.abs(sc.getPlayer1SmallScore()-sc.getPlayer2SmallScore());
        if(sub>2&&Math.max(sc.getPlayer1SmallScore(),sc.getPlayer2SmallScore())>=11){
            return true;
        }
        return false;
    }

    public List<Integer> getPlayerPoints(Integer againstId,String player) {
        if(player.equals("player1"))
            return scoreTableMapper.getP1Points(againstId);
        else if (player.equals("player2")) {
            return scoreTableMapper.getP2Points(againstId);
        }else
            return null;
    }
}




