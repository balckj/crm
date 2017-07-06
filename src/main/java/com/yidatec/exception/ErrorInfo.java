package com.yidatec.exception;

import java.util.Map;

/**
 * 异常信息模型，容纳多种语言版本的异常消息文本
 *
 * @author QuShengWen
 */
public class ErrorInfo {
    /**
     * 错误消息Id
     */
    private String id;

    /**
     * 错误消息名称
     */
    private String name;

    /**
     * 错误消息的语言代码与文本的映射
     */
    private Map<String, String> msgMapping;

    /**
     * 获取错误消息的代码
     *
     * @return String 错误消息的代码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置错误消息代码
     *
     * @param id 待设置的错误消息的代码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取错误消息的名称
     *
     * @return 错误消息的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置错误消息的名称
     *
     * @param name 错误消息的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取错误消息的语言代码与文本的映射
     *
     * @return Map<String, String>
     */
    public Map<String, String> getMsgMapping() {
        return msgMapping;
    }

    /**
     * 设置错误消息的语言代码与文本的映射
     *
     * @param msgMapping 待设置的值
     */
    public void setMsgMapping(Map<String, String> msgMapping) {
        this.msgMapping = msgMapping;
    }

    /**
     * 获取指定语言版本的消息文本
     *
     * @param key 语言代码
     * @return String 错误消息文本
     */
    public String getMsg(String key) {
        return msgMapping.get(key);
    }
}
