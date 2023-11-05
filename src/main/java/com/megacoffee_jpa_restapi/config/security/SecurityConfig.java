package com.megacoffee_jpa_restapi.config.security;

import com.megacoffee_jpa_restapi.config.jwt.JwtAuthenticationFilter;
import com.megacoffee_jpa_restapi.config.jwt.JwtAuthorizationFilter;
import com.megacoffee_jpa_restapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.RequestDispatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberRepository memberRepository;
    private final CorsFilter corsFilter;
    private final EncodingFilter encodingFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 특정 api 접근시 권한을 파악하도록 antMather()추가 예정
        // JWT 토큰 생성 필터 및 필터의 시간 처리 필터 추가 예정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(encodingFilter, JwtAuthorizationFilter.class)
                .addFilter(corsFilter)
                .formLogin().usernameParameter("id").disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))
                .authorizeRequests()
                .antMatchers("/api/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
