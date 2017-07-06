package com.yidatec.exception;

/**
 * 错误定义读取
 *
 * @author QuShengWen
 */
public interface IErrorLoader {

    /**
     * 获取错误消息定义模型
     *
     * @return ErrorResource
     */
    ErrorResource getResource();
}
