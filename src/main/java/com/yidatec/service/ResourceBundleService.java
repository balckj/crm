package com.yidatec.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
/**
 * @author QuShengWen
 */
@Service("resourceBundleService")
public class ResourceBundleService implements ApplicationContextAware {

	private static ApplicationContext appContext;
	private static MessageSourceAccessor messages;

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		appContext = context;
	}

	public static MessageSourceAccessor getMessageSource() {
		if (messages == null) {
			MessageSource drms = (MessageSource) appContext
					.getBean("messageSource");
			messages = new MessageSourceAccessor(drms);
			
		}
		return messages;
	}

	public static String getMessage(String code) {
		try {
			return getMessageSource().getMessage(code);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMessage(String code, Object[] args) {
		try {
			return getMessageSource().getMessage(code, args);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMessage(String code, Object[] args,
			String defaultMessage) {
		try {
			return getMessageSource().getMessage(code, args, defaultMessage);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMessage(String code, String defaultMessage) {
		try {
			return getMessageSource().getMessage(code, defaultMessage);
		} catch (Exception e) {
			return null;
		}
	}
}
