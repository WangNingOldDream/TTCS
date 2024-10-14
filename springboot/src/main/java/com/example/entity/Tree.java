package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName tree
 */
@TableName(value ="tree")
public class Tree implements Serializable {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "competition_id")
    private Integer competitionId;

    @TableField(value = "node_order")
    private Integer nodeOrder;

    @TableField(value = "player_id")
    private Integer playerId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * id
     */ /**
     * id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 该对阵树所对应的竞赛的id
     */ /**
     * 该对阵树所对应的竞赛的id
     */
    public Integer getCompetitionId() {
        return competitionId;
    }

    /**
     * 该对阵树所对应的竞赛的id
     */
    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    /**
     * 对阵树中表示玩家的结点的编号，从1开始编号
     */ /**
     * 对阵树中表示玩家的结点的编号，从1开始编号
     */
    public Integer getNodeOrder() {
        return nodeOrder;
    }

    /**
     * 对阵树中表示玩家的结点的编号，从1开始编号
     */
    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    /**
     * 该结点所对应的运动员用户的编号
     */ /**
     * 该结点所对应的运动员用户的编号
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * 该结点所对应的运动员用户的编号
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
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
        Tree other = (Tree) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getNodeOrder() == null ? other.getNodeOrder() == null : this.getNodeOrder().equals(other.getNodeOrder()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getNodeOrder() == null) ? 0 : getNodeOrder().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", competitionId=").append(getCompetitionId());
        sb.append(", nodeOrder=").append(getNodeOrder());
        sb.append(", playerId=").append(getPlayerId());
        sb.append(", serialVersionUID=").append(getSerialVersionUID());
        sb.append("]");
        return sb.toString();
    }
}