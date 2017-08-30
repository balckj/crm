package com.yidatec.controller;

import com.yidatec.service.WechatService;
//import com.yidatec.util.ConfigProperties;
import com.yidatec.util.ConfProperties;
import com.yidatec.util.SHA1;
import com.yidatec.util.XStreamFactory;
import com.yidatec.wechat.msg.Articles;
import com.yidatec.wechat.msg.InMessage;
import com.yidatec.wechat.msg.OutMessage;
import com.thoughtworks.xstream.XStream;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Qu on 2017/8/25.
 */
@Controller
public class WechatController extends BaseController {

	private static final Logger log = LogManager
			.getLogger(WechatController.class);

	@Autowired
	ConfProperties confProperties;

	@Autowired
	WechatService wechatService;

	/**
	 * 验证首次绑定
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/weixin.wx", method = RequestMethod.GET)
	@ResponseBody
	public String index_get(String signature, String timestamp, String nonce,
			String echostr) {

		// 验证消息
		if (!checkSignature(confProperties.getWeChatToken(), signature,
				timestamp, nonce)) {
			// 验证失败
			return "";
		}
		log.info("服务器验签成功!");
		return echostr;
	}

	/**
	 * 微信消息泵入口
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/weixin.wx", method = RequestMethod.POST)
	@ResponseBody
	public String index_post(String signature, String timestamp, String nonce,
			String echostr, HttpServletRequest request) {

		// 验证消息
//		if (!checkSignature(ConfigProperties.getWeixinToken(), signature,
//				timestamp, nonce)) {
//			// 验证失败
//			return "";
//		}
        InMessage inMsg = null;
		try {
			inMsg = getInMessage(request);
			OutMessage outMsg = wechatService.execute(inMsg);

			if (outMsg == null){
				return "";
            }

			// 反馈给微信服务器
			outMsg.setCreateTime(new Date().getTime());
			outMsg.setToUserName(inMsg.getFromUserName());
            if(outMsg.getFromUserName() == null || outMsg.getFromUserName().isEmpty())
			    outMsg.setFromUserName(inMsg.getToUserName());
			XStream xs = XStreamFactory.init(false);
			xs.alias("xml", OutMessage.class);
			xs.alias("item", Articles.class);
			String xml = xs.toXML(outMsg);
			return xml;
		} catch (Exception exp) {
            StringBuffer res = new StringBuffer(300);
            res.append("WeixinService.execute() No response");
            if(inMsg != null){
                res.append(", message type:").append(inMsg.getMsgType()).append("   event type:").append(inMsg.getEvent()).append("    from:").append(inMsg.getFromUserName());
            }
            res.append("***********************").append(exp.getMessage());
			log.error(res.toString(), exp);
            return "";
		}
	}

	

	/**
	 * 获取微信平台发来的消息
	 * 
	 * @return
	 */
	private InMessage getInMessage(HttpServletRequest request) {
		InMessage msg = new InMessage();
		try {
			ServletInputStream in = request.getInputStream();
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml", InMessage.class);
			String xmlMsg = inputStream2String(in);
			xs.fromXML(xmlMsg,msg);
		} catch (Exception e) {
			msg = new InMessage();
            log.error(e.getMessage(), e);
		}
		return msg;
	}

	/**
	 * 流转字符串
	 * 
	 * @param in
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static final String inputStream2String(InputStream in)
			throws UnsupportedEncodingException, IOException {
		if (in == null)
			return "";

		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}

	/**
	 * 验证微信发来的消息是否真实有效
	 */
	public static final boolean checkSignature(String token, String signature,
			String timestamp, String nonce) {
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = params.get(0) + params.get(1) + params.get(2);
		return SHA1.encode(temp).equals(signature);
	}

}
