package com.xinguang.iot.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.xinguang.iot.annotation.RequireLogin;
import com.xinguang.iot.common.constants.ClientResponeConstants;
import com.xinguang.iot.common.utils.JwtUtils;
import com.xinguang.iot.ClientResp;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;


/**
 * Created by chuantou on 2016/12/22.
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CheckLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        HandlerMethod myHandlerMethod = (HandlerMethod) handler;
        Annotation requireLogin = myHandlerMethod.getMethodAnnotation(
                RequireLogin.class);

        ClientResp <Object> resp = new ClientResp<Object>();
        if (null != requireLogin) { // 需要登录
            String token = getToken(request);
            if (null != token) { // 有token
                try {
                    String companyId = JwtUtils.verify(token);
                    request.setAttribute("companyId", companyId);
                } catch (Exception e) {
                    if (e instanceof JWTVerificationException) {// token过期
                        resp.setResultCode(ClientResponeConstants.RESULT_CODE_NO_LOGIN_EXPIRES);
                        resp.setResultDesc("登录过期");
                    } else {// token无效
                        resp.setResultCode(ClientResponeConstants.RESULT_CODE_NO_AUTHENTICATED);
                        resp.setResultDesc("未登录");
                    }
                    out(response, resp);
                    return false;
                }
            } else {// without token
                resp.setResultCode(ClientResponeConstants.RESULT_CODE_NO_AUTHENTICATED);
                resp.setResultDesc("未登录");
                out(response, resp);
                return false;
            }
        }


        return true;
    }

    private String getToken(HttpServletRequest httpRequest) {
        String auth = httpRequest.getHeader("Authorization");
        if ((auth != null) && (auth.length() > 7))
        {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo("bearer") == 0)
            {

                auth = auth.substring(7, auth.length());

                return auth;
            }
        }

        return null;
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
