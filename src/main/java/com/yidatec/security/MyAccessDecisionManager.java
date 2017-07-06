package com.yidatec.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * 访问决策管理器，该类用于判断当前用户是否有权限访问资源
 *
 * @author QuShengWen
 */

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();

        while (ite.hasNext()) {

            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig) ca).getAttribute();

            // ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //如果没有权限，抛出“访问拒绝”异常
        throw new AccessDeniedException("Access Denied");
    }

    public void decide(Collection<? extends GrantedAuthority> myAuthorities, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();

        while (ite.hasNext()) {

            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig) ca).getAttribute();

            // ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for (GrantedAuthority ga : myAuthorities) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //如果没有权限，抛出“访问拒绝”异常
        throw new AccessDeniedException("Access Denied");
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

}
