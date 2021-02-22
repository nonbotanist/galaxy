package com.wayne.auth.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(WebSecurityConfigurer.class);

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/oauth/**").permitAll();
//		http // 配置登录页并允许访问
//				.formLogin().permitAll()
//				// 配置Basic登录
//				// .and().httpBasic()
//				// 配置登出页面
//				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").and().authorizeRequests()
//				.antMatchers("/oauth/**", "/login/**", "/logout/**").permitAll()
//				// 其余所有请求全部需要鉴权认证
//				.anyRequest().authenticated()
//				// 关闭跨域保护;
//				.and().csrf().disable();
		/**
		 * authorization_code 需要 httpBasic 认证
		 */
		http.httpBasic().and().authorizeRequests().antMatchers("/**").permitAll().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.parentAuthenticationManager(authenticationManagerBean());
//		auth.authenticationProvider(authenticationProvider());
//		auth.authenticationProvider(verificationCodeProvider);

	}
	 /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();

        return manager;
    }
    
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
