package com.yidatec.exception;

/**
 * 微信外壳异常，仅是一个标识，它应与ConfigProperties.getWeixinShellException一起使用
 * @author QuShengWen
 */
public class WebIframeException extends BaseException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 原始异常
     */
    BaseException exception;

    /**
     * 构造函数
     *
     * @param exception 原始异常
     */
    public WebIframeException(Exception exception) {
        if (exception instanceof BaseException)
            this.exception = (BaseException) exception;
        else {
            this.exception = new BusinessException(exception);
        }
    }

    /**
     * 设置消息变量值
     *
     * @param txt 待设置异常消息变量值数组
     */
    public void setTxt(String... txt) {
        exception.setTxt(txt);
    }

    /**
     * 获取错误代码
     *
     * @return int
     */
    public int getErrorCode() {
        return exception.getErrorCode();
    }


    /**
     * 获取指定语言的错误消息
     *
     * @param lang 语言和国家的代码
     * @return String
     */
    public String getErrorMsg(String lang) {
        return exception.getErrorMsg(lang);
    }

    /**
     * 获取以错误代码开始的错误消息
     *
     * @param lang 语言和国家的代码
     * @return String
     */
    public String getErrorMsgWithCode(String lang) {
        return exception.getErrorMsgWithCode(lang);
    }
}
