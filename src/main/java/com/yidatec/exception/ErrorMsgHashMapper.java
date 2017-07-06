package com.yidatec.exception;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * msg元素解组
 * @author QuShengWen
 */
public class ErrorMsgHashMapper implements IUnmarshaller, IAliasable {
    /**
     * 解组元素
     */
    private static final String ENTRY_ELEMENT_NAME = "msg";

    /**
     * 名称空间
     */
    private String m_uri;

    /**
     * 当前元素名称
     */
    private String m_name;


    /**
     * 构造函数
     */
    public ErrorMsgHashMapper() {
        m_uri = null;
        m_name = ENTRY_ELEMENT_NAME;
    }


    /**
     * 当前元素是否是指定的解组元素
     *
     * @param ctx 解组环境
     * @return boolean
     * @throws JiBXException
     */
    @Override
    public boolean isPresent(IUnmarshallingContext ctx) throws JiBXException {
        return ctx.isAt(m_uri, m_name);
    }

    /**
     * 解组
     *
     * @param obj  Map<String, String>
     * @param ictx 解组环境
     * @return Map<String, String>
     * @throws JiBXException
     */
    @Override
    public Object unmarshal(Object obj, IUnmarshallingContext ictx)
            throws JiBXException {
        // make sure we're at the appropriate start tag
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        if (!ctx.isAt(m_uri, m_name)) {
            ctx.throwStartTagNameError(m_uri, m_name);
        }

        // create new hashmap if needed
        Map<String, String> map = (HashMap<String, String>) obj;
        if (map == null) {
            map = new HashMap<String, String>(3);
        }

        // process all entries present in document
        while (ctx.isAt(m_uri, ENTRY_ELEMENT_NAME)) {
            ErrorMsg value = (ErrorMsg) ctx.unmarshalElement();
            String key = value.getLang();
            String msgTxt = value.getMessage();
            map.put(key, msgTxt);
        }
        return map;
    }

}
