package com.yidatec.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 处理微信消息帮助类
 */
public class WeixinHelper {

	private static final Logger log = LogManager.getLogger(WeixinHelper.class);

	public static final String host = ConfigProperties.getWeixinHost();
	public static final String hostNoPrefix = ConfigProperties.getWeixinHostNoPrefix();
	public static final String contextPath = ConfigProperties
			.getWeixinContextPath();
	public static final String appId = ConfigProperties.getWeixinAppId();
	public static final String appSecret = ConfigProperties
			.getWeixinAppSecret();

	/** access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token */
	public static String ACCESS_TOKEN = "";

	/** 获取access token的URL &appid=APPID&secret=APPSECRET */
	public final static String URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	/**
	 * 获取access token
	 * 
	 * @return
	 */
	public void checkToken() {
		try {
			String get_token_url = URL_TOKEN + "&appid=" + appId + "&secret="
					+ appSecret;

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(get_token_url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				String json = EntityUtils.toString(response.getEntity());
				if (json != null) {
					//JSONObject jsonObj = JSON.parseObject(json);
					
					JsonNode jsonObj = new ObjectMapper().readTree(json);
//					JsonNode res = jsonObj.findValue("errcode");
//					if (res!=null && res.asInt() == 0) {
						ACCESS_TOKEN = jsonObj.findValue("access_token").asText();
//					} else {
//						log.error("ACCESS_TOKEN 解码失败, JSON:" + json);
//					}
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}