package com.xinguang.iot.request;

/**
 * Created by jinx on 2017/5/15.
 */
public class DeviceValidRequest {
    private String clientId;
    private String loginName;
    private String apiKey;

    public DeviceValidRequest() {
    }

    public DeviceValidRequest(String clientId, String loginName, String apiKey) {
        this.clientId = clientId;
        this.loginName = loginName;
        this.apiKey = apiKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "DeviceValidRequest{" +
                "clientId='" + clientId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}
