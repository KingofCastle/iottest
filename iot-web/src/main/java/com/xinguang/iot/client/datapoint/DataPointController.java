package com.xinguang.iot.client.datapoint;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.annotation.RequireSign;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.*;
import com.xinguang.iot.mqttprotocol.process.ProtocolProcess;
import com.xinguang.iot.service.DataService;
import com.xinguang.iot.service.SensorService;
import com.xinguang.iot.ClientResp;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jinx on 2017/4/7.
 */
@Controller
@RequestMapping("/datapoint")
public class DataPointController extends BaseClientController {


    @Autowired
    private DataService dataService;
    @Autowired
    private SensorService sensorService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增数据点", notes = "新增数据点", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> add(@ApiParam(value = "数据点数据", required = true) DataPointAddReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        if (ProtocolProcess.Topic.TEM.getCode().equals(req.getType())){
            DataTemperature temperature = new DataTemperature();
            temperature.setTime(new Date(req.getTime()));
            temperature.setValue(req.getValue());
            dataService.saveTemp(temperature);
        }
        if (ProtocolProcess.Topic.HUM.getCode().equals(req.getType())){
            DataHumidity humidity = new DataHumidity();
            humidity.setTime(new Date(req.getTime()));
            humidity.setValue(req.getValue());
            dataService.saveHumi(humidity);
        }
        if (ProtocolProcess.Topic.GPS.getCode().equals(req.getType())){
            DataGps gps = new DataGps();
            gps.setTime(new Date(req.getTime()));
            //TODO
        }

        resp.setResultData(RespYOrN.YES);
        return resp;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除数据点", notes = "删除指定数据点", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> delete(@ApiParam(value = "数据点参数", required = true) DataPointDeleteReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysSensor sensor = sensorService.findSensorByCode(req.getSensorCode());
        String sensorId = sensor.getId();
        if (ProtocolProcess.Topic.TEM.getCode().equals(req.getType())){
            DataTemperature temperature =  dataService.findTemByTime(new Date(req.getTime()),sensorId);
            if (temperature!=null){
                dataService.deleteDataTemp(temperature);
            }
        }
        if (ProtocolProcess.Topic.HUM.getCode().equals(req.getType())){
            DataHumidity humidity =  dataService.findHumByTime(new Date(req.getTime()),sensorId);
            if (humidity!=null){
                dataService.deleteDataHum(humidity);
            }
        }
        resp.setResultData(RespYOrN.YES);
        return resp;
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看数据点", notes = "查看指定数据点", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<DataPointDetailResp> detail(@ApiParam(value = "查看数据点的请求参数", required = true) DataPointDetailReq req, HttpServletRequest request) {
        ClientResp<DataPointDetailResp> resp = new ClientResp<>();
        resp.setResultData(null);
        SysSensor sensor = sensorService.findSensorByCode(req.getSensorCode());
        if (sensor==null||!request.getAttribute("companyId").equals(sensor.getCompanyId())){
            resp.setResultCode(ClientResponeConstants.SENSOR_NOT_FOUND);
            resp.setResultDesc("传感器未找到");
            return resp;
        }
        String sensorId = sensor.getId();
        if (ProtocolProcess.Topic.TEM.getCode().equals(req.getType())){
            DataTemperature temperature = dataService.findLastTemperature(sensorId);
            resp.setResultData(new DataPointDetailResp(temperature.getValue()));
        }
        if (ProtocolProcess.Topic.HUM.getCode().equals(req.getType())){
            DataHumidity humidity = dataService.findLastHumidity(sensorId);
            resp.setResultData(new DataPointDetailResp(humidity.getValue()));
        }
        return resp;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "编辑数据点", notes = "编辑数据点", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "编辑数据点请求参数", required = true) DataPointEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        resp.setResultData(RespYOrN.NO);
        SysSensor sensor = sensorService.findSensorByCode(req.getSensorCode());
        String sensorId = sensor.getId();

        if (ProtocolProcess.Topic.TEM.getCode().equals(req.getType())){
            DataTemperature temperature =  dataService.findTemByTime(new Date(req.getTime()),sensorId);
            if (temperature!=null){
                temperature.setValue(req.getValue());
                dataService.saveTemp(temperature);
                resp.setResultData(RespYOrN.YES);
            }
        }
        if (ProtocolProcess.Topic.HUM.getCode().equals(req.getType())){
            DataHumidity humidity =  dataService.findHumByTime(new Date(req.getTime()),sensorId);
            if (humidity!=null){
                humidity.setValue(req.getValue());
                resp.setResultData(RespYOrN.YES);
            }
        }
        return resp;
    }

    @RequestMapping(value = "/history",method = RequestMethod.POST)
    @ResponseBody
    @RequireSign
    @ApiOperation(value = "查询指定时间段的数据", notes = "查询指定时间段的数据", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<DataPointHistoryResp> history(@ApiParam(value = "JSON格式的传感器实体", required = true) DataPointHistoryReq req, HttpServletRequest request) {
        ClientResp<DataPointHistoryResp> resp = new ClientResp<>();
        resp.setResultData(null);
        SysSensor sensor = sensorService.findSensorByCode(req.getSensorCode());
        String sensorId = sensor.getId();
        DataPointHistoryResp dataPointHistoryResp  = new DataPointHistoryResp();
        if (ProtocolProcess.Topic.TEM.getCode().equals(req.getType())){
            PageWrap<DataTemperature> page = new PageWrap<DataTemperature>(req.getPageNum(), req.getPageSize(),null);
            List<DataTemperature> temperatureList =  dataService.findTem(new Date(req.getStart()),new Date(req.getEnd()),page,sensorId);
            if(CollectionUtils.isNotEmpty(temperatureList)){
                List<DataPointHistoryResp.ResItem> resItems = new ArrayList<>();
                for(int i=0;i<temperatureList.size();i++){
                    DataPointHistoryResp.ResItem item = new DataPointHistoryResp.ResItem();
                    item.setTime(temperatureList.get(i).getTime().getTime());
                    item.setValue(temperatureList.get(i).getValue());
                    resItems.add(item);
                }
                dataPointHistoryResp.setValues(resItems);
                resp.setResultData(dataPointHistoryResp);
            }
        }
        if (ProtocolProcess.Topic.HUM.getCode().equals(req.getType())){
            PageWrap<DataHumidity> page = new PageWrap<DataHumidity>(req.getPageNum(), req.getPageSize(),null);
            List<DataHumidity> humidityList =  dataService.findHum(new Date(req.getStart()),new Date(req.getEnd()),page,sensorId);
            if(CollectionUtils.isNotEmpty(humidityList)){
                List<DataPointHistoryResp.ResItem> resItems = new ArrayList<>();
                for(int i=0;i<humidityList.size();i++){
                    DataPointHistoryResp.ResItem item = new DataPointHistoryResp.ResItem();
                    item.setTime(humidityList.get(i).getTime().getTime());
                    item.setValue(humidityList.get(i).getValue());
                    resItems.add(item);
                }
                dataPointHistoryResp.setValues(resItems);
                resp.setResultData(dataPointHistoryResp);
            }
        }

        return resp;
    }



}
