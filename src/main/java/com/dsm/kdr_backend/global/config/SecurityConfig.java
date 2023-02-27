package com.dsm.kdr_backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dsm.kdr_backend.global.exception.handler.AuthenticationEntryPointImpl;
import com.dsm.kdr_backend.global.jwt.FilterConfig;
import com.dsm.kdr_backend.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().and()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic().disable()

            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/product").authenticated()
            .antMatchers(HttpMethod.PUT, "/product/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/product/**").authenticated()

            .antMatchers(HttpMethod.POST, "/category").authenticated()
            .antMatchers(HttpMethod.DELETE, "/category/**").authenticated()

            .antMatchers(HttpMethod.POST, "/notice").authenticated()
            .antMatchers(HttpMethod.PUT, "/notice/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/notice/**").authenticated()
            .anyRequest().permitAll()

            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)

            .and().apply(new FilterConfig(jwtTokenProvider));
    }

}