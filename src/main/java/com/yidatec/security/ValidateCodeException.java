package com.yidatec.security;

import org.springframework.security.core.AuthenticationException;

/**
 * 校验码错误异常
 * @author QuShengWen
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 494779576403392658L;

    /**
     * 构造函数
     *
     * @param msg 消息文本
     */
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
