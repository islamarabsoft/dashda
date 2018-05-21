/**
 * 
 */
package com.dashda.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author mhanafy
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT USERNAME, PASSWORD, " + 
					"CASE WHEN A.ACTIVE IS NOT NULL THEN (U.ACTIVE AND A.ACTIVE) ELSE U.ACTIVE END AS ACTIVE " + 
					"FROM USER U " + 
					"LEFT JOIN EMPLOYEE E ON E.ID = U.EMPLOYEE_ID " + 
					"LEFT JOIN ACCOUNT A ON A.ID = E.ACCOUNT_ID WHERE U.USERNAME = ?")
			.authoritiesByUsernameQuery("SELECT USERNAME, PERMISSION FROM USER U " + 
					"INNER JOIN USER_ROLE UR ON UR.ID = U.USER_ROLE_ID AND U.USERNAME = ? " + 
					"INNER JOIN USER_ROLE_PERMISSION AS URP ON UR.ID = URP.USER_ROLE_ID " +
					"INNER JOIN PERMISSION AS P ON URP.PERMISSION_ID = P.ID AND P.PERMISSION_TYPE_ID = 1")
			.passwordEncoder(new BCryptPasswordEncoder());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.httpBasic().
	                realmName("Dashda Authintication").
	                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	                and().csrf().disable().
	                authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();

	    }

	    @Bean
	    public NoOpPasswordEncoder passwordEncoder() {
	        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	    }
	    
	    
			public static void main(String [] args){
				String encoded=new BCryptPasswordEncoder().encode("newagent");
				System.out.println(encoded);
			}
	    
}
