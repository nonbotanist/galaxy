package com.wayne.auth.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Configuration
@EnableAuthorizationServer
@AutoConfigureAfter(WebSecurityConfigurer.class)
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(AuthorizationServerConfigurer.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	DataSource dataSource;

	@Autowired
	TokenStore tokenStore;

	@Autowired
	AuthorizationCodeServices authorizationCodeServices;

	@Autowired
	ApprovalStore approvalStore;

//	@Autowired
//	UserDetailsService userDetailsService;
	
	@Autowired
	ClientDetailsService clientDetailsService;

	@Autowired
	private WebResponseExceptionTranslator webResponseExceptionTranslator;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		// 允许表单认证
		security.allowFormAuthenticationForClients();
		/* 配置token获取合验证时的策略 */
		security.tokenKeyAccess("permitAll()") // 开启/oauth/token_key验证端口认证权限访问
				// 开启/oauth/check_token验证端口认证权限访问
				.checkTokenAccess("isAuthenticated()"); // 验证通过返回token信息

		// 默认的 authenticationManager 是错的，手动设置
		security.addTokenEndpointAuthenticationFilter(
				new BasicAuthenticationFilter(authenticationManager, new BasicAuthenticationEntryPoint()));
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 配置数据从oauth_client_details表读取来存储
		 clients.withClientDetails(new JdbcClientDetailsService(dataSource));
		//clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.exceptionTranslator(webResponseExceptionTranslator);
		//
		endpoints.approvalStore(approvalStore).tokenStore(tokenStore).authenticationManager(authenticationManager)
				//.userDetailsService(userDetailsService)
				.authorizationCodeServices(authorizationCodeServices)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE,
						HttpMethod.OPTIONS)
				.reuseRefreshTokens(true);

	}

	@Bean
	public ApprovalStore approvalStore() {
		return new JdbcApprovalStore(dataSource);
	}

	@Bean
	public TokenStore tokenStore() {
		// return new InMemoryTokenStore(); //使用内存中的 token store
		return new JdbcTokenStore(dataSource); /// 使用Jdbctoken store
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

}
