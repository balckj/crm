package com.yidatec.security;

import com.yidatec.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author QuShengWen
 */
@Service
public class AuthUtil {

    @Autowired
    private MyInvocationSecurityMetadataSource myInvocationSecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    public void decide(User user,String resource)throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<ConfigAttribute> configAttributes =  myInvocationSecurityMetadataSource.getAttributes(resource);
        myAccessDecisionManager.decide(user.getAuthorities(), null, configAttributes);
    }
}
