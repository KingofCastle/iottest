package com.xinguang.iot.interceptor;

import com.xinguang.iot.annotation.RequireSign;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.SigUtils;
import com.xinguang.iot.dao.entity.SysCompany;
import com.xinguang.iot.service.CompanyService;
import com.xinguang.iot.ClientResp;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.Annotation;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by chuantou on 2016/12/22.
 */
public class CheckSignInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CompanyService companyService;

    private static final Logger logger = LoggerFactory.getLogger(CheckSignInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        HandlerMethod myHandlerMethod = (HandlerMethod) handler;
        Annotation requireLogin = myHandlerMethod.getMethodAnnotation(
                RequireSign.class);

        ClientResp<Object> resp = new ClientResp<Object>();
        if (null != requireLogin) { // 需要验证
            boolean result =  checkIdentity(request);
            if (result){
                String apiKey = request.getParameter("apiKey");
                SysCompany company = companyService.findSecretByKey(apiKey);
                request.setAttribute("companyId",company.getId());
            }else {
                resp.setResultCode(ClientResponeConstants.SIGNATURE_ERROR);
                resp.setResultDesc("签名错误");
                out(response, resp);
                return false;
            }
        }
        return true;
    }

    private boolean checkIdentity(HttpServletRequest request) {
        boolean result = false;
        String apiKey = request.getParameter("apiKey");
        if (apiKey!=null){
            SysCompany company  =  companyService.findSecretByKey(apiKey);
            if (company!=null){
                String apiSecret = company.getApiSecret();
                String sig =request.getParameter("sig");
                Map<String,String> paramMap = getParams(request);
                try {
                    if (sig.equals(SigUtils.genSig(request.getRequestURL().toString(),paramMap, apiSecret))){
                        result = true;
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private Map<String,String> getParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    if (!paramName.equals("sig"))
                        map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    /**
     * 返回输出json
     *
     * @param response
     * @param resp
     */
    private static final void out(HttpServletResponse response, ClientResp resp) {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(objectMapper.writeValueAsString(resp));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
