package com.study.config;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.security.access.hierarchicalroles.NullRoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

public class UrlAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private LinkedHashMap<RequestMatcher, String> requestMap = new LinkedHashMap<>();
    private RoleHierarchy roleHierarchy = new NullRoleHierarchy();

    public UrlAuthorizationManager(LinkedHashMap<RequestMatcher, String> requestMap) {
        this.requestMap = requestMap;
    }

    public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> supplier, RequestAuthorizationContext requestAuthorizationContext) {
        Authentication authentication = supplier.get();
        HttpServletRequest request = requestAuthorizationContext.getRequest();

        if (requestMap != null) {
            for (Map.Entry<RequestMatcher, String> entry : requestMap.entrySet()) {
                RequestMatcher requestMatcher = entry.getKey();
                if (requestMatcher.matches(request)) {
                    String authority = entry.getValue();
                    boolean isGranted = isGranted(authentication, authority);
                    return new AuthorityAuthorizationDecision(isGranted, AuthorityUtils.createAuthorityList(authority));
                }
            }
        }

        return new AuthorizationDecision(true);
    }

    private boolean isGranted(Authentication authentication, String authority) {
        return authentication != null && isAuthorized(authentication, authority);
    }

    private boolean isAuthorized(Authentication authentication, String authority) {
        Iterator<? extends GrantedAuthority> iter = getGrantedAuthorities(authentication).iterator();

        GrantedAuthority grantedAuthority;
        do {
            if (!iter.hasNext()) {
                return false;
            }
            grantedAuthority = iter.next();
        } while (!authority.equals(grantedAuthority.getAuthority()));

        return true;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Authentication authentication) {
        return this.roleHierarchy.getReachableGrantedAuthorities(authentication.getAuthorities());
    }
}
