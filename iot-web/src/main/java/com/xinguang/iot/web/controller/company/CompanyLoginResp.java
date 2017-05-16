package com.xinguang.iot.web.controller.company;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.web.domain.AccessToken;

/**
 * Created by jinx on 2017/5/3.
 */
public class CompanyLoginResp {
    @ApiModelProperty("企业")
    private CompanyParma company;

    private AccessToken accessToken;

    public CompanyParma getCompany() {
        return company;
    }

    public void setCompany(CompanyParma company) {
        this.company = company;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }


    public static class CompanyParma {
        @ApiModelProperty("企业ID")
        private String companyId;
        @ApiModelProperty("企业编号")
        private String companyCode;
        @ApiModelProperty("企业名称")
        private String companyName;// '企业名称

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}
