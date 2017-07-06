package com.yidatec.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权成功处理器
 *
 * @author QuShengWen
 */
public class MyAuthenticationSuccessHandler implements
        AuthenticationSuccessHandler {
    /**
     * 日志记录器
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 向客户端发送成功应答
     *
     * @param request        HttpServletRequest
     * @param response       HttpServletResponse
     * @param authentication Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        // logger.info(")

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. ");
            return;
        }

        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            String message = "{\"res\":1, \"txt\":\"认证成功\"}";
            writer.write(message);
            writer.flush();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            clearAuthenticationAttributes(request);
        }
    }

    /**
     * 清除session中临时的授权相关的数据
     *
     * @param request HttpServletRequest
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}