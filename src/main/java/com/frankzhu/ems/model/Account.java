package com.frankzhu.ems.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("账户类")
public class Account {
    @ApiModelProperty("账户名（电话）")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("账户类别")
    private String identity;

    public Account(String username, String password, String identity){
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }
}
