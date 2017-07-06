package com.yidatec.exception;

/**
 * 业务异常，仅能容纳一条消息文本
 *
 * @author QuShengWen
 */
public class BusinessTextException extends BaseException implements ExceptionID {

    private static final long serialVersionUID = 1155465416910255865L;

    private String message;
    /**
     * 构造函数,缺省为"系统异常"
     */
    public BusinessTextException(String message) {
        super();
        this.message = message;
    }
    
    /**
	 * 获取异常ID
	 * 
	 * @return int 异常ID
	 */
	public int getErrorCode() {
		return 0;
	}

    /**
	 * 获取指定语言的错误消息
	 * 
	 * @param lang
	 *            语言和国家的代码
	 * @return 错误消息
	 */
	public String getErrorMsg(String lang) {
		return message;
	}

	/**
	 * 获取由错误代码开始的指定语言的错误消息
	 * 
	 * @param lang
	 *            语言和国家的代码
	 * @return 错误消息
	 */
	public String getErrorMsgWithCode(String lang) {
		return getErrorMsg(lang);
	}

}
