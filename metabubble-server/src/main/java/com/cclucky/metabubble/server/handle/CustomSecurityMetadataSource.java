package com.cclucky.metabubble.server.handle;

import com.cclucky.metabubble.server.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求资源
        FilterInvocation fi = (FilterInvocation) object;
        String method = fi.getRequest().getMethod();
        String url = fi.getRequest().getRequestURI();
        // TODO: 2023/10/28 根据字段判断是否可访问
        List<String> roleNameList = roleMenuService.findRoleNameList(url);
        if (CollectionUtils.isEmpty(roleNameList)) {
            return SecurityConfig.createList("ROLE_ANONYMOUS");
        }
        // 根据当前请求url获取所需角色权限
        // 以SecurityConfig的内置方式返回角色数据
        return SecurityConfig.createList(roleNameList.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
