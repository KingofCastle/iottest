package com.xinguang.iot.web.controller.device;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysCompany;
import com.xinguang.iot.dao.entity.SysDevice;
import com.xinguang.iot.service.CompanyService;
import com.xinguang.iot.service.DeviceService;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created by jinx on 2017/4/7.
 * 设备添加
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseClientController {


    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增设备", notes = "新增设备", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<HashMap<String, Object>> add(@ApiParam(value = "JSON格式的设备实体", required = true) @RequestBody DeviceAddReq req, HttpServletRequest request) {
        ClientResp<HashMap<String, Object>> resp = new ClientResp<>();
        SysDevice device = new SysDevice();
        if(StringUtils.isNotBlank(req.getType())){
            device.setType(req.getType());
        }
        if(StringUtils.isNotBlank(req.getName())){
            device.setName(req.getName());
        }
        if(StringUtils.isNotBlank(req.getIntroduction())){
            device.setIntroduction(req.getIntroduction());
        }
        if(StringUtils.isNotBlank(req.getTags())){
            device.setTags(req.getTags());
        }
        String deviceCode = "11222";
        device.setCode(deviceCode);//TODO
        deviceService.saveDevice(device);
        HashMap<String, Object> map = new HashMap<>();
        map.put("deviceCode", deviceCode);
        resp.setResultData(map);
        return resp;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除设备", notes = "删除设备", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> delete(@ApiParam(value = "设备编号", required = true) @RequestBody DeviceDeleteReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysDevice device =  deviceService.findDeviceByCode(req.getDeviceCode());
        deviceService.deleteDevice(device);
        resp.setResultData(RespYOrN.YES);
        return resp;
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看设备", notes = "查看设备", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<DeviceDetailResp> detail(@ApiParam(value = "设备编号", required = true) @RequestBody DeviceDetailReq req, HttpServletRequest request) {
        ClientResp<DeviceDetailResp> resp = new ClientResp<>();
        resp.setResultData(null);
        SysDevice device =null;
        if(StringUtils.isNotBlank(req.getDeviceCode())){
            device =  deviceService.findDeviceByCode(req.getDeviceCode());
        }
        DeviceDetailResp deviceDetailResp = new DeviceDetailResp();
        if (device!=null){
            deviceDetailResp.setType(device.getType());
            deviceDetailResp.setTags(device.getTags());
            deviceDetailResp.setName(device.getName());
            deviceDetailResp.setIntroduction(device.getIntroduction());
            resp.setResultData(deviceDetailResp);
        }
        return resp;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改设备", notes = "修改设备", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "JSON格式的设备实体", required = true) @RequestBody DeviceEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysDevice device = deviceService.findDeviceByCode(req.getDeviceCode());
        if (device != null) {
            if (StringUtils.isNotBlank(req.getName()))
                device.setName(req.getName());
            if (StringUtils.isNotBlank(req.getType()))
                device.setType(req.getType());
            if (StringUtils.isNotBlank(req.getIntroduction()))
                device.setIntroduction(req.getIntroduction());
            if (StringUtils.isNotBlank(req.getTags()))
                device.setTags(req.getTags());
            deviceService.updateDevice(device);
            resp.setResultData(RespYOrN.YES);
        } else {
            resp.setResultData(RespYOrN.NO);
        }
        return resp;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "罗列设备", notes = "罗列设备", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<DeviceListResp> list(@ApiParam(value = "请求参数", required = true) @RequestBody DeviceListReq req, HttpServletRequest request) {
        ClientResp<DeviceListResp> resp = new ClientResp<>();
        resp.setResultData(null);
        PageWrap<SysDevice> page = new PageWrap<>(req.getPageNum(), req.getPageSize(),null);
        List<SysDevice> deviceList =  deviceService.find(page);
        if (CollectionUtils.isNotEmpty(deviceList)){
            DeviceListResp deviceListResp = new DeviceListResp();
            List<DeviceListResp.DeviceItem> deviceItems = new ArrayList<>();
            for(int i=0;i<deviceList.size();i++){
                DeviceListResp.DeviceItem item = new DeviceListResp.DeviceItem();
                item.setName(deviceList.get(i).getName());
                item.setCode(deviceList.get(i).getCode());
                item.setType(deviceList.get(i).getType());
                item.setIntroduction(deviceList.get(i).getIntroduction());
                deviceItems.add(item);
            }
            deviceListResp.setDevices(deviceItems);
            resp.setResultData(deviceListResp);
        }
        return resp;
    }

//    @RequestMapping(value = "/valid",method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation(value = "设备认证", notes = "设备认证", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ClientResp<RespYOrN> add(@ApiParam(value = "设备认证参数", required = true) DeviceValidReq req, HttpServletRequest request) {
//        ClientResp<RespYOrN> resp = new ClientResp<>();
//        if (StringUtils.isNoneBlank(req.getClientId(),req.getCompanyLogin(),req.getApiKey())){
//            SysDevice device = deviceService.checkDevice(req.getClientId());
//            if (device==null){
//                resp.setResultCode(ClientResponeConstants.DEVICE_NOT_VALID);
//                resp.setResultData(RespYOrN.NO);
//                resp.setResultDesc("设备鉴权未通过");
//                return resp;
//            }
//            SysCompany company = companyService.checkValid(req.getCompanyLogin(),req.getApiKey());
//            if (company != null) {
//                resp.setResultData(RespYOrN.YES);
//                resp.setResultDesc("设备鉴权通过");
//                return resp;
//            }else {
//                resp.setResultCode(ClientResponeConstants.DEVICE_NOT_VALID);
//                resp.setResultData(RespYOrN.NO);
//                resp.setResultDesc("设备鉴权未通过");
//                return resp;
//            }
//        }
//        resp.setResultCode(ClientResponeConstants.RESULT_CODE_PARAMS_ERROR);
//        resp.setResultDesc("参数错误");
//        return resp;
//
//    }

}
