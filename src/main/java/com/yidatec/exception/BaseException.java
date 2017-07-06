package com.yidatec.exception;


import com.yidatec.service.ResourceBundleService;

/**
 * 所有异常的基类，它加载异常消息，建立异常ID与消息的映射，支持多种语言的异常消息，和消息变量
 * 所有未定义的异常统称为“系统异常”它表示不应该系统不应该出现该异常，这些异常可能由系统配置错误导致的
 *
 * @author QuShengWen
 */
public abstract class BaseException extends Exception {

    private static final long serialVersionUID = 5528269045827762787L;

    /**
     * 异常ID，默认为系统异常ID：1000000。
     */
    private int errorId = ExceptionID.SYSTEM_EXCEPTION;

    /**
     * 异常消息变量值，消息中可有多个变量，因此变量值可有多个，用数组表示
     */
    private String[] txt = null;

    /**
     * 构造函数
     */
    protected BaseException() {
    }

    /**
     * 构造函数
     *
     * @param errId 异常ID
     */
    protected BaseException(int errId) {
        this.errorId = errId;
    }

    /**
     * 构造函数
     *
     * @param errId 异常ID
     * @param e     原始异常
     */
    protected BaseException(int errId, Exception e) {
        super(e);
        this.errorId = errId;
    }

    /**
     * 构造函数
     *
     * @param errId 异常ID
     * @param txt   异常消息变量值数组
     */
    protected BaseException(int errId, String... txt) {
        this.errorId = errId;
        this.txt = txt;
    }

    /**
     * 构造函数
     *
     * @param errId 异常ID
     * @param e     原始异常
     * @param txt   异常消息变量值数组
     */
    protected BaseException(int errId, Exception e, String... txt) {
        super(e);
        this.errorId = errId;
        this.txt = txt;
    }

    /**
     * 设置异常消息变量值数组
     *
     * @param txt 待设置异常消息变量值数组
     */
    public void setTxt(String... txt) {
        this.txt = txt;
    }

    /**
     * 获取异常ID
     *
     * @return int 异常ID
     */
    public int getErrorCode() {
        return this.errorId;
    }

    /**
     * 获取错误消息，包含了多种语言版本，可由语言国家代码来获取具体语言的消息文本
     *
     * @return ErrorInfo 包含了多种语言版本的错误消息对象
     */
    private ErrorInfo getErrorMsgMapping() {
        IErrorLoader loader = DefaultErrorLoader.getInstance();
        ErrorResource resource = loader.getResource();
        ErrorInfo info = resource.getErrorInfo("" + errorId);
        return info;
    }

    /**
     * 获取指定语言的错误消息
     *
     * @param lang 语言和国家的代码
     * @return 错误消息
     */
    public String getErrorMsg(String lang) {
        return getVarErrorMsg(lang);
    }

    /**
     * 获取由错误代码开始的指定语言的错误消息
     *
     * @param lang 语言和国家的代码
     * @return 错误消息
     */
    public String getErrorMsgWithCode(String lang) {
        return getErrorCode() + " : " + getVarErrorMsg(lang);
    }

    /**
     * 获取指定语言的未经消息变量值替换的原始错误消息
     *
     * @param lang 语言和国家的代码
     * @return 错误消息
     */
    private String getMsg(String lang) {
        return getErrorMsgMapping().getMsg(lang);
    }

    /**
     * 获取指定语言的并经消息变量值替换的原始错误消息
     *
     * @param lang 语言和国家的代码
     * @return 错误消息
     */
    private String getVarErrorMsg(String lang) {
        String errMsg = getMsg(lang);
        if (txt != null && txt.length > 0) {
            for (int i = 0; i < txt.length; i++) {
                if (txt[i] != null) {
                    if (txt[i].startsWith("i18n.")) {
                        txt[i] = ResourceBundleService.getMessage(txt[i].substring(5));
                    }
                    errMsg = errMsg.replaceAll("%" + i + "%", txt[i]);
                }
            }
        }
        return errMsg;
    }
}
