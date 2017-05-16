package com.xinguang.iot.client.vehicle;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysVehicle;
import com.xinguang.iot.service.VehicleService;
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
 * 车辆
 */
@Controller
@RequestMapping("/vehicle")
public class VehicleController extends BaseClientController {


    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增车辆", notes = "新增车辆", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> add(@ApiParam(value = "JSON格式的车辆实体", required = true) @RequestBody VehicleAddReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysVehicle vehicle = new SysVehicle();
        if(StringUtils.isNotBlank(req.getPlant())){
            vehicle.setPlate(req.getPlant());
        }
        if(StringUtils.isNotBlank(req.getDriver())){
            vehicle.setDriver(req.getDriver());
        }
        if(StringUtils.isNotBlank(req.getIntroduction())){
            vehicle.setIntroduction(req.getIntroduction());
        }
        if(StringUtils.isNotBlank(req.getTelephone())){
            vehicle.setTelephone(req.getTelephone());
        }
        if(StringUtils.isNotBlank(req.getModel())){
            vehicle.setModel(req.getModel());
        }
        try {
            vehicleService.saveVehicle(vehicle);
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
    @ApiOperation(value = "删除车辆", notes = "删除车辆", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> delete(@ApiParam(value = "车辆车牌", required = true) @RequestBody VehicleDeleteReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysVehicle vehicle =  vehicleService.findVehicleByPlate(req.getPlant());
        try {
            vehicleService.deleteVehicle(vehicle);
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
    @ApiOperation(value = "查看车辆", notes = "查看车辆", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<VehicleDetailResp> detail(@ApiParam(value = "车牌", required = true) @RequestBody VehicleDetailReq req, HttpServletRequest request) {
        ClientResp<VehicleDetailResp> resp = new ClientResp<>();
        SysVehicle vehicle =null;
        if(StringUtils.isNotBlank(req.getPlant())){
            vehicle =  vehicleService.findVehicleByPlate(req.getPlant());
        }
        VehicleDetailResp vehicleDetailResp = new VehicleDetailResp();
        if (vehicle!=null){
            vehicleDetailResp.setPlant(vehicle.getPlate());
            vehicleDetailResp.setDriver(vehicle.getDriver());
            vehicleDetailResp.setIntroduction(vehicle.getIntroduction());
            vehicleDetailResp.setTelephone(vehicle.getTelephone());
            vehicleDetailResp.setModel(vehicle.getModel());
        }
        resp.setResultData(vehicleDetailResp);
        return resp;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改车辆", notes = "修改车辆", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "JSON格式的车辆实体", required = true) @RequestBody VehicleEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysVehicle vehicle =  vehicleService.findVehicleByPlate(req.getPlant());
        if(vehicle!=null){
            if (StringUtils.isNotBlank(req.getModel()))
                vehicle.setModel(req.getModel());
            if (StringUtils.isNotBlank(req.getTelephone()))
                vehicle.setTelephone(req.getTelephone());
            if (StringUtils.isNotBlank(req.getIntroduction()))
                vehicle.setIntroduction(req.getIntroduction());
            if (StringUtils.isNotBlank(req.getDriver()))
                vehicle.setDriver(req.getDriver());
            vehicleService.updateVehicle(vehicle);
            resp.setResultData(RespYOrN.YES);
        }else {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_FAIL);
            resp.setResultDesc("修改车辆失败");
            resp.setResultData(RespYOrN.NO);
        }
        return resp;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "罗列车辆", notes = "罗列车辆", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<VehicleListResp> list(@ApiParam(value = "请求参数", required = true) @RequestBody VehicleListReq req, HttpServletRequest request) {
        ClientResp<VehicleListResp> resp = new ClientResp<>();
        resp.setResultData(null);
        PageWrap<SysVehicle> page = new PageWrap<>(req.getPageNum(), req.getPageSize(),null);
        List<SysVehicle> vehicleList =  vehicleService.find(page);
        if (CollectionUtils.isNotEmpty(vehicleList)){
            VehicleListResp vehicleListResp = new VehicleListResp();
            List<VehicleListResp.VehicleItem> vehicleItems = new ArrayList<>();
            for(int i=0;i<vehicleList.size();i++){
                VehicleListResp.VehicleItem item = new VehicleListResp.VehicleItem();
                item.setPlant(vehicleList.get(i).getPlate());
                item.setDriver(vehicleList.get(i).getDriver());
                item.setModel(vehicleList.get(i).getModel());
                item.setTelephone(vehicleList.get(i).getTelephone());
                vehicleItems.add(item);
            }
            vehicleListResp.setVehicles(vehicleItems);
            resp.setResultData(vehicleListResp);
            return resp;
        }
        return resp;
    }
}
