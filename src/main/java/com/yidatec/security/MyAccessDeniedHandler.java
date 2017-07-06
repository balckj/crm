package com.yidatec.security;

import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author QuShengWen
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.access.AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("{\"res\":" + 0 + ", \"txt\":\"" + new String("权限拒绝".getBytes(),"UTF-8") + "\"}");
        writer.flush();
        writer.close();
    }
}
