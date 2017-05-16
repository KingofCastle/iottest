package com.xinguang.iot.client.store;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysStore;
import com.xinguang.iot.service.StoreService;
import com.xinguang.iot.ClientResp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinx on 2017/4/7.
 * 设备添加
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseClientController {


    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增门店", notes = "新增门店", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> add(@ApiParam(value = "JSON格式的门店实体", required = true) @RequestBody StoreAddReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysStore store = new SysStore();
        if(StringUtils.isNotBlank(req.getCompanyId())){
            store.setCompanyId(req.getCompanyId());
        }
        if(StringUtils.isNotBlank(req.getName())){
            store.setName(req.getName());
        }
        if(StringUtils.isNotBlank(req.getIntroduction())){
            store.setIntroduction(req.getIntroduction());
        }
        if(StringUtils.isNotBlank(req.getTelephone())){
            store.setTelephone(req.getTelephone());
        }
        if(StringUtils.isNotBlank(req.getMaster())){
            store.setMaster(req.getMaster());
        }
        if(StringUtils.isNotBlank(req.getAddress())){
            store.setAddress(req.getAddress());
        }
        String storeCode = "1111";//TODO
        store.setCode(storeCode);
        try {
            storeService.saveStore(store);
            resp.setResultData(RespYOrN.YES);
        } catch (Exception e) {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
            resp.setResultDesc(e.getMessage());
            resp.setResultData(RespYOrN.NO);
        }

        return resp;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除门店", notes = "删除门店", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> delete(@ApiParam(value = "门店编号", required = true) @RequestBody StoreDeleteReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
//        SysStore store =  storeService.findStoreByCode(req.getStoreCode());
//        try {
//            storeService.deleteStore(store);
//        } catch (Exception e) {
//            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
//            resp.setResultDesc(e.getMessage());
//            resp.setResultData(RespYOrN.NO);
//        }
//        resp.setResultData(RespYOrN.YES);
        return resp;
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看门店", notes = "查看门店", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<StoreDetailResp> detail(@ApiParam(value = "门店编号", required = true) @RequestBody StoreDetailReq req, HttpServletRequest request) {
        ClientResp<StoreDetailResp> resp = new ClientResp<>();
        SysStore store =null;
        if(StringUtils.isNotBlank(req.getStoreCode())){
            store =  storeService.findStoreByCode(req.getStoreCode());
        }
        StoreDetailResp storeDetailResp = new StoreDetailResp();
        if (store!=null){
            storeDetailResp.setAddress(store.getAddress());
            storeDetailResp.setCompanyId(store.getCompanyId());
            storeDetailResp.setName(store.getName());
            storeDetailResp.setIntroduction(store.getIntroduction());
            storeDetailResp.setTelephone(store.getTelephone());
            storeDetailResp.setMaster(store.getMaster());
        }
        resp.setResultData(storeDetailResp);
        return resp;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改门店", notes = "修改门店", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "JSON格式的门店实体", required = true) @RequestBody StoreEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysStore store =  storeService.findStoreById(req.getStoreId());
        if(store!=null){
            if (StringUtils.isNotBlank(req.getName()))
                store.setName(req.getName());
            if (StringUtils.isNotBlank(req.getTelephone()))
                store.setTelephone(req.getTelephone());
            if (StringUtils.isNotBlank(req.getIntroduction()))
                store.setIntroduction(req.getIntroduction());
            if (StringUtils.isNotBlank(req.getAddress()))
                store.setAddress(req.getAddress());

            if (StringUtils.isNotBlank(req.getMaster()))
                store.setMaster(req.getMaster());
            if (StringUtils.isNotBlank(req.getCompanyId()))
                store.setCompanyId(req.getCompanyId());
            storeService.updateStore(store);
            resp.setResultData(RespYOrN.YES);
        }else {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
            resp.setResultDesc("修改传感器失败");
            resp.setResultData(RespYOrN.NO);
        }
        return resp;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "罗列门店", notes = "罗列门店", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<StoreListResp> list(@ApiParam(value = "请求参数", required = true) @RequestBody StoreListReq req, HttpServletRequest request) {
        ClientResp<StoreListResp> resp = new ClientResp<>();
        resp.setResultData(null);
        PageWrap<SysStore> page = new PageWrap<>(req.getPageNum(), req.getPageSize(),null);
        List<SysStore> storeList =  storeService.find(page);
        if (CollectionUtils.isNotEmpty(storeList)){
            StoreListResp storeListResp = new StoreListResp();
            List<StoreListResp.StoreItem> storeItems = new ArrayList<>();
            for(int i=0;i<storeList.size();i++){
                StoreListResp.StoreItem item = new StoreListResp.StoreItem();
                item.setName(storeList.get(i).getName());
                item.setCode(storeList.get(i).getCode());
                item.setAddress(storeList.get(i).getAddress());
                item.setTelephone(storeList.get(i).getTelephone());
                storeItems.add(item);
            }
            storeListResp.setStores(storeItems);
            resp.setResultData(storeListResp);
            return resp;
        }
        return resp;
    }


//    public static class Resp {
//        public static Resp YES = new Resp(true);
//        public static Resp NO = new Resp(false);
//        @ApiModelProperty("操作结果")
//        private boolean result;
//
//        public Resp(boolean result) {
//            this.result = result;
//        }
//
//        public boolean isResult() {
//            return result;
//        }
//
//        public void setResult(boolean result) {
//            this.result = result;
//        }
//    }
}
