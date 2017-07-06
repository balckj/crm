package com.yidatec.exception;

import java.util.Map;

/**
 * errorResource元素的映射模型，它容纳所有消息定义
 *
 * @author QuShengWen
 */
public class ErrorResource {

    /**
     * 所有消息Id与消息文本的映射
     */
    public Map<String, ErrorInfo> errorMapping;

    /**
     * 构造函数
     */
    public ErrorResource() {

    }

    /**
     * 获取错误消息映射
     *
     * @return Map<String, ErrorInfo>
     */
    public Map<String, ErrorInfo> getErrorMapping() {
        return errorMapping;
    }

    /**
     * 设置错误消息映射
     *
     * @param errorMapping 待设置的值
     */
    public void setErrorMapping(Map<String, ErrorInfo> errorMapping) {
        this.errorMapping = errorMapping;
    }

    /**
     * 获取指定错误代码的消息文本
     *
     * @param errorId 错误代码
     * @return ErrorInfo  消息文本
     */
    public ErrorInfo getErrorInfo(String errorId) {
        return errorMapping.get(errorId);
    }
}
