package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

import java.io.Serializable;

/**
 * 
 * @TableName against_form
 */

/*
@Data注解生成setter\getter
 */
@Data
@TableName(value ="against_form")
public class AgainstForm implements Serializable {
    /**
     * 选手对阵表id,tableId表示为自增主键，在传参过程中不需要此字段
     */
    @TableId(type = IdType.AUTO,value = "against_contest_id")
    private Integer againstContestId;

    /**
     * 比赛id
     */
    @TableField(value = "competition_id")
    private Integer competitionId;

    /**
     * 对阵树中表示对局的节点编号
     */
    @TableField(value = "order_in_competition")
    private Integer orderInCompetition;

    /**
     * 裁判id
     */
    @TableField(value = "referee_id")
    private Integer refereeId;

    /**
     * 单场比赛状态  未开始，进行中，结束
     */
    @TableField(value = "against_state")
    private String againstState;

    /**
     * 总场次
     */
    @TableField(value = "total_contest_num")
    private String totalContestNum;

    /**
     * 玩家1胜场
     */
    @TableField(value = "player1_large_score")
    private Integer player1LargeScore;

    /**
     * 玩家2胜场
     */
    @TableField(value = "player2_large_score")
    private Integer player2LargeScore;

    /**
     * 该场对局回放
     */
    @TableField(value = "record")
    private String record;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 选手对阵表id
     */
    public Integer getAgainstContestId() {
        return againstContestId;
    }

    /**
     * 选手对阵表id
     */
    public void setAgainstContestId(Integer againstContestId) {
        this.againstContestId = againstContestId;
    }

    /**
     * 比赛id
     */
    public Integer getCompetitionId() {
        return competitionId;
    }

    /**
     * 比赛id
     */
    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    /**
     * 对阵树中表示对局的节点编号
     */
    public Integer getOrderInCompetition() {
        return orderInCompetition;
    }

    /**
     * 对阵树中表示对局的节点编号
     */
    public void setOrderInCompetition(Integer orderInCompetition) {
        this.orderInCompetition = orderInCompetition;
    }

    /**
     * 裁判id
     */
    public Integer getRefereeId() {
        return refereeId;
    }

    /**
     * 裁判id
     */
    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

    /**
     * 单场比赛状态  未开始，进行中，结束
     */
    public String getAgainstState() {
        return againstState;
    }

    /**
     * 单场比赛状态  未开始，进行中，结束
     */
    public void setAgainstState(String againstState) {
        this.againstState = againstState;
    }

    /**
     * 总场次
     */
    public String getTotalContestNum() {
        return totalContestNum;
    }

    /**
     * 总场次
     */
    public void setTotalContestNum(String totalContestNum) {
        this.totalContestNum = totalContestNum;
    }

    /**
     * 玩家1胜场
     */
    public Integer getPlayer1LargeScore() {
        return player1LargeScore;
    }

    /**
     * 玩家1胜场
     */
    public void setPlayer1LargeScore(Integer player1LargeScore) {
        this.player1LargeScore = player1LargeScore;
    }

    /**
     * 玩家2胜场
     */
    public Integer getPlayer2LargeScore() {
        return player2LargeScore;
    }

    /**
     * 玩家2胜场
     */
    public void setPlayer2LargeScore(Integer player2LargeScore) {
        this.player2LargeScore = player2LargeScore;
    }

    /**
     * 该场对局回放
     */
    public String getRecord() {
        return record;
    }

    /**
     * 该场对局回放
     */
    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AgainstForm other = (AgainstForm) that;
        return (this.getAgainstContestId() == null ? other.getAgainstContestId() == null : this.getAgainstContestId().equals(other.getAgainstContestId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getOrderInCompetition() == null ? other.getOrderInCompetition() == null : this.getOrderInCompetition().equals(other.getOrderInCompetition()))
            && (this.getRefereeId() == null ? other.getRefereeId() == null : this.getRefereeId().equals(other.getRefereeId()))
            && (this.getAgainstState() == null ? other.getAgainstState() == null : this.getAgainstState().equals(other.getAgainstState()))
            && (this.getTotalContestNum() == null ? other.getTotalContestNum() == null : this.getTotalContestNum().equals(other.getTotalContestNum()))
            && (this.getPlayer1LargeScore() == null ? other.getPlayer1LargeScore() == null : this.getPlayer1LargeScore().equals(other.getPlayer1LargeScore()))
            && (this.getPlayer2LargeScore() == null ? other.getPlayer2LargeScore() == null : this.getPlayer2LargeScore().equals(other.getPlayer2LargeScore()))
            && (this.getRecord() == null ? other.getRecord() == null : this.getRecord().equals(other.getRecord()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAgainstContestId() == null) ? 0 : getAgainstContestId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getOrderInCompetition() == null) ? 0 : getOrderInCompetition().hashCode());
        result = prime * result + ((getRefereeId() == null) ? 0 : getRefereeId().hashCode());
        result = prime * result + ((getAgainstState() == null) ? 0 : getAgainstState().hashCode());
        result = prime * result + ((getTotalContestNum() == null) ? 0 : getTotalContestNum().hashCode());
        result = prime * result + ((getPlayer1LargeScore() == null) ? 0 : getPlayer1LargeScore().hashCode());
        result = prime * result + ((getPlayer2LargeScore() == null) ? 0 : getPlayer2LargeScore().hashCode());
        result = prime * result + ((getRecord() == null) ? 0 : getRecord().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", againstContestId=").append(againstContestId);
        sb.append(", competitionId=").append(competitionId);
        sb.append(", orderInCompetition=").append(orderInCompetition);
        sb.append(", refereeId=").append(refereeId);
        sb.append(", againstState=").append(againstState);
        sb.append(", totalContestNum=").append(totalContestNum);
        sb.append(", player1LargeScore=").append(player1LargeScore);
        sb.append(", player2LargeScore=").append(player2LargeScore);
        sb.append(", record=").append(record);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}