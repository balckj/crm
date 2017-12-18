package com.yidatec.controller;


import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Role;
import com.yidatec.model.User;
import com.yidatec.service.UserService;
import com.yidatec.util.ConfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;


/**
 * 所有Controller的基类，提供了基本的通用方法
 * 
 * @author QuShengWen
 */
public abstract class BaseController {

	@Autowired
	ConfProperties confProperties;

	@Autowired
	UserMapper userMapper;

	public static final String WEB_USER = "web_user";

	/**
	 * 返回表示请求处理成功的Json字符串
	 * 
	 * @param txt
	 *            成功消息文本
	 * @return Json 成功消息
	 */
	public static final String getSuccessJson(String txt) {

		return getResultJson(1, txt);
	}

	/**
	 * 返回表示请求处理失败的Json字符串
	 * 
	 * @param txt
	 *            失败消息文本
	 * @return Json 失败消息
	 */
	public static final String getErrorJson(String txt) {
		return getResultJson(0, txt);
	}

	/**
	 * 返回表示请求处理结果的Json字符串
	 * 
	 * @param resCode
	 *            结果代码，1表示成功；0表示失败
	 * @param txt
	 *            成功或失败的消息文本
	 * @return Json 结果
	 */
	public static final String getResultJson(int resCode, String txt) {
		if (txt == null)
			txt = "";
//		if (resCode <= 0)
//			resCode = 0;
//		else if (resCode > 0)
//			resCode = 1;
		return "{\"res\":" + resCode + ", \"txt\":\"" + txt + "\"}";
	}

	/**
	 * 获取当前web登录用户信息，它包含了当前用户的功能权限。
	 *
	 * @return UserEntity 当前登录用户
	 */
	public User getWebUser() {
//		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//		User user = (User)session.getAttribute(WEB_USER);
//		return user;
		Object tmp = SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		if(tmp instanceof User){
			return (User)tmp;
		}
		return null;
	}
//
//	/**
//	 * 服务器端验证结果处理
//	 * @param result
//	 * @throws BusinessException
//	 */
//	public void valid(BindingResult result) throws Exception {
//		if(result.hasErrors()) {
//
//			MultiException ex = new MultiException();
//			List<FieldError> listFieldError =  result.getFieldErrors();
//			for (FieldError fe : listFieldError) {
//
//				//if(fe.getArguments() != null && fe.getArguments().length>1){
//                //ResourceBundleService.getMessage(fe.getDefaultMessage(),fe.getRejectedValue())
//                if(fe.getRejectedValue() instanceof DataItemEntity){
//                    String label = ((DataItemEntity)fe.getRejectedValue()).getLabel();
//                    String msg = ResourceBundleService.getMessage(fe.getDefaultMessage()).replaceAll("\\{label\\}",label);
//                    ex.AddException(new BusinessTextException(msg));
//                }else{
//                    ex.AddException(new BusinessTextException(ResourceBundleService.getMessage(fe.getDefaultMessage(),fe.getArguments())));
//                }
//
//				//}
//			}
//
//			if (ex.getCount() > 0)
//				throw ex;
//        }
//	}
	
	

	/**
	 * 计算分页起点
	 * 
	 * @param page
	 *            当前页号
	 * @param rows
	 *            每页记录数
	 * @return 当前页第一个记录的在所有记录里的索引
	 */
	public int getOffset(int page, int rows) {
		if (page < 1) {
			page = 1;
		}
		return (page - 1) * rows;
	}

	/**
	 * 构造Like参数
	 * 
	 * @param item
	 *            Sql like参数的文本值
	 * @return String like参数，包含%
	 */
	public String like(String item) {
		return item == null || item.isEmpty() ? item : "%" + item + "%";
	}

//	protected Object processValidationRes(BindingResult result){
//		result.getFieldErrors();
//	}

	public boolean isAdmin(){
		User u = getWebUser();
		return isAdmin(u);
	}
	public boolean isAdmin(User u){
		if(u != null) {
			List<Role> roleList = u.getRoleList();
			if (roleList != null) {
				for (Role r : roleList) {
					if (r.getId().equalsIgnoreCase(confProperties.getAdminRoleId())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isAdmin(String userId){
		User u = userMapper.findById(userId);
		return isAdmin(u);
	}
}
