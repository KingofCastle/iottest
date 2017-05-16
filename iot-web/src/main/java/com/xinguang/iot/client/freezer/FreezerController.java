package com.xinguang.iot.client.freezer;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysFreezer;
import com.xinguang.iot.service.FreezerService;
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
 * 冰柜
 */
@Controller
@RequestMapping("/freezer")
public class FreezerController extends BaseClientController {


    @Autowired
    private FreezerService freezerService;
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增冰柜", notes = "新增冰柜", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> add(@ApiParam(value = "JSON格式的冰柜实体", required = true) @RequestBody FreezerAddReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysFreezer freezer = new SysFreezer();
        if(StringUtils.isNotBlank(req.getModel())){
            freezer.setModel(req.getModel());
        }
        if(StringUtils.isNotBlank(req.getVolume())){
            freezer.setVolume(req.getVolume());
        }
        if(StringUtils.isNotBlank(req.getIntroduction())){
            freezer.setIntroduction(req.getIntroduction());
        }
        if(StringUtils.isNotBlank(req.getStoreId())){
            freezer.setStoreId(req.getStoreId());
        }
        try {
            freezerService.saveFreezer(freezer);
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
    @ApiOperation(value = "删除冰柜", notes = "删除冰柜", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> delete(@ApiParam(value = "冰柜编号", required = true) @RequestBody FreezerDeleteReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysFreezer freezer =  freezerService.findFreezerByCode(req.getFreezerCode());
        try {
            freezerService.deleteFreezer(freezer);
        } catch (Exception e) {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
            resp.setResultDesc(e.getMessage());
            resp.setResultData(RespYOrN.NO);
        }
        resp.setResultData(RespYOrN.YES);
        return resp;
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看冰柜", notes = "查看冰柜", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<FreezerDetailResp> detail(@ApiParam(value = "编号", required = true) @RequestBody FreezerDetailReq req, HttpServletRequest request) {
        ClientResp<FreezerDetailResp> resp = new ClientResp<>();
        SysFreezer freezer =null;
        if(StringUtils.isNotBlank(req.getFreezerCode())){
            freezer =  freezerService.findFreezerByCode(req.getFreezerCode());
        }
        FreezerDetailResp vehicleDetailResp = new FreezerDetailResp();
        if (freezer!=null){
            vehicleDetailResp.setModel(freezer.getModel());
            vehicleDetailResp.setVolume(freezer.getVolume());
            vehicleDetailResp.setIntroduction(freezer.getIntroduction());

            vehicleDetailResp.setStoreName(storeService.findStoreById(freezer.getStoreId()).getName());
        }
        resp.setResultData(vehicleDetailResp);
        return resp;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改冰柜", notes = "修改冰柜", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "JSON格式的冰柜实体", required = true) @RequestBody FreezerEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysFreezer freezer =  freezerService.findFreezerByCode(req.getFreezerCode());
        if(freezer!=null){
            if (StringUtils.isNotBlank(req.getModel()))
                freezer.setModel(req.getModel());
            if (StringUtils.isNotBlank(req.getStoreId()))
                freezer.setStoreId(req.getStoreId());
            if (StringUtils.isNotBlank(req.getIntroduction()))
                freezer.setIntroduction(req.getIntroduction());
            if (StringUtils.isNotBlank(req.getVolume()))
                freezer.setVolume(req.getVolume());
            freezerService.updateFreezer(freezer);
            resp.setResultData(RespYOrN.YES);
        }else {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
            resp.setResultDesc("修改冰柜失败");
            resp.setResultData(RespYOrN.NO);
        }
        return resp;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "罗列冰柜", notes = "罗列冰柜", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<FreezerListResp> list(@ApiParam(value = "请求参数", required = true) @RequestBody FreezerListReq req, HttpServletRequest request) {
        ClientResp<FreezerListResp> resp = new ClientResp<>();
        resp.setResultData(null);
        PageWrap<SysFreezer> page = new PageWrap<>(req.getPageNum(), req.getPageSize(),null);
        List<SysFreezer> freezerList =  freezerService.find(page);
        if (CollectionUtils.isNotEmpty(freezerList)){
            FreezerListResp freezerListResp = new FreezerListResp();
            List<FreezerListResp.FreezerItem> freezerItems = new ArrayList<>();
            for(int i=0;i<freezerList.size();i++){
                FreezerListResp.FreezerItem item = new FreezerListResp.FreezerItem();
                item.setVolume(freezerList.get(i).getVolume());
                item.setFreezerCode(freezerList.get(i).getCode());
                item.setModel(freezerList.get(i).getModel());
                item.setIntroduction(freezerList.get(i).getIntroduction());
                item.setStoreName(storeService.findStoreById(freezerList.get(i).getStoreId()).getName());

                freezerItems.add(item);
            }
            freezerListResp.setFreezers(freezerItems);
            resp.setResultData(freezerListResp);
            return resp;
        }
        return resp;
    }
}
