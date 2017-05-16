package com.xinguang.iot.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZhimaCreditScoreBriefGetRequest;
import com.alipay.api.response.ZhimaCreditScoreBriefGetResponse;
import com.xinguang.iot.common.Exception.ZMXYBreifGetFaildException;

/**
 * Created by chuantou on 2016/12/22.
 */
public class ZMXYUtils {

    //用于生成TransactionId的自增器
    private static AtomicLong transAutoIncIdx   = new AtomicLong(1000000000000l);

    private static final String URL = "https://openapi.alipay.com/gateway.do";
    private static final String APPID = "2016121904418243";
    private static final String APP_PRIVATE_KEY =   "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMtkhmUCp53B5pNKXld52aveM5V6wEj8nGTvhN0b5frHyu3+ouxCkedvsRDwP82sbYDxPMUfF3gWz0Jgt+CZlodTUplBrmm/oG5FJ5vVGdfqEfuQPxUjwZkQ2RAESmeTl8nIzf3ue5pcQijCzfcPL4ey94ifaK3ggWe/1rEX0su9AgMBAAECgYA7a1C/7bieI+mCmjVZhOWMikuznbrTF8lwy3CbT+0uCHRGdx+Nuhz56e1mr2lUYs1S4CcKx3yJvtAp6iuN2BDbTplKPBt/uHAe3+rppvHvMIpawCtYcTH4dDwbbNSpYfPVOoTpOu2bc/4/I2476MnzCu2YoOVkTzxIKex23vU1GQJBAOxt5JNCV+42cO899/pI+klBO08hG+ffcXEaRYhMM1BBNbjcRKuvvYPR28nac7s9dau3nKGaL+Q24k9zey/cCUcCQQDcOpAUa1K4rg6Ojgcz5gsi8o/svsOppz9C1iQjJ8IYAAaeqezSSDUSf9KURsTwVX+9DyaMsC1uOvcrYpW64ETbAkEAxv9D6XJowjaOXb/BzabJCwldmx+Z9lNKj1D2f17rvbv1NLApGHylvaLQJJi62jlDppG3wwkdEC/l0cu4PrMsFwJAV2j3HuOqJZsQNAyzVyoLaYeVEIZ26rNzLHgKR9nT8+qutfMECEKYrgsF7cxZBnspBEUrK9QRdBr7V8D9ZPr22QJBAKrOHQnTHErp0wXjgYOs17N+hTp5pjvaIOqi9YfYm3Io4VVYx5We3qGN/wlLHTmm2s+5sBZqhl9vuy7QPTG0exs=";
//    private static final String APP_PUBLIC_KEY =    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2pnlbH2zNA7/0fsJ8k5AhM1fzXRoRmWCSlVPU1P00IRlIEMY/3bnnVN9pQ524Pd/fJWMEleuSRrxeskQkzMT/RA6ExRksAIjeEwUfprUrrJGt6bdfywCXd7xKrXwuE4uy+QgXsB/7q6I5nBgR9zntozZoa7X+v9waUaW1jbe9DwIDAQAB";
    private static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    private static final String FORMAT = "json";
    private static final String CHARSET = "UTF-8";
    private static final String PRODUCT_CODE = "w1010100000000002733";

    public static String breifget(String cert_no, String name, String admittance_score) throws AlipayApiException, ZMXYBreifGetFaildException {
        String transactionId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                + transAutoIncIdx.getAndDecrement();

        AlipayClient alipayClient = new DefaultAlipayClient(URL, APPID, APP_PRIVATE_KEY, FORMAT,CHARSET, ALIPAY_PUBLIC_KEY);

        ZhimaCreditScoreBriefGetRequest request = new ZhimaCreditScoreBriefGetRequest();
        request.setBizContent("{" +
                "    \"transaction_id\":\"" +transactionId+ "\"," +
                "    \"product_code\":\"" + PRODUCT_CODE + "\"," +
                "    \"cert_type\":\"IDENTITY_CARD\"," +
                "    \"cert_no\":\"" + cert_no + "\"," +
                "    \"name\":\"" + name + "\"," +
                "    \"admittance_score\":" + admittance_score + "" +
                "  }");

        ZhimaCreditScoreBriefGetResponse response = alipayClient.execute(request);
        if(response.isSuccess()) {
            return response.getIsAdmittance();
        } else {
            throw new ZMXYBreifGetFaildException(response.getCode(), response.getSubMsg());
        }
    }
}
