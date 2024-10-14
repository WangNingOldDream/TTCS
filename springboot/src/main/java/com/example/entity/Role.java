package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName role
 */
@TableName(value ="role")
public class Role implements Serializable {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "competition_id")
    private Integer competitionId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "role_in_competition")
    private String roleInCompetition;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     *
     */ /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     */ /**
     * 
     */
    public Integer getCompetitionId() {
        return competitionId;
    }

    /**
     * 
     */
    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    /**
     *
     */ /**
     * 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * user在赛事中的角色 player、referee、manager
     */ /**
     * user在赛事中的角色 player、referee、manager
     */
    public String getRoleInCompetition() {
        return roleInCompetition;
    }

    /**
     * user在赛事中的角色 player、referee、manager
     */
    public void setRoleInCompetition(String roleInCompetition) {
        this.roleInCompetition = roleInCompetition;
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
        Role other = (Role) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRoleInCompetition() == null ? other.getRoleInCompetition() == null : this.getRoleInCompetition().equals(other.getRoleInCompetition()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRoleInCompetition() == null) ? 0 : getRoleInCompetition().hashCode());
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
        sb.append(", userId=").append(getUserId());
        sb.append(", roleInCompetition=").append(getRoleInCompetition());
        sb.append(", serialVersionUID=").append(getSerialVersionUID());
        sb.append("]");
        return sb.toString();
    }
}