package com.yidatec.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTools {

	// 菜单创建
	public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

	// 菜单查询
	public static String MENU_SEARCH_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";

	// 菜单删除
	public static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";

	private String createMenuJson() throws Exception{
		Map<String, Object> menus = new HashMap<String, Object>();
		List<Map<String, Object>> buttons = new ArrayList<Map<String, Object>>();
		Map<String, Object> button = new HashMap<String, Object>();
		Map<String, Object> sub_button = new HashMap<String, Object>();
		List<Map<String, Object>> list_sub_button = new ArrayList<Map<String, Object>>();

		/** 客户 */
		button.put("name", "crm");

		sub_button = new HashMap<String, Object>();
		sub_button.put("type", "view");
		sub_button.put("name", "campaign");

		String redirectUrl = WeixinHelper.host+WeixinHelper.contextPath+"/retriveOpenId";
		redirectUrl = URLEncoder.encode(redirectUrl,"UTF-8");
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=" + WeixinHelper.appId
				+ "&redirect_uri="+redirectUrl
				+ "&response_type=code" + "&scope=snsapi_base" + "&state=campaign#wechat_redirect";
		sub_button.put("url", url);
//		sub_button.put("url","http://qsw.tunnel.qydev.com/module/campaign.html#/list");
		list_sub_button.add(sub_button);

		sub_button = new HashMap<String, Object>();
		sub_button.put("type", "view");
		sub_button.put("name", "customer");
		redirectUrl = WeixinHelper.host+WeixinHelper.contextPath+"/retriveOpenId";
		redirectUrl = URLEncoder.encode(redirectUrl,"UTF-8");
		url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=" + WeixinHelper.appId
				+ "&redirect_uri="+redirectUrl
				+ "&response_type=code" + "&scope=snsapi_base" + "&state=customer#wechat_redirect";
		sub_button.put("url", url);
//		sub_button.put("url", "http://qsw.tunnel.qydev.com/module/customer.html#/list");
		list_sub_button.add(sub_button);

//		sub_button = new HashMap<String, Object>();
//		sub_button.put("type", "view");
//		sub_button.put("name", "总教头入口");
//		redirectUrl = WeixinHelper.host+WeixinHelper.contextPath+"/retriveOpenId";
//		redirectUrl = URLEncoder.encode(redirectUrl,"UTF-8");
//		url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
//				+ "appid=" + WeixinHelper.appId
//				+ "&redirect_uri="+redirectUrl
//				+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=header#wechat_redirect";
//		sub_button.put("url", url);
//		list_sub_button.add(sub_button);
		button.put("sub_button", list_sub_button);
		buttons.add(button);

		menus.put("button", buttons);
		String jsonMenu = new ObjectMapper().writeValueAsString(menus);
		System.out.println(jsonMenu);
		return jsonMenu;
	}

	/**
	 * 创建按钮
	 */
	public void createMenu(String TOKEN) {
		try {
			String url = MENU_CREATE_URL + TOKEN;
			String jsonMenu = this.createMenuJson();
			String res = HttpClientHelper.callTrustHttps(url, jsonMenu);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 信任HTTPS请求
	 * 
	 * @param url
	 * @return
	 */
	// public String deleteMenu(String TOKEN) {
	// try {
	// HttpClient client = new HttpClient();
	// Protocol myhttps = new Protocol("https",
	// new MySSLProtocolSocketFactory(), 443);
	// Protocol.registerProtocol("https", myhttps);
	// GetMethod get = new GetMethod(MENU_DELETE_URL + TOKEN);
	// get.getParams().setContentCharset("UTF-8");
	// // 发送HTTP请求
	// String respStr = "";
	// try {
	// client.executeMethod(get);
	// // 返回成功
	// if (get.getStatusCode() == 200) {
	// respStr = get.getResponseBodyAsString();
	// } else {
	// respStr = "";
	// }
	// } catch (HttpException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return respStr;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

//	public static String toJsonString(Object obj) {
//
//		SerializeWriter out = new SerializeWriter();
//		JSONSerializer serializer = new JSONSerializer(out);
//		// serializer.getValueFilters().add(new RoleFilter());
//		serializer.write(obj);
//		return out.toString();
//	}

	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		WeixinHelper weixinHelper = new WeixinHelper();
		weixinHelper.checkToken();
		// 创建菜单
		new MenuTools().createMenu(weixinHelper.ACCESS_TOKEN);
		// 删除菜单
		// new MenuTools().deleteMenu(weixinHelper.ACCESS_TOKEN);
	}

}
