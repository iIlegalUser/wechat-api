package com.example.user.entity.bo;

import lombok.Data;

@Data
public class LoginBO {
    private String sessionkey;
    private String unionid;
    private String openid;
    private String errcode;
    private String errmsg;
}
