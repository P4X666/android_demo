package com.example.logindemo.bean;

public class LoginBean {

    private String msg;
    private String code;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginBean(String msg, String code, String token) {
        this.msg = msg;
        this.code = code;
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", token='" + token + '\'' +
                '}';
    }
}
