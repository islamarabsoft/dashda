/**
 * 
 */
package com.dashda.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author mhanafy
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	DataSource dataSource;

	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled" + " from users where username=?")
				.authoritiesByUsernameQuery("select username, authority " + "from authorities where username=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll().antMatchers("/agent/**").hasRole("AGENT")
				.antMatchers("/manager/**")
				.hasRole("MANAGER").antMatchers("/admin/**").hasRole("ADMIN")
//				.and().formLogin().loginPage("/login")
//				.permitAll().and().logout().permitAll();
				.and().httpBasic();  // Authenticate users with HTTP basic authentication
	
	}
	
//	public static void main(String [] args){
//		String encoded=new BCryptPasswordEncoder().encode("agent");
//		System.out.println(encoded);
//	}
}
