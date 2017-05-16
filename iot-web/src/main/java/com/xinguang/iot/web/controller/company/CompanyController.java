package com.xinguang.iot.web.controller.company;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.xinguang.iot.annotation.RequireLogin;
import com.xinguang.iot.base.BaseClientController;
import com.xinguang.iot.RespYOrN;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.IdUtils;
import com.xinguang.iot.common.utils.JwtUtils;
import com.xinguang.iot.common.utils.MD5Utils;
import com.xinguang.iot.dao.entity.*;
import com.xinguang.iot.service.CompanyService;
import com.xinguang.iot.web.domain.AccessToken;
import com.xinguang.iot.ClientResp;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by jinx on 2017/4/7.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseClientController {


    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "企业注册", notes = "企业注册", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> register(@ApiParam(value = "JSON格式的企业实体", required = true) @RequestBody CompanyRegisterReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        SysCompany company = new SysCompany();
//        company.setCode("001");//TODO
        company.setCompanyLogin(req.getCompanyLogin());
        company.setPassword(MD5Utils.MD5(req.getPassword()));
        String apiKey = IdUtils.uuid2();
        company.setApiKey(apiKey);
        company.setApiSecret(MD5Utils.MD5(apiKey+"iot"+new Random().nextInt()).toUpperCase());
        int i = companyService.saveCompany(company);
        if (i!=1){
            resp.setResultData(RespYOrN.NO);
        }else{

            resp.setResultData(RespYOrN.YES);
        }
        return resp;
    }


    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    @ApiOperation(value = "修改密码", notes = "修改密码", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> modify(@ApiParam(value = "请求参数", required = true) @RequestBody CompanyModifyReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        String password = req.getPassword();
        String newPassword = req.getNewPassword();
        String confirmNewPwd = req.getConfirmNewPwd();
        if (StringUtils.isAnyBlank(password, newPassword, confirmNewPwd)) {
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_PARAMS_ERROR);
            resp.setResultDesc("密码不能为空");
            resp.setResultData(RespYOrN.NO);
            return resp;
        }
        if(!StringUtils.equals(newPassword,confirmNewPwd)){
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_PARAMS_ERROR);
            resp.setResultDesc("两次新密码输入不一致");
            resp.setResultData(RespYOrN.NO);
            return resp;
        }
        SysCompany company = companyService.findCompanyByCode("001");//TODO
        company.setPassword(MD5Utils.MD5(newPassword));
        int result = companyService.update(company);
        if (result > 0) {
            resp.setResultData(RespYOrN.YES);
        } else {
            resp.setResultData(RespYOrN.NO);
        }
        return resp;
    }


    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "编辑企业", notes = "编辑企业", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<RespYOrN> edit(@ApiParam(value = "JSON格式的企业实体", required = true) @RequestBody CompanyEditReq req, HttpServletRequest request) {
        ClientResp<RespYOrN> resp = new ClientResp<>();
        resp.setResultData(RespYOrN.NO);
        SysCompany company = companyService.findCompanyByCode(req.getCompanyCode());
        if (company!=null){
            if (StringUtils.isNotBlank(req.getCompanyName())){
                company.setCompanyName(req.getCompanyName());
            }

            if (StringUtils.isNotBlank(req.getAddress())){
                company.setAddress(req.getAddress());
            }
            if (StringUtils.isNotBlank(req.getIntroduction())){
                company.setIntroduction(req.getIntroduction());
            }
            if (StringUtils.isNotBlank(req.getEmail())){
                company.setEmail(req.getEmail());
            }
            if (StringUtils.isNotBlank(req.getTelephone())){
                company.setTelephone(req.getTelephone());
            }
            companyService.update(company);
            resp.setResultData(RespYOrN.YES);
        }
        return resp;
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "企业登陆", notes = "企业登陆", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResp<CompanyLoginResp> login(@ApiParam(value = "JSON格式的企业实体", required = true) @RequestBody CompanyLoginReq req) {
        String password = req.getPassword();
        String loginName = req.getLoginName();
        ClientResp<CompanyLoginResp> resp = new ClientResp<>();
        if(StringUtils.isAnyBlank(loginName,password)){
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_PARAMS_ERROR);
            resp.setResultDesc("账号密码不能为空");
            return resp;
        }
        SysCompany company = companyService.isValidUser(loginName,password);
        if(company == null){
            resp.setResultCode(ClientResponeConstants.RESULT_CODE_PARAMS_ERROR);
            resp.setResultDesc("账号或密码不正确");
            return resp;
        }
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(JwtUtils.signToken(company.getId()));
        accessToken.setRefreshToken(JwtUtils.signRefreshToken(company.getId()));
        accessToken.setExpiresIn(60*60*24*30);

        CompanyLoginResp.CompanyParma companyParma = new CompanyLoginResp.CompanyParma();
        companyParma.setCompanyId(company.getId());
        companyParma.setCompanyName(company.getCompanyName());
        companyParma.setCompanyCode(company.getCode());
        CompanyLoginResp loginResp = new CompanyLoginResp();
        loginResp.setAccessToken(accessToken);
        loginResp.setCompany(companyParma);
        resp.setResultData(loginResp);
        return resp;
    }

}
