package com.xinguang.iot.web.controller.device;

/**
 * Created by jinx on 2017/5/11.
 */
public class DeviceValidReq {
    String companyLogin;
    String apiKey;
    String clientId;

    public String getCompanyLogin() {
        return companyLogin;
    }

    public void setCompanyLogin(String companyLogin) {
        this.companyLogin = companyLogin;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
