package com.yidatec.exception;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;

import java.io.InputStream;

/**
 * 加载错误消息定义
 *
 * @author QuShengWen
 */
public class DefaultErrorLoader implements IErrorLoader {

    /**
     * 错误消息的定义模型
     */
    private ErrorResource resource;

    /**
     * 单例
     */
    private static DefaultErrorLoader INSTANCE = new DefaultErrorLoader();

    /**
     * 消息资源定义文件
     */
    private String ERROR_CONFIG = "exception.xml";

    /**
     * 获取实例对象
     *
     * @return DefaultErrorLoader
     */
    public static DefaultErrorLoader getInstance() {
        return INSTANCE;
    }

    /**
     * 构造函数
     */
    private DefaultErrorLoader() {
        LoadMsgConf();
    }

    /**
     * 加载错误消息定义
     */
    protected void LoadMsgConf() {
        try {
            IBindingFactory factory = BindingDirectory
                    .getFactory(ErrorResource.class);
            IUnmarshallingContext uctx = factory.createUnmarshallingContext();
            InputStream is = getClass().getClassLoader().getResourceAsStream(ERROR_CONFIG);
            resource = (ErrorResource) uctx.unmarshalDocument(is, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取错误消息定义模型
     *
     * @return ErrorResource
     */
    public ErrorResource getResource() {
        return resource;
    }
}
