package com.seven.lib_model.model.user;



public class UserEntity {

    /**
     * id : 1
     * sex : male
     * avatar : xxx
     * username : xxx
     * phone : xxxxxxxxxxx
     * role : 1
     * is_set_pay_password : 1
     * invite_code : xxxxxx
     */

    private int id;
    private String sex;
    private String avatar;
    private String username;
    private String phone;
    private int role;
    private int is_set_pay_password;
    private String invite_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getIs_set_pay_password() {
        return is_set_pay_password;
    }

    public void setIs_set_pay_password(int is_set_pay_password) {
        this.is_set_pay_password = is_set_pay_password;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
