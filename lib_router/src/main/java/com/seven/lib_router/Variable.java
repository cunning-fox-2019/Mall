package com.seven.lib_router;


import com.seven.lib_opensource.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @auhtor seven
 * email:frankchenlei@163.com
 * 2019/3/12
 */

public class Variable {

    private static Variable instance;

    public Variable(){
        eventHttpCode=10001;
    }

    public static Variable getInstance() {
        if (instance == null) {
            synchronized (Variable.class) {
                if (instance == null) {
                    instance = new Variable();
                }
            }
        }

        return instance;
    }

    private int eventHttpCode;

    /**
     * 网络请求的拦截器消息
     */
    private void eventHttp(){
        EventBus.getDefault().post(new MessageEvent(eventHttpCode, ""));
    }

    private String token;
    private String uuid;
    private String language;
    private String brandId;
    private String memberId;

    public int getEventHttpCode() {
        return eventHttpCode;
    }

    public void setEventHttpCode(int eventHttpCode) {
        this.eventHttpCode = eventHttpCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        eventHttp();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
        eventHttp();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        eventHttp();
    }

    public static void setInstance(Variable instance) {
        Variable.instance = instance;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
