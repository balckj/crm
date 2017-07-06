package com.yidatec.security;


import com.yidatec.exception.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 授权失败处理
 *
 * @author QuShengWen
 */
@Service
public class MyAuthenticationFailureHandler implements
        AuthenticationFailureHandler {

    @Autowired
    ExceptionJsonUtil exceptionJsonUtil;
    /**
     * 日志记录器
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 是否可以多次重试
     */
    private boolean maxTryEnable = false;

    /**
     * 重试次数
     */
    private int maxTryCount = 3;

    /**
     * 重试次数的键
     */
    public final static String TRY_MAX_COUNT = "tryMaxCount";

    /**
     * 用户dao接口
     */
//    private UserMapper userMapper;

    /**
     * 构造函数
     */
    public MyAuthenticationFailureHandler() {
    }

    /**
     * 判断是否开启重试功能
     *
     * @return boolean
     */
    public boolean isMaxTryEnable() {
        return maxTryEnable;
    }

    /**
     * 设置是否开启重试功能
     *
     * @param maxTryEnable boolean
     */
    public void setMaxTryEnable(boolean maxTryEnable) {
        this.maxTryEnable = maxTryEnable;
    }

    /**
     * 获取最大重试次数
     *
     * @return int
     */
    public int getMaxTryCount() {
        return maxTryCount;
    }

    /**
     * 设置最大重试次数
     *
     * @param maxTryCount 最大重试次数
     */
    public void setMaxTryCount(int maxTryCount) {
        this.maxTryCount = maxTryCount;
    }

    /**
     * 授权失败处理，发送错误消息到浏览器客户端
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param exception AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. ");
            return;
        }
        BaseException currEx = null;
        if (exception instanceof LockedException) {
            currEx = new BusinessException(ExceptionID.LOCKED_ACCOUNT, exception);
        } else if (exception instanceof DisabledException) {
            currEx = new BusinessException(ExceptionID.DISABLED_ACCOUNT, exception);
        }
        if (currEx != null) {
            exceptionJsonUtil.sendException(request, response, currEx);
            return;
        }

        checkMaxTryCount(request, response, exception);
    }

    /**
     * 检测是否达到最大重试次数，如果达到抛出相应的异常信息
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param exception
     */
    private void checkMaxTryCount(HttpServletRequest request,
                                  HttpServletResponse response, AuthenticationException exception) {
        HttpSession session = request.getSession();

        BaseException currEx = null;

        if (exception instanceof UsernameNotFoundException) {
            currEx = new BusinessException(ExceptionID.INVALID_ACCOUNT,
                    exception);
        }
//        else if (exception instanceof ValidateCodeException) {// validate code
//            // erro
//            currEx = new BusinessException(ExceptionID.INVALID_VALIDATE_CODE, exception);
//        }
        else if (exception instanceof BadCredentialsException) {// password
            // error
            currEx = new BusinessException(ExceptionID.INVALID_PASSWORD,
                    exception);
        }
//        else if (exception instanceof CredentialsExpiredException) {
//            currEx = new BusinessException(ExceptionID.PASSWORD_EXPIRED,
//                    exception);
//        }
        else {
            currEx = new BusinessException(exception);
        }

        if (maxTryEnable) {

            Integer tryCount = (Integer) session.getAttribute(TRY_MAX_COUNT);
            if (tryCount == null) {
                tryCount = 0;
            }
            session.setAttribute(TRY_MAX_COUNT, ++tryCount);
            if (tryCount == maxTryCount) {
                // userMapper.updateUserStatus(status, account)
            }
            BaseException tryEx = null;
            if (tryCount >= maxTryCount) {
                tryEx = new BusinessException(ExceptionID.REACH_MAX_TRY);
            }

            if (tryEx == null) {
                exceptionJsonUtil.sendException(request, response, currEx);
            } else {
                MultiException multiException = new MultiException();
                multiException.AddException(currEx);
                multiException.AddException(tryEx);
                exceptionJsonUtil.sendException(request, response,
                        multiException);
            }
        } else
            exceptionJsonUtil.sendException(request, response, currEx);

    }

    /**
     * 清除session中临时的授权相关的数据
     *
     * @param request HttpServletRequest
     */
    protected final void clearAuthenticationAttributes(
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}