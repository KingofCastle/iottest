package com.xinguang.iot.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.xinguang.iot.common.Exception.AliDayuException;
import com.xinguang.iot.common.config.Global;

/**
 * Created by chuantou on 2016/12/26.
 */
public class AliDaYuUtils {

    private static final String sms_type = "normal";

    private static final String url = "http://gw.client.taobao.com/router/rest";
    private static final String appkey = "23583898";
    private static final String secret = "173e834ce2071b8c6495c49b7c34db52";

    public static BizResult sendSMS(String orderId, String phone, String paramsJsonStr) throws ApiException, AliDayuException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        if (StringUtils.isNoneBlank(orderId))
            req.setExtend(orderId);
        req.setSmsType(sms_type);
        req.setSmsFreeSignName(Global.getConfig("sms_free_sign_name"));
        if (StringUtils.isNoneBlank(paramsJsonStr))
            req.setSmsParamString(paramsJsonStr);
        req.setRecNum(phone);
        req.setSmsTemplateCode(Global.getConfig("sms_template_code"));
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        if (null != rsp.getErrorCode()) {
            throw new AliDayuException(rsp.getErrorCode(), rsp.getSubMsg());
        }

        return rsp.getResult();
    }

    public static void main(String[] args) {
        try {
            sendSMS("1", "13588116448", "{'date':'2016-01-01','code':'1'}");
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (AliDayuException e) {
            e.printStackTrace();
        }
    }

}
