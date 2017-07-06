package com.yidatec.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务异常，容纳多条消息文本
 * @author QuShengWen
 */
public class MultiException extends BaseException {
    private static final long serialVersionUID = -4026961372431548242L;

    /**
     * 异常消息列表
     */
    private List<BaseException> exList = new ArrayList<BaseException>(10);

    /**
     * 构造函数
     */
    public MultiException() {
    }

    /**
     * 添加异常消息
     *
     * @param ex 异常消息
     */
    public void AddException(BaseException ex) {
        if (ex != null)
            exList.add(ex);
    }

    /**
     * 获取错误消息文本，没有错误代码
     *
     * @param lang 语言和国家的代码
     * @return String 错误消息文本
     */
    public String getErrorMsg(String lang) {
        StringBuilder sb = new StringBuilder(50);
        for (BaseException bEx : exList) {
            sb.append(bEx.getErrorMsg(lang) + "\\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 2);
        }
        return sb.toString();
    }

    /**
     * 获取错误消息文本，有错误代码
     *
     * @param lang 语言和国家的代码
     * @return String 错误消息文本
     */
    public String getErrorMsgWithCode(String lang) {
        StringBuffer sb = new StringBuffer(50);
        for (BaseException bEx : exList) {
            sb.append(bEx.getErrorMsgWithCode(lang) + "\\n");
        }
        if (sb.length() > 0) {
            return sb.substring(0, (sb.length() - 2));
        }
        return sb.toString();
    }

    /**
     * 错误消息数量
     *
     * @return int
     */
    public int getCount() {

        return exList.size();

    }
}
