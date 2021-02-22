package com.wayne.auth.configuration.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
public class ClientDetailsService implements org.springframework.security.oauth2.provider.ClientDetailsService {

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		// TODO Auto-generated method stub
		return null;
	}

}
