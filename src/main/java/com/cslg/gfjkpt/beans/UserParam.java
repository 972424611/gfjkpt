package com.cslg.gfjkpt.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class UserParam {

    private int id;

    @NotBlank(message = "账号不能为空")
    @Length(min = 1, max = 32, message = "用户名长度须在32个字以内")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 12, message = "密码长度须在4-12个字符")
    private String password;

    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
