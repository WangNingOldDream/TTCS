package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName score_table
 */
@TableName(value ="score_table")
public class ScoreTable implements Serializable {
    @TableId(value = "contest_num_id", type = IdType.AUTO)
    private Integer contestNumId;

    @TableField(value = "against_contest_id")
    private Integer againstContestId;

    @TableField(value = "player1_small_score")
    private Integer player1SmallScore;

    @TableField(value = "player2_small_score")
    private Integer player2SmallScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 比赛场次
     */ /**
     * 比赛场次
     */
    public Integer getContestNumId() {
        return contestNumId;
    }

    /**
     * 比赛场次
     */
    public void setContestNumId(Integer contestNumId) {
        this.contestNumId = contestNumId;
    }

    /**
     *
     */ /**
     * 
     */
    public Integer getAgainstContestId() {
        return againstContestId;
    }

    /**
     * 
     */
    public void setAgainstContestId(Integer againstContestId) {
        this.againstContestId = againstContestId;
    }

    /**
     * 玩家1该场得分
     */ /**
     * 玩家1该场得分
     */
    public Integer getPlayer1SmallScore() {
        return player1SmallScore;
    }

    /**
     * 玩家1该场得分
     */
    public void setPlayer1SmallScore(Integer player1SmallScore) {
        this.player1SmallScore = player1SmallScore;
    }

    /**
     * 玩家2该场得分
     */ /**
     * 玩家2该场得分
     */
    public Integer getPlayer2SmallScore() {
        return player2SmallScore;
    }

    /**
     * 玩家2该场得分
     */
    public void setPlayer2SmallScore(Integer player2SmallScore) {
        this.player2SmallScore = player2SmallScore;
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
        ScoreTable other = (ScoreTable) that;
        return (this.getContestNumId() == null ? other.getContestNumId() == null : this.getContestNumId().equals(other.getContestNumId()))
            && (this.getAgainstContestId() == null ? other.getAgainstContestId() == null : this.getAgainstContestId().equals(other.getAgainstContestId()))
            && (this.getPlayer1SmallScore() == null ? other.getPlayer1SmallScore() == null : this.getPlayer1SmallScore().equals(other.getPlayer1SmallScore()))
            && (this.getPlayer2SmallScore() == null ? other.getPlayer2SmallScore() == null : this.getPlayer2SmallScore().equals(other.getPlayer2SmallScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getContestNumId() == null) ? 0 : getContestNumId().hashCode());
        result = prime * result + ((getAgainstContestId() == null) ? 0 : getAgainstContestId().hashCode());
        result = prime * result + ((getPlayer1SmallScore() == null) ? 0 : getPlayer1SmallScore().hashCode());
        result = prime * result + ((getPlayer2SmallScore() == null) ? 0 : getPlayer2SmallScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", contestNumId=").append(getContestNumId());
        sb.append(", againstContestId=").append(getAgainstContestId());
        sb.append(", player1SmallScore=").append(getPlayer1SmallScore());
        sb.append(", player2SmallScore=").append(getPlayer2SmallScore());
        sb.append(", serialVersionUID=").append(getSerialVersionUID());
        sb.append("]");
        return sb.toString();
    }
}