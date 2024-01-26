package com.page.login_page.model;

import lombok.Data;

@Data
public class LoginReq {
    private LoginType loginType;
    private String loginValue; // email or username value
    private String password;

}
