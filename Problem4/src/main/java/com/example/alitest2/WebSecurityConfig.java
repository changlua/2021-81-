package com.example.alitest2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @ClassName WebSecurityConfig
 * @Author ChangLu
 * @Date 2021/10/31 14:17
 * @Description TODO
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
                .headers().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/endpoints").hasAnyRole("USER")
                .pathMatchers("/users").hasAnyRole("ADMIN")
                .pathMatchers("/ws/test").hasAnyRole("TEST") // 该行勿改动，否则影响评分
                .pathMatchers("/ws/**").hasAnyRole("admin")
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }

}
