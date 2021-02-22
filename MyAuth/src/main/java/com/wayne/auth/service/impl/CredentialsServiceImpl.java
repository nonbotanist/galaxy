/**
 * 
 */
package com.wayne.auth.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wayne.auth.dao.CredentialDao;
import com.wayne.auth.dao.mapper.CredentialMapper;
import com.wayne.auth.service.CredentialsService;



/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Service
public class CredentialsServiceImpl implements CredentialsService {
	Logger logger = LoggerFactory.getLogger(CredentialsServiceImpl.class);
	
	@Autowired
	private CredentialMapper credentialMapper;
		
	@Override
	public CredentialDao findByUserName(String userName) throws UsernameNotFoundException {
		
		CredentialDao credentialDao = credentialMapper.findByUserName(userName);
		
		return credentialDao;
	}

//	@Override
//	public CredentialDao verify(String username, String password) throws UserAndPasswordUnmatchException {
//		
//		CredentialDao credentialDao = credentialMapper.findByUserNamePassword(username, password);
//		
//		if(credentialDao==null)
//			
//			throw new UserAndPasswordUnmatchException();
//		
//		else
//			return credentialDao;
//	}

}
