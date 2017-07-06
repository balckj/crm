package com.yidatec.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author QuShengWen
 */
public class WxSignatureUtil {

    public static Map<String,String> generateJSAPISignature(String jsapi_ticket,String url){
        String noncestr = create_nonce_str();
        String timestamp = create_timestamp();
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=").append(jsapi_ticket);
        sb.append("&noncestr=").append(noncestr);
        sb.append("&timestamp=").append(timestamp);
        sb.append("&url=").append(url);
        String sign = SHA1.encode(sb.toString());

        Map<String, String> ret = new HashMap<String,String>();
        ret.put("signature",sign);
        ret.put("nonceStr",noncestr);
        ret.put("timestamp",timestamp);
        ret.put("appId",ConfigProperties.getWeixinAppId());
        ret.put("debug",ConfigProperties.getJsapi_debug());
        return ret;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}
