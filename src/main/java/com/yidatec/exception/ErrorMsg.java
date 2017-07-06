package com.yidatec.exception;

/**
 * 错误消息模型
 *
 * @author QuShengWen
 */
public class ErrorMsg {
    /**
     * 语言代码
     */
    private String lang;

    /**
     * 消息文本
     */
    private String message;

    /**
     * 获取语言代码
     *
     * @return String 语言代码
     */
    public String getLang() {
        return lang;
    }

    /**
     * 设置语言代码
     *
     * @param lang 待设置的语言代码
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * 获取异常消息文本
     *
     * @return String 异常消息文本
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置异常消息文本
     *
     * @param message 待设置的异常消息文本
     */
    public void setMessage(String message) {
        this.message = message;
    }


}
