package com.zo.todoapp.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.beans.Encoder;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/", "/v1/tasks")
      .permitAll()
      .and()
      .formLogin()
      .loginPage("/login")
      .permitAll()
      .and()
      .logout()
      .permitAll();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {

    UserDetails user =
      User.withDefaultPasswordEncoder()
          .username("user")
          .password("user")
          .roles("USER")
          .build();

    return new InMemoryUserDetailsManager(user);
  }
}
