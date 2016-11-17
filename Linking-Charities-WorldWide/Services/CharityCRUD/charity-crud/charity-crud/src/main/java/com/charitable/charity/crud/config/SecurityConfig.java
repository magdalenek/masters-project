package com.charitable.charity.crud.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


public class SecurityConfig {

    /*

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
                .loginPage("/admin/login1").failureUrl("/admin/login1?error").permitAll().and()
                .logout().permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin")
                .roles("ADMIN", "USER").and().withUser("user").password("user")
                .roles("USER");
    }
    */
}
