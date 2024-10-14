package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 
 * @TableName competition
 */
@TableName(value ="competition")
public class Competition implements Serializable {
    /**
     * 赛事id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 赛事名称
     */
    @TableField(value = "competition_name")
    private String competitionName;

    /**
     * 举办时间
     */
    @TableField(value = "hold_time")
    private LocalDateTime holdTime;

    /**
     * 举办地点
     */
    @TableField(value = "hold_place")
    private String holdPlace;

    /**
     * 赛制 七局四胜、五局三胜
     */
    @TableField(value = "competition_format")
    private String competitionFormat;

    /**
     * 是否允许观赛
     */
    @TableField(value = "allow_watch")
    private Integer allowWatch;

    /**
     * 是否允许报名
     */
    @TableField(value = "allow_register")
    private Integer allowRegister;

    /**
     * 最小参赛人数
     */
    @TableField(value = "min_player_num")
    private Integer minPlayerNum;

    /**
     * 最大参赛人数
     */
    @TableField(value = "max_player_num")
    private Integer maxPlayerNum;

    /**
     * 允许参赛最低年龄
     */
    @TableField(value = "min_player_age")
    private Integer minPlayerAge;

    /**
     * 允许参赛最大年龄
     */
    @TableField(value = "max_player_age")
    private Integer maxPlayerAge;

    /**
     * 允许参赛最低运动员水平等级(四级到九级）
     */
    @TableField(value = "min_TTC_level")
    private String minTtcLevel;

    /**
     * 允许参赛最大运动员水平等级
     */
    @TableField(value = "max_TTC_level")
    private String maxTtcLevel;

    /**
     * 限制参赛运动员性别
     */
    @TableField(value = "sex_limit")
    private String sexLimit;

    /**
     * 比赛所处状态，准备、进行、结束
     */
    @TableField(value = "competition_state")
    private String competitionState;

    /**
     * 赛事封面
     */
    @TableField(value = "cover")
    private byte[] cover;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 赛事id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 赛事id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 赛事名称
     */
    public String getCompetitionName() {
        return competitionName;
    }

    /**
     * 赛事名称
     */
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    /**
     * 举办时间
     */
    public LocalDateTime getHoldTime() {
        return holdTime;
    }

    /**
     * 举办时间
     */
    public void setHoldTime(LocalDateTime holdTime) {
        this.holdTime = holdTime;
    }

    /**
     * 举办地点
     */
    public String getHoldPlace() {
        return holdPlace;
    }

    /**
     * 举办地点
     */
    public void setHoldPlace(String holdPlace) {
        this.holdPlace = holdPlace;
    }

    /**
     * 赛制 七局四胜、五局三胜
     */
    public String getCompetitionFormat() {
        return competitionFormat;
    }

    /**
     * 赛制 七局四胜、五局三胜
     */
    public void setCompetitionFormat(String competitionFormat) {
        this.competitionFormat = competitionFormat;
    }

    /**
     * 是否允许观赛
     */
    public Integer getAllowWatch() {
        return allowWatch;
    }

    /**
     * 是否允许观赛
     */
    public void setAllowWatch(Integer allowWatch) {
        this.allowWatch = allowWatch;
    }

    /**
     * 是否允许报名
     */
    public Integer getAllowRegister() {
        return allowRegister;
    }

    /**
     * 是否允许报名
     */
    public void setAllowRegister(Integer allowRegister) {
        this.allowRegister = allowRegister;
    }

    /**
     * 最小参赛人数
     */
    public Integer getMinPlayerNum() {
        return minPlayerNum;
    }

    /**
     * 最小参赛人数
     */
    public void setMinPlayerNum(Integer minPlayerNum) {
        this.minPlayerNum = minPlayerNum;
    }

    /**
     * 最大参赛人数
     */
    public Integer getMaxPlayerNum() {
        return maxPlayerNum;
    }

    /**
     * 最大参赛人数
     */
    public void setMaxPlayerNum(Integer maxPlayerNum) {
        this.maxPlayerNum = maxPlayerNum;
    }

    /**
     * 允许参赛最低年龄
     */
    public Integer getMinPlayerAge() {
        return minPlayerAge;
    }

    /**
     * 允许参赛最低年龄
     */
    public void setMinPlayerAge(Integer minPlayerAge) {
        this.minPlayerAge = minPlayerAge;
    }

    /**
     * 允许参赛最大年龄
     */
    public Integer getMaxPlayerAge() {
        return maxPlayerAge;
    }

    /**
     * 允许参赛最大年龄
     */
    public void setMaxPlayerAge(Integer maxPlayerAge) {
        this.maxPlayerAge = maxPlayerAge;
    }

    /**
     * 允许参赛最低运动员水平等级(四级到九级）
     */
    public String getMinTtcLevel() {
        return minTtcLevel;
    }

    /**
     * 允许参赛最低运动员水平等级(四级到九级）
     */
    public void setMinTtcLevel(String minTtcLevel) {
        this.minTtcLevel = minTtcLevel;
    }

    /**
     * 允许参赛最大运动员水平等级
     */
    public String getMaxTtcLevel() {
        return maxTtcLevel;
    }

    /**
     * 允许参赛最大运动员水平等级
     */
    public void setMaxTtcLevel(String maxTtcLevel) {
        this.maxTtcLevel = maxTtcLevel;
    }

    /**
     * 限制参赛运动员性别
     */
    public String getSexLimit() {
        return sexLimit;
    }

    /**
     * 限制参赛运动员性别
     */
    public void setSexLimit(String sexLimit) {
        this.sexLimit = sexLimit;
    }

    /**
     * 比赛所处状态，准备、进行、结束
     */
    public String getCompetitionState() {
        return competitionState;
    }

    /**
     * 比赛所处状态，准备、进行、结束
     */
    public void setCompetitionState(String competitionState) {
        this.competitionState = competitionState;
    }

    /**
     * 赛事封面
     */
    public byte[] getCover() {
        return cover;
    }

    /**
     * 赛事封面
     */
    public void setCover(byte[] cover) {
        this.cover = cover;
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
        Competition other = (Competition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompetitionName() == null ? other.getCompetitionName() == null : this.getCompetitionName().equals(other.getCompetitionName()))
            && (this.getHoldTime() == null ? other.getHoldTime() == null : this.getHoldTime().equals(other.getHoldTime()))
            && (this.getHoldPlace() == null ? other.getHoldPlace() == null : this.getHoldPlace().equals(other.getHoldPlace()))
            && (this.getCompetitionFormat() == null ? other.getCompetitionFormat() == null : this.getCompetitionFormat().equals(other.getCompetitionFormat()))
            && (this.getAllowWatch() == null ? other.getAllowWatch() == null : this.getAllowWatch().equals(other.getAllowWatch()))
            && (this.getAllowRegister() == null ? other.getAllowRegister() == null : this.getAllowRegister().equals(other.getAllowRegister()))
            && (this.getMinPlayerNum() == null ? other.getMinPlayerNum() == null : this.getMinPlayerNum().equals(other.getMinPlayerNum()))
            && (this.getMaxPlayerNum() == null ? other.getMaxPlayerNum() == null : this.getMaxPlayerNum().equals(other.getMaxPlayerNum()))
            && (this.getMinPlayerAge() == null ? other.getMinPlayerAge() == null : this.getMinPlayerAge().equals(other.getMinPlayerAge()))
            && (this.getMaxPlayerAge() == null ? other.getMaxPlayerAge() == null : this.getMaxPlayerAge().equals(other.getMaxPlayerAge()))
            && (this.getMinTtcLevel() == null ? other.getMinTtcLevel() == null : this.getMinTtcLevel().equals(other.getMinTtcLevel()))
            && (this.getMaxTtcLevel() == null ? other.getMaxTtcLevel() == null : this.getMaxTtcLevel().equals(other.getMaxTtcLevel()))
            && (this.getSexLimit() == null ? other.getSexLimit() == null : this.getSexLimit().equals(other.getSexLimit()))
            && (this.getCompetitionState() == null ? other.getCompetitionState() == null : this.getCompetitionState().equals(other.getCompetitionState()))
            && (Arrays.equals(this.getCover(), other.getCover()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompetitionName() == null) ? 0 : getCompetitionName().hashCode());
        result = prime * result + ((getHoldTime() == null) ? 0 : getHoldTime().hashCode());
        result = prime * result + ((getHoldPlace() == null) ? 0 : getHoldPlace().hashCode());
        result = prime * result + ((getCompetitionFormat() == null) ? 0 : getCompetitionFormat().hashCode());
        result = prime * result + ((getAllowWatch() == null) ? 0 : getAllowWatch().hashCode());
        result = prime * result + ((getAllowRegister() == null) ? 0 : getAllowRegister().hashCode());
        result = prime * result + ((getMinPlayerNum() == null) ? 0 : getMinPlayerNum().hashCode());
        result = prime * result + ((getMaxPlayerNum() == null) ? 0 : getMaxPlayerNum().hashCode());
        result = prime * result + ((getMinPlayerAge() == null) ? 0 : getMinPlayerAge().hashCode());
        result = prime * result + ((getMaxPlayerAge() == null) ? 0 : getMaxPlayerAge().hashCode());
        result = prime * result + ((getMinTtcLevel() == null) ? 0 : getMinTtcLevel().hashCode());
        result = prime * result + ((getMaxTtcLevel() == null) ? 0 : getMaxTtcLevel().hashCode());
        result = prime * result + ((getSexLimit() == null) ? 0 : getSexLimit().hashCode());
        result = prime * result + ((getCompetitionState() == null) ? 0 : getCompetitionState().hashCode());
        result = prime * result + (Arrays.hashCode(getCover()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", competitionName=").append(getCompetitionName());
        sb.append(", holdTime=").append(getHoldTime());
        sb.append(", holdPlace=").append(getHoldPlace());
        sb.append(", competitionFormat=").append(getCompetitionFormat());
        sb.append(", allowWatch=").append(getAllowWatch());
        sb.append(", allowRegister=").append(getAllowRegister());
        sb.append(", minPlayerNum=").append(getMinPlayerNum());
        sb.append(", maxPlayerNum=").append(getMaxPlayerNum());
        sb.append(", minPlayerAge=").append(getMinPlayerAge());
        sb.append(", maxPlayerAge=").append(getMaxPlayerAge());
        sb.append(", minTtcLevel=").append(getMinTtcLevel());
        sb.append(", maxTtcLevel=").append(getMaxTtcLevel());
        sb.append(", sexLimit=").append(getSexLimit());
        sb.append(", competitionState=").append(getCompetitionState());
        sb.append(", cover=").append(getCover());
        sb.append(", serialVersionUID=").append(getSerialVersionUID());
        sb.append("]");
        return sb.toString();
    }
}