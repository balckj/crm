package com.yidatec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 安全过滤器链
 *
 * @author QuShengWen
 */

public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements
        Filter {
    /**
     * 安全元数据
     */
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

//    @Autowired
//    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
//        super.setAccessDecisionManager(myAccessDecisionManager);
//    }

    /**
     * 获取安全元素据对象
     *
     * @return SecurityMetadataSource
     */
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    /**
     * 执行授权检查
     *
     * @param request  ServletRequest
     * @param response ServletResponse
     * @param chain    FilterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    /**
     * 执行安全检查链
     *
     * @param fi FilterInvocation
     * @throws IOException
     * @throws ServletException
     */
    private void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    /**
//     * 获取安全元数据
//     *
//     * @return FilterInvocationSecurityMetadataSource
//     */
//    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
//        return securityMetadataSource;
//    }

//    /**
//     * 设置安全元数据
//     *
//     * @param securityMetadataSource
//     */
//    public void setSecurityMetadataSource(
//            FilterInvocationSecurityMetadataSource securityMetadataSource) {
//        this.securityMetadataSource = securityMetadataSource;
//    }


    public void init(FilterConfig arg0) throws ServletException {
    }

    public void destroy() {
    }

    /**
     * 获取安全对象类
     *
     * @return FilterInvocation.class
     */
    @Override
    public Class<? extends Object> getSecureObjectClass() {
        // 下面的MyAccessDecisionManager的supports方法必须放回true,否则会提醒类型错误
        return FilterInvocation.class;
    }
}