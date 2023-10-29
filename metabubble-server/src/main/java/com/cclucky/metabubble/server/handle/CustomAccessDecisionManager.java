package com.cclucky.metabubble.server.handle;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限决策
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        List<String> collect = configAttributes.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (collect.contains("ROLE_ANONYMOUS") || collect.contains(authority.getAuthority())) {
                return;
            }
        }
        throw new RuntimeException("无权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
