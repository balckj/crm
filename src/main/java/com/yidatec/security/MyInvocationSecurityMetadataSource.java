package com.yidatec.security;

import com.yidatec.model.Permission;
import com.yidatec.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统内功能权限的配置，每个url是一个资源，一个资源可能有多个角色可以访问
 * 这个类建立了资源与角色的对应关系，并驻留在内存中，当更改功能权限，需要重新登录
 * @author QuShengWen
 */
@Service

public class MyInvocationSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private SecurityData securityData;

    /**
     * 权限与角色的映射
     */
    private HashMap<String, Collection<ConfigAttribute>> resourceMap = null;

//    @PostConstruct
//    public void init() {
//        if (resourceMap == null) {
//            loadAllPermissionAndRoles();
//        }
//    }

    /**
     * 加载所有资源与角色的对应关系
     */
    private void loadAllPermissionAndRoles() {

        this.resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<Permission> permissionList = securityData
                .findAllPermissionWithRole();
        for (Permission item : permissionList) {
            resourceMap.put(item.getUrl(), convert(item.getRoleList()));
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        if (resourceMap == null) {
            loadAllPermissionAndRoles();
        }
        for (Collection<ConfigAttribute> entry : resourceMap.values()) {
            if (entry != null)
                allAttributes.addAll(entry);
        }

        return allAttributes;
    }

    /**
     * 获得有权限访问请求的资源的角色列表
     *
     * @param object 请求
     * @return 角色配置列表
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        if (resourceMap == null) {
            loadAllPermissionAndRoles();
        }
        Iterator<String> it = resourceMap.keySet().iterator();

        while (it.hasNext()) {
            String resURL = it.next();
            if (resURL != null) {
                AntPathRequestMatcher pathMatcher = new AntPathRequestMatcher(resURL);
                if (pathMatcher.matches(request)) {
                    Collection<ConfigAttribute> returnCollection = resourceMap
                            .get(resURL);
                    return returnCollection;
                }
            }
        }
        return null;
    }

    public Collection<ConfigAttribute> getAttributes(String url){
        if (resourceMap == null) {
            loadAllPermissionAndRoles();
        }
        Iterator<String> it = resourceMap.keySet().iterator();
        while (it.hasNext()) {
            String resURL = it.next();
            if (resURL != null) {
//                AntPathRequestMatcher pathMatcher = new AntPathRequestMatcher(resURL);
//                if (pathMatcher.matches(request)) {
                if(url.contains(resURL)){
                    Collection<ConfigAttribute> returnCollection = resourceMap
                            .get(resURL);
                    return returnCollection;
                }
            }
        }
        return null;
    }

    /**
     * 按照spring security要求，始终返回true
     *
     * @param arg0
     * @return
     */
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    /**
     * 自定义方法，将List<RoleEntity>集合转换为框架需要的Collection<ConfigAttribute>集合
     *
     * @return
     */
    private Collection<ConfigAttribute> convert(List<Role> roleList) {

        List<ConfigAttribute> roleConfiglist = new ArrayList<ConfigAttribute>();

        if(roleList != null) {
            for (Role role : roleList) {
                roleConfiglist.add(new SecurityConfig(role.getName()));
            }
        }
        if (roleConfiglist.size() == 0) {
            roleConfiglist.add(new SecurityConfig(UUID.randomUUID().toString())); // all url should be controled.
        }

        return roleConfiglist;
    }

    /**
     * 清除资源角色配置
     */
    public void clear() {
        if (resourceMap != null) {
            resourceMap.clear();
        }
        resourceMap = null;
    }

    /**
     * 重新加载配置
     */
    public void refresh() {
        if(securityData != null){
            securityData.refresh();
        }
        clear();
        loadAllPermissionAndRoles();
    }

}
