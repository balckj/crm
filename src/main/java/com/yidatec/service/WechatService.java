package com.yidatec.service;


import com.yidatec.util.WxSignatureUtil;
import com.yidatec.wechat.msg.EventType;
import com.yidatec.wechat.msg.InMessage;
import com.yidatec.wechat.msg.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信基础服务
 * @author j
 *
 */
@Service("weixinService")
public class WechatService {

//	public static final String noncestr = "iloveyou";
//
//	public static final String timestamp = "1443628800000";

	//private static final Logger log = LogManager.getLogger(WeixinController.class);
//	@Autowired
//	UserMapper userMapper;
//
//	@Autowired
//	PicTaskService picTaskService;
//
//	@Autowired
//	DeptService deptService;

	@Autowired
	JsApiTicketService jsApiTicketService;

	@Autowired
	AccessTokenService accessTokenService;
	
	/**
	 * 执行响应微信推送消息的代码
	 * @param inMsg
	 * @return
	 */
	
	public OutMessage execute(InMessage inMsg) throws Exception {
		if (inMsg == null)
			return null;
		OutMessage outMsg = null;
		if(EventType.LOCATION.equals(inMsg.getEvent())){
//			outMsg = updateLocation(inMsg);
		}
		else
		if(EventType.CLICK.equals(inMsg.getEvent())){
			outMsg = processMenuClick(inMsg);
		}
//		else if (MessageType.IMAGE.equals(inMsg.getMsgType())){
//			//outMsg = checkSession(inMsg);
//			outMsg = processImageMessage(inMsg);
//		}
		else if(EventType.VIEW.equals(inMsg.getEvent())){
			//outMsg = new OutMessage();
			outMsg = null;
		}
		else if(EventType.SUBSCRIBE.equals(inMsg.getEvent())){
			outMsg = null;
		}
		else if(EventType.UNSUBSCRIBE.equals(inMsg.getEvent())){
//			userMapper.unBind(inMsg.getFromUserName());
			outMsg = null;
		}
		else{
//			outMsg = checkSession(inMsg);
//			if(outMsg == null){
//				outMsg = new OutMessage();
//				outMsg.setContent("请不要发送无效的数据！");
//			}
//            return help(inMsg);
		}
		return outMsg;
	}

	
//	private OutMessage checkSession(InMessage inMsg) {
//		if (getWeixinUserByOpenId(inMsg.getFromUserName()) == null) {
//			BusinessException exception = new BusinessException(
//					ExceptionID.SESSION_EXPIRED);
//			OutMessage outMsg = new OutMessage();
//			outMsg.setContent(exception.getErrorMsgWithCode("zh_CN"));
//			return outMsg;
//		}
//		return null;
//	}
//
//    private OutMessage help(InMessage inMsg){
//        OutMessage outMsg = new OutMessage();
//        outMsg.setMsgType("transfer_customer_service");
//        outMsg.setFromUserName("ZhiXingGuanJia");
//        return outMsg;
//    }
//
//	public static UserEntity getWeixinUserByOpenId(String openId) {
//		HttpSession session = SessionManager.getSessionManager().findWeixinSession(openId);
//		if(session == null)return null;
//		UserEntity user = (UserEntity)session.getAttribute(SecurityService.WEIXIN_USER);
//		return user;
//	}
//
//	public OutMessage updateLocation(InMessage inMsg) {
//
//		HttpSession session = SessionManager.getSessionManager().findWeixinSession(inMsg.getFromUserName());
//		if(session != null && session.getAttribute(SecurityService.WEIXIN_USER) != null){
//			BMapPoint point = new BMapPoint();
//			point.setGps_Lat(inMsg.getLatitude());
//			point.setGps_Lon(inMsg.getLongitude());
//			session.setAttribute(SecurityService.WEIXIN_LOCATION, point);
//		}
//		return null;
//	}
	
	/**
	 * 事件推送消息解析
	 * @param inMsg
	 * @return
	 */
//	private OutMessage processEventMessage(InMessage inMsg) throws Exception {
//		
//		OutMessage outMsg = new OutMessage();
//		if (inMsg.getEvent().equals(EventType.SUBSCRIBE)) {//关注
//			//outMsg.setContent(MessageHelper.getInstance().getMessage("WELCOME_TXT"));
//		} else if (inMsg.getEvent().equals(EventType.UNSUBSCRIBE)) {
//			// 取消关注
//			userMapper.unBind(inMsg.getFromUserName());
//		} else if (inMsg.getEvent().equals(EventType.CLICK)) {
//			// 自定义微信功能菜单点击事件
//			return processMenuClick(inMsg);
//		} else if (inMsg.getEvent().equals(EventType.SCAN)) {//扫描带参数二维码事件,用户已关注时的事件推送
//
//		} else if (inMsg.getEvent().equals(EventType.LOCATION)){
//			// 上报位置
//			BMapPoint point = new BMapPoint();
//			point.setGps_Lat(inMsg.getLatitude());
//			point.setGps_Lon(inMsg.getLongitude());
//			BaseWeixinController.updateLocation(inMsg.getFromUserName(),point);
//		}
//		return outMsg;
//		
//	}
	
	/**
	 * 处理按钮点击消息
	 * @param inMsg
	 * @return
	 * @throws Exception 
	 */
	private OutMessage processMenuClick(InMessage inMsg) throws Exception {

		
//		if("logout".equals(inMsg.getEventKey())){
//			HttpSession session = SessionManager.getSessionManager().removeWeixinSession(inMsg.getFromUserName());
//			OutMessage outMsg = new OutMessage();
//			if(session != null){
//				outMsg.setContent("退出成功！");
//			}else{
//				outMsg.setContent("您已经退出系统！");
//			}
//			return outMsg;
//		}
		return null;
	}
	
	/**
	 * 图片上传消息解析
	 * @param inMsg
	 * @return
	 */
//	private OutMessage processImageMessage(InMessage inMsg) {
//		OutMessage outMsg = null;
//		// 先取得微信绑定用户的信息
//		//HttpSession session = SessionManager.getSessionManager().findWeixinSession(inMsg.getFromUserName());
//		UserEntity user = getWeixinUserByOpenId(inMsg.getFromUserName());
//		if(user == null) {
//			BusinessException exception = new BusinessException(
//					ExceptionID.SESSION_EXPIRED);
//			outMsg = new OutMessage();
//			outMsg.setContent(exception.getErrorMsgWithCode("zh_CN"));
//			return outMsg;
//		}else{
//			// 判断此用户是否有图片上传的任务
//			PicTaskFileEntity file = new PicTaskFileEntity();
//			file.setUserId(user.getId());
//			file.setPicUrl(inMsg.getPicUrl());
//			PicTaskStateEntity task = picTaskService.insertPicFileToTask(file);
//			outMsg = new OutMessage();
//			if (task != null) {
//				outMsg.setContent(task.getMsg());
//			}else{
//				outMsg.setContent("您没有需要上传照片的任务！");
//			}
//		}
//		return outMsg;
//	}
	
	public Map<String,Object> generateJSAPISignature(String url){
		return WxSignatureUtil.generateJSAPISignature(getJSAPIToken(), url);
	}

	public String getAccessToken(){
		return accessTokenService.getAccessToken();
	}

	public String getJSAPIToken(){
		return jsApiTicketService.getJsapiTicket();
	}
	
	
}
