package com.mercury.SpringBootRestDemo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mercury.SpringBootRestDemo.security.handler.AccessDeniedHandlerImpl;
import com.mercury.SpringBootRestDemo.security.handler.AuthenticationEntryPointImpl;
import com.mercury.SpringBootRestDemo.security.handler.AuthenticationFailureHandlerImpl;
import com.mercury.SpringBootRestDemo.security.handler.AuthenticationSuccessHandlerImpl;
import com.mercury.SpringBootRestDemo.security.handler.LogoutSuccessHandlerImpl;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsServiceImpl;

	@Autowired
	private AuthenticationEntryPointImpl authenticationEntryPointImpl;

	@Autowired
	private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

	@Autowired
	private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

	@Autowired
	private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;

	public void configure(HttpSecurity http) throws Exception {

		http
		.cors()
		.and()
		
		.csrf()
		.disable()
		//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        //.and()
		
		.authorizeRequests()
		//.antMatchers(HttpMethod.POST,"/login").permitAll()
		.antMatchers(HttpMethod.GET, "/products", "/index.html").permitAll()
		//.antMatchers(HttpMethod.GET, "/orders", "/orders/*").hasRole("ADMIN")
		.anyRequest().permitAll()
		//.authenticated()
		.and()
		
		.exceptionHandling()
        .accessDeniedHandler(accessDeniedHandlerImpl)
        //this line prevent spring login page to be accessed
        .authenticationEntryPoint(authenticationEntryPointImpl)
        .and()
		
        .formLogin()
        //.permitAll()
        //.loginProcessingUrl("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(authenticationSuccessHandlerImpl)
        .failureHandler(authenticationFailureHandlerImpl)
		.and()
		
		.logout()
		.permitAll()
		.logoutUrl("/logout")
		.logoutSuccessHandler(logoutSuccessHandlerImpl)
		.and()
		
		.httpBasic();
	}

	@Autowired // @Autowired on function will autowired the parameters
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean // put the return object into spring container, as a bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.addAllowedOrigin("*"); // You should only set trusted site here. e.g. http://localhost:4200 means only this site can access.
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
	        configuration.addAllowedHeader("*");
	        configuration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
}
