package com.study.config;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.study.domain.auth.AuthorizationService;

public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, String>> {

    private AuthorizationService authorizationService;
    private LinkedHashMap<RequestMatcher, String> resourceMap;

    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public LinkedHashMap<RequestMatcher, String> getObject() {
        if (resourceMap == null) {
            init();
        }
        return resourceMap;
    }

    private void init() {
        resourceMap = authorizationService.getResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
