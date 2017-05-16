package com.xinguang.iot.web.controller.sensor;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.annotation.RequireSign;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysDevice;
import com.xinguang.iot.dao.entity.SysSensor;
import com.xinguang.iot.service.DeviceService;
import com.xinguang.iot.service.SensorService;
import com.xinguang.iot.ClientResp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jinx on 2017/4/7.
 * 设备添加
 */
@Controller
@RequestMapping("/sensor")
public class SensorController extends BaseClientController {


    @Autowired
    private SensorService sensorService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增传感器", notes = "新增传感器", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<HashMap<String, Object>> add(@ApiParam(value = "JSON格式的传感器实体", required = true) @RequestBody SensorAddReq req, HttpServletRequest request) {
        ClientResp<HashMap<String, Object>> resp = new ClientResp<>();
        resp.setResultCode(ClientResponeConstants.RESULT_CODE_SUCCESS);
        SysSensor sensor = new SysSensor();
        if(StringUtils.isNotBlank(req.getDeviceCode())){
            SysDevice device = deviceService.findDeviceByCode(req.getDeviceCode());
            sensor.setDeviceId(device.getId());
        }
        if(StringUtils.isNotBlank(req.getType())){
            sensor.setType(req.getType());
        }
        if(StringUtils.isNotBlank(req.getName())){
            sensor.setName(req.getName());
        }
        if(StringUtils.isNotBlank(req.getIntroduction())){
            sensor.setIntroduction(req.getIntroduction());
        }
        if(StringUtils.isNotBlank(req.getTags())){
            sensor.setTags(req.getTags());
        }
        String sensorCode = "1111";//TODO
        try {
            sensorService.saveSensor(sensor);
        } catch (Exception e) {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
            resp.setResultDesc(e.getMessage());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("sensorCode", sensorCode);
        resp.setResultData(map);
        return resp;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除传感器", notes = "删除传感器", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> delete(@ApiParam(value = "传感器编号", required = true) @RequestBody SensorDeleteReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysSensor sensor =  sensorService.findSensorByCode(req.getSensorCode());
        try {
            sensorService.deleteSensor(sensor);
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
    @RequireSign
    @ApiOperation(value = "查看传感器", notes = "查看传感器", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<SensorDetailResp> detail(@ApiParam(value = "传感器编号", required = true) @RequestBody SensorDetailReq req, HttpServletRequest request) {
        ClientResp<SensorDetailResp> resp = new ClientResp<>();
        SysSensor sensor =null;
        if(StringUtils.isNotBlank(req.getSensorCode())){
            sensor =  sensorService.findSensorByCode(req.getSensorCode());
        }
        SensorDetailResp sensorDetailResp = new SensorDetailResp();
        if (sensor!=null){
            sensorDetailResp.setType(sensor.getType());
            sensorDetailResp.setTags(sensor.getTags());
            sensorDetailResp.setName(sensor.getName());
            sensorDetailResp.setIntroduction(sensor.getIntroduction());
        }
        resp.setResultData(sensorDetailResp);
        return resp;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改传感器", notes = "修改传感器", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "JSON格式的传感器实体", required = true) @RequestBody SensorEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysSensor sensor =  sensorService.findSensorById(req.getSensorCode());
        if(sensor!=null){
            if (StringUtils.isNotBlank(req.getName()))
                sensor.setName(req.getName());
            if (StringUtils.isNotBlank(req.getType()))
                sensor.setType(req.getType());
            if (StringUtils.isNotBlank(req.getIntroduction()))
                sensor.setIntroduction(req.getIntroduction());
            if (StringUtils.isNotBlank(req.getTags()))
                sensor.setTags(req.getTags());
            sensorService.updateSensor(sensor);
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
    @ApiOperation(value = "罗列传感器", notes = "罗列传感器", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<SensorListResp> list(@ApiParam(value = "JSON格式的传感器实体", required = true) @RequestBody SensorListReq req, HttpServletRequest request) {
        ClientResp<SensorListResp> resp = new ClientResp<>();
        resp.setResultData(null);
        PageWrap<SysSensor> page = new PageWrap<>(req.getPageNum(), req.getPageSize(),null);
        List<SysSensor> sensorList =  sensorService.find(page);
        if (CollectionUtils.isNotEmpty(sensorList)){
            SensorListResp sensorListResp = new SensorListResp();
            List<SensorListResp.SensorItem> sensorItems = new ArrayList<>();
            for(int i=0;i<sensorList.size();i++){
                SensorListResp.SensorItem item = new SensorListResp.SensorItem();
                item.setName(sensorList.get(i).getName());
                item.setCode(sensorList.get(i).getCode());
                item.setType(sensorList.get(i).getType());
                item.setIntroduction(sensorList.get(i).getIntroduction());
                sensorItems.add(item);
            }
            sensorListResp.setSensors(sensorItems);
            resp.setResultData(sensorListResp);
            return resp;
        }
        return resp;
    }
}
