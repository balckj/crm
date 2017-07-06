package com.yidatec.wechat.msg;

/**
 * 事件类型
 * @author Lance
 *
 */
public class EventType {

	/**
	 * 订阅
	 */
	public final static String SUBSCRIBE = "subscribe";
	
	/**
	 * 取消订阅
	 */
	public final static String UNSUBSCRIBE = "unsubscribe";
	
	/**
	 * 点击事件
	 */
	public final static String CLICK = "CLICK";
	
	/**
	 * 用户已关注时的事件推送
	 */
	public final static String SCAN = "SCAN";
	
	/**
	 * 获取用户地理位置
	 */
	public final static String LOCATION = "LOCATION";
	
	/**
	 * 点击菜单
	 */
	public final static String VIEW = "VIEW";
}
