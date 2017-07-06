package com.yidatec.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户名和密码验证过滤器
 *
 * @author QuShengWen
 */
public class MyUsernamePasswordAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {
    /**
     * 验证码键
     */
    public static final String SPRING_SECURITY_FORM_VALIDATE_KEY = "validateCode";

    /**
     * 验证码参数
     */
    private String validateCodeParameter = SPRING_SECURITY_FORM_VALIDATE_KEY;

    /**
     * 开始验证帐号和密码是否匹配
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: "
                            + request.getMethod());
        }
        // 检测验证码
//		checkValidateCode(request);
        return super.attemptAuthentication(request, response);
    }

    /**
     * 检测校验码是否匹配
     *
     * @param request HttpServletRequest
     */
    protected void checkValidateCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionValidateCode = obtainSessionValidateCode(session);
        // 让上一次的验证码失效
        session.setAttribute(validateCodeParameter, null);
        String validateCode = obtainValidateCode(request);
        if (StringUtils.isEmpty(validateCode)
                || StringUtils.isEmpty(sessionValidateCode)
                || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            throw new ValidateCodeException(validateCode);
        }
    }

    /**
     * 获取请求中的校验码
     *
     * @param request HttpServletRequest
     * @return String 校验码
     */
    private String obtainValidateCode(HttpServletRequest request) {
        return request.getParameter(validateCodeParameter);
    }

    /**
     * 获取session的校验码
     *
     * @param session HttpSession
     * @return String session的校验码
     */
    private String obtainSessionValidateCode(HttpSession session) {
        return (String) session.getAttribute(validateCodeParameter);
    }

    /**
     * 获取校验码参数
     *
     * @return String 校验码参数
     */
    public final String getValidateCodeParameter() {
        return validateCodeParameter;
    }

    /**
     * 设置校验码参数
     *
     * @param validateCodeParameter 校验码参数名
     */
    public void setValidateCodeParameter(String validateCodeParameter) {
        Assert.hasText(validateCodeParameter,
                "Validate Code parameter must not be empty or null");
        this.validateCodeParameter = validateCodeParameter;
    }
}
