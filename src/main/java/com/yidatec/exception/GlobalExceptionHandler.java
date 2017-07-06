package com.yidatec.exception;

import com.yidatec.controller.BaseController;
import com.yidatec.util.ConfProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author QuShengWen
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ConfProperties confProperties;
    /**
     * 日志记录
     */
    protected static final Log logger = LogFactory
            .getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object jsonErrorHandler(HttpServletRequest req, Exception exception) throws Exception {
//        response.setCharacterEncoding("UTF-8");
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
        logger.error(message, be);
        return message;
    }

}