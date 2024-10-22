package com.example.entity;

import lombok.Data;

@Data
public class SimpleUserInfo {
    Integer uid;
    String accountName;
    byte[] avatar;
    SimpleUserInfo(Integer Uid,String account_name,byte[] avatar){
        uid=Uid;
        accountName= account_name;
        this.avatar=avatar;
    }
}
