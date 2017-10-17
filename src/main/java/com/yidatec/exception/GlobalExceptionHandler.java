package com.yidatec.exception;

import com.yidatec.controller.BaseController;
import com.yidatec.util.ConfProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        }else if(exception instanceof DuplicateKeyException){
            int index = exception.getCause().getMessage().lastIndexOf("for key");
            String field = exception.getCause().getMessage().substring(index+9,exception.getCause().getMessage().length()-1);
            logger.error(exception.getMessage(), exception);
            if("mobilePhone".equalsIgnoreCase(field)){
                message = BaseController.getErrorJson("电话号码重复");
            }else if("openId".equalsIgnoreCase(field)){
                message = BaseController.getErrorJson("微信标识重复");
            }else if("code".equalsIgnoreCase(field)){
                message = BaseController.getErrorJson("编号重复");
            }
            else if("name".equalsIgnoreCase(field)){
                message = BaseController.getErrorJson("名称重复");
            }else{
                be = new BusinessException(exception);
            }
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