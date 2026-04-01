package com.study.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.study.domain.auth.service.AuthorizationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private UserLogoutSucessHandler userLogoutSucessHandler;

    @Autowired
    private AuthorizationService authorizationService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * URL 기반 인가 관리자 Bean
     */
    @Bean
    public UrlAuthorizationManager urlAuthorizationManager() {
        UrlAuthorizationManager urlAuthorizationManager = new UrlAuthorizationManager(urlResourcesMapFactoryBean().getObject());
        urlAuthorizationManager.setRoleHierarchy(roleHierarchy());
        return urlAuthorizationManager;
    }

    /**
     * DB에서 받아온 URL 패턴과 Role을 매핑한 맵 생성
     */
    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setAuthorizationService(authorizationService);
        return urlResourcesMapFactoryBean;
    }

    /**
     * DB에서 가져온 Role 계층 구조
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        List<Map<String, Object>> roleList = authorizationService.getRoleHierarchy();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roleList.size(); i++) {
            if (i != 0) {
                sb.append("\n");
            }
            Map<String, Object> roleMap = roleList.get(i);
            sb.append(roleMap.get("path").toString());
        }
        log.info("#### Role Hierarchy: {}", sb);

        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(sb.toString());
        return roleHierarchy;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                (authorize) -> authorize
                .requestMatchers("/", "/signup", "/login", "/error", "/logout", "/test", "/rest/**").permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).access(urlAuthorizationManager())
                .anyRequest().authenticated()
            )
            .formLogin(
                form -> form
                .loginPage("/login") // GET
                .loginProcessingUrl("/auth") // POST
                .usernameParameter("userId")
                .passwordParameter("userPwd")
                .successHandler(new UserSuccessHandler()) // 인증 성공시
                .failureHandler(new UserLoginFailHandler()) // 인증 실패시
            )
            .logout(
                (logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(userLogoutSucessHandler)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true))
            .sessionManagement(
                session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/")
            );

        return http.build();
    }
}
