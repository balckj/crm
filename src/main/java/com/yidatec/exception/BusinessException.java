package com.yidatec.exception;

/**
 * 业务异常，仅能容纳一条消息文本
 *
 * @author QuShengWen
 */
public class BusinessException extends BaseException implements ExceptionID {

    private static final long serialVersionUID = 1155465416910255865L;

    /**
     * 构造函数,缺省为"系统异常"
     */
    public BusinessException() {
        super(SYSTEM_EXCEPTION);
    }

    /**
     * 构造函数,缺省为"系统异常"
     *
     * @param source 原始异常
     */
    public BusinessException(Exception source) {
        super(SYSTEM_EXCEPTION, source);
    }

    /**
     * 构造函数
     *
     * @param errorCode 异常代码
     */
    public BusinessException(int errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     *
     * @param errorCode 异常代码
     * @param source    原始异常
     */
    public BusinessException(int errorCode, Exception source) {
        super(errorCode, source);
    }

    /**
     * 构造函数
     *
     * @param errorCode 异常代码
     * @param txt       异常消息变量值
     */
    public BusinessException(int errorCode, String... txt) {
        super(errorCode, txt);
    }

    /**
     * 构造函数
     *
     * @param errorCode 异常代码
     * @param source    原始异常
     * @param txt       异常消息变量值
     */
    public BusinessException(int errorCode, Exception source, String... txt) {
        super(errorCode, source, txt);
    }

}
