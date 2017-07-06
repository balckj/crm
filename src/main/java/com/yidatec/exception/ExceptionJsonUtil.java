package com.yidatec.exception;


import com.yidatec.controller.BaseController;
import com.yidatec.util.ConfProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 将异常转换为json文本发送到浏览器客户端，格式为：{"res":0,"txt":"content"},
 * 所有异常json文本的res一定等于0 ，如果异常不是BaseException对象，都将被转换为系统异常
 *
 * @author QuShengWen
 */
@Service
public class ExceptionJsonUtil {

    @Autowired
    ConfProperties confProperties;
    /**
     * 日志记录
     */
    protected static final Log logger = LogFactory
            .getLog(ExceptionJsonUtil.class);

    /**
     * 转换异常，并发送至浏览器客户端
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param exception 待转换的异常
     */
    public void sendException(HttpServletRequest request,
                                     HttpServletResponse response, Exception exception) {

        response.setCharacterEncoding("UTF-8");
        String currentLang = confProperties.getSystemLocale();
        String message = null;
        BaseException be = null;
        if (exception instanceof WeixinShellException) {
            be = (BaseException) exception;
            message = be.getErrorMsgWithCode(currentLang);
            message = confProperties.getWeixinShellException(message);
        }else if (exception instanceof WrapBusinessException) {
            be = (BaseException) exception;
            message = be.getErrorMsgWithCode(currentLang);
            message = confProperties.getWrapExceptionHtml(message);
        }else if (exception instanceof WebIframeException) {
            be = (BaseException) exception;
            message = be.getErrorMsgWithCode(currentLang);
            message = confProperties.getWebIframeExceptionHtml(message);
        }
        else if (exception instanceof BaseException) {
            be = (BaseException) exception;
        }
//        else if(exception instanceof DuplicateKeyException){
//            be = new BusinessException(ExceptionID.DATA_SUBMITTED,exception);
//        }

        else {
            be = new BusinessException(exception);
        }
        if (message == null) {
            message = be.getErrorMsgWithCode(currentLang);
            message = BaseController.getErrorJson(message);
        }
        try {
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(message);
            writer.flush();
            writer.close();
            logger.error(message, be);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
