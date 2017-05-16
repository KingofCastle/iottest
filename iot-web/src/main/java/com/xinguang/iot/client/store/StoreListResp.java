package com.xinguang.iot.client.store;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinx on 2017/4/26.
 */
public class StoreListResp {
    @ApiModelProperty("门店列表")
    private List<StoreItem> stores;

    public List<StoreItem> getStores() {
        return stores;
    }

    public void setStores(List<StoreItem> stores) {
        this.stores = stores;
    }

    public static class StoreItem {
        @ApiModelProperty("门店编码")
        private String code;
        @ApiModelProperty("联系电话")
        private String telephone;
        @ApiModelProperty("门店名称")
        private String name;
        @ApiModelProperty("门店地址")
        private String address;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
