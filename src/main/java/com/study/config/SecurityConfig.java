package com.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.study.domain.member.UserInfoService;

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
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                (authorize) -> authorize
                // .requestMatchers("/signup", "/", "/login").permitAll()
                .requestMatchers("/", "/signup", "/login", "/error", "/logout", "/test", "/rest/**").permitAll()
//                .requestMatchers("/**").access(new UrlAuthorizationManager())
//                .requestMatchers(new AntPathRequestMatcher("/**")).access(urlAuthorizationManager())
                .anyRequest().authenticated()
//                .anyRequest().access(new UrlAuthorizationManager())
                
            )
            // .httpBasic(AbstractHttpConfigurer::disable)
            // .formLogin(AbstractHttpConfigurer::disable)
            .formLogin(
                form -> form
                .loginPage("/login") // GET
                .loginProcessingUrl("/auth") // POST
                .usernameParameter("userId")
                .passwordParameter("userPwd")
                .successHandler(new UserSuccessHandler()) // 인증 성공시
				.failureHandler(new UserLoginFailHandler()) // 인증 실패시
                // .defaultSuccessUrl("/", true) //handler를 사용하려면 주석처리
                // .permitAll() // 모든권한(테스트에만 사용)
            )
            .logout(
                (logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // .logoutSuccessUrl("/logout")
                // .addLogoutHandler(userLogoutHandler)// 로그아웃 성공 후 이동할 URL 설정(단순 로그아웃)-> 아래 로그아웃success핸들러로 로그아웃 구현
				.logoutSuccessHandler(userLogoutSucessHandler)  // 로그아웃 성공 후 이동할 URL 설정(로그아웃 성공시)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true))
            .sessionManagement(
                session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                // SessionCreationPolicy.ALWAYS        - 스프링시큐리티가 항상 세션을 생성
                // SessionCreationPolicy.IF_REQUIRED - 스프링시큐리티가 필요시 생성(기본) 
                // SessionCreationPolicy.NEVER           - 스프링시큐리티가 생성하지않지만, 기존에 존재하면 사용
                // SessionCreationPolicy.STATELESS     - 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음 
//                .maximumSessions(-1) // 최대 허용 가능 세션 수, -1 : 무제한 로그인 세션 허용
                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true) // 동시 로그인 차단, false : 기존 세션 만료(default)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/") // 세션이 만료된 경우 이동 할 페이지
            );

        return http.build();
    }

}
