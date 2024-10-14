package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * 
 * @TableName common_user
 */
@TableName(value ="common_user")
public class CommonUser implements Serializable {
    @TableId(value = "userID", type = IdType.AUTO)
    private Integer userid;

    @TableField(value = "account_name")
    private String accountName;

    @TableField(value = "account_password")
    private String accountPassword;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_sex")
    private String userSex;

    @TableField(value = "birthday")
    private LocalDate birthday;

    @TableField(value = "birth_place")
    private String birthPlace;

    @TableField(value = "TTC_level")
    private String ttcLevel;

    @TableField(value = "mail")
    private String mail;

    @TableField(value = "avatar")
    private byte[] avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 用户id
     */ /**
     * 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 账户名
     */ /**
     * 账户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 账户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 账户密码
     */ /**
     * 账户密码
     */
    public String getAccountPassword() {
        return accountPassword;
    }

    /**
     * 账户密码
     */
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    /**
     * 用户名
     */ /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户性别
     */ /**
     * 用户性别
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * 用户性别
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * 出生日期
     */ /**
     * 出生日期
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * 籍贯
     */ /**
     * 籍贯
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * 籍贯
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * 乒乓球运动水平等级(四-九级）
     */ /**
     * 乒乓球运动水平等级(四-九级）
     */
    public String getTtcLevel() {
        return ttcLevel;
    }

    /**
     * 乒乓球运动水平等级(四-九级）
     */
    public void setTtcLevel(String ttcLevel) {
        this.ttcLevel = ttcLevel;
    }

    /**
     * 邮箱
     */ /**
     * 邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 邮箱
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 头像图像
     */ /**
     * 头像图像
     */
    public byte[] getAvatar() {
        return avatar;
    }

    /**
     * 头像图像
     */
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
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
        CommonUser other = (CommonUser) that;
        return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getAccountPassword() == null ? other.getAccountPassword() == null : this.getAccountPassword().equals(other.getAccountPassword()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserSex() == null ? other.getUserSex() == null : this.getUserSex().equals(other.getUserSex()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getBirthPlace() == null ? other.getBirthPlace() == null : this.getBirthPlace().equals(other.getBirthPlace()))
            && (this.getTtcLevel() == null ? other.getTtcLevel() == null : this.getTtcLevel().equals(other.getTtcLevel()))
            && (this.getMail() == null ? other.getMail() == null : this.getMail().equals(other.getMail()))
            && (Arrays.equals(this.getAvatar(), other.getAvatar()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getAccountPassword() == null) ? 0 : getAccountPassword().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserSex() == null) ? 0 : getUserSex().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getBirthPlace() == null) ? 0 : getBirthPlace().hashCode());
        result = prime * result + ((getTtcLevel() == null) ? 0 : getTtcLevel().hashCode());
        result = prime * result + ((getMail() == null) ? 0 : getMail().hashCode());
        result = prime * result + (Arrays.hashCode(getAvatar()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(getUserid());
        sb.append(", accountName=").append(getAccountName());
        sb.append(", accountPassword=").append(getAccountPassword());
        sb.append(", userName=").append(getUserName());
        sb.append(", userSex=").append(getUserSex());
        sb.append(", birthday=").append(getBirthday());
        sb.append(", birthPlace=").append(getBirthPlace());
        sb.append(", ttcLevel=").append(getTtcLevel());
        sb.append(", mail=").append(getMail());
        sb.append(", avatar=").append(getAvatar());
        sb.append(", serialVersionUID=").append(getSerialVersionUID());
        sb.append("]");
        return sb.toString();
    }
}