package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName competition_format
 */
@TableName(value ="competition_format")
public class CompetitionFormat implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "format")
    private String format;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 赛制id
     */ /**
     * 赛制id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 赛制id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 赛制类型
     */ /**
     * 赛制类型
     */
    public String getFormat() {
        return format;
    }

    /**
     * 赛制类型
     */
    public void setFormat(String format) {
        this.format = format;
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
        CompetitionFormat other = (CompetitionFormat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFormat() == null ? other.getFormat() == null : this.getFormat().equals(other.getFormat()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFormat() == null) ? 0 : getFormat().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", format=").append(getFormat());
        sb.append(", serialVersionUID=").append(getSerialVersionUID());
        sb.append("]");
        return sb.toString();
    }
}