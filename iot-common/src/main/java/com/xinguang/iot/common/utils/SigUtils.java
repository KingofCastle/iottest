package com.xinguang.iot.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名util.
 */
public class SigUtils {

    private static String concatParams(Map<String, String> params2) throws UnsupportedEncodingException {
        Object[] key_arr = params2.keySet().toArray();
        Arrays.sort(key_arr);
        String str = "";

        for (Object key : key_arr) {
            String val = params2.get(key);
            key = URLEncoder.encode(key.toString(), "UTF-8");
            val = URLEncoder.encode(val, "UTF-8");
            str += "&" + key + "=" + val;
        }

        return str.replaceFirst("&", "");
    }

    private static String byte2hex(byte[] b) {
        StringBuffer buf = new StringBuffer();
        int i;

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

    public static String genSig(String pathUrl, Map<String, String> params,
                                String apiSecret) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str = concatParams(params);
        str = pathUrl + "?" + str + apiSecret;

        MessageDigest md = MessageDigest.getInstance("SHA1");
        return byte2hex(md.digest(byte2hex(str.getBytes("UTF-8")).getBytes()));
    }

    //http://localhost:8090/iot/datapoint/detail?apiKey=fds15fe0fs0a4sa5d231fs&sig=dsadsa&sensorId=1
    //9211c75a942233d7d669f70c75fa1044fc2b0579
    //http://localhost:8090/iot/datapoint/detail?apiKey=fds15fe0fs0a4sa5d231fs&sig=9211c75a942233d7d669f70c75fa1044fc2b0579&sensorId=DS18B20-001&type=0
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, String> params = new HashMap<>();
        params.put("apiKey","fds15fe0fs0a4sa5d231fs");
        params.put("sensorCode","DHT22-001");
        params.put("type","0");
        System.out.println(genSig("http://localhost:8090/iot/datapoint/detail",params,"wedft12fe13"));
    }
}
