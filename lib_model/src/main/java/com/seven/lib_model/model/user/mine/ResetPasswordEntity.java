package com.seven.lib_model.model.user.mine;

/**
 * Created by xxxxxxH on 2019/4/26.
 */

public class ResetPasswordEntity {
    private String phone;
    private int code;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
