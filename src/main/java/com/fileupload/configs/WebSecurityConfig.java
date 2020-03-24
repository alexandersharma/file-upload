package com.fileupload.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth
	      // Defines three users with their passwords and roles
	      .inMemoryAuthentication()
	      .withUser("firstuser").password("firstuser").roles("USER")
	      .and()
	      .withUser("seconduser").password("seconduser").roles("USER")
	      .and()
	      .withUser("admin").password("admin").roles("ADMIN");
	    return;
	  }
  
	  /**
	   * Disable CSRF protection (to simplify this demo) and allow all user to access
	   */
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  
		  http
	      .csrf().disable()
	      .authorizeRequests()
	      	.antMatchers("/","/upload","/api/upload/**").permitAll()
	        .anyRequest().authenticated()
	        .and()
	      .formLogin()
	      .defaultSuccessUrl("/notifications", true)
	        .and()
	      .httpBasic();
	    return;
	  }


} 
