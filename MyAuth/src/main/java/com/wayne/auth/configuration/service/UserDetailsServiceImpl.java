package com.wayne.auth.configuration.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wayne.auth.dao.AuthorityDao;
import com.wayne.auth.dao.CredentialDao;
import com.wayne.auth.exception.UserNotFoundException;
import com.wayne.auth.service.CredentialsService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Service("UserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private CredentialsService crendentialsService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
		
			CredentialDao credential = crendentialsService.findByUserName(username);
					
			List<GrantedAuthority> granteds = new ArrayList<GrantedAuthority>();
			
			for(AuthorityDao dao: credential.getAuthorities()){
				
				granteds.add(new SimpleGrantedAuthority(dao.getAuthority()));
				
			}
			
			
			User u =  new User(credential.getName(), credential.getPwd(), credential.isEnabled(), true, true, true,granteds);
			
			return u;
			
		}catch(UserNotFoundException e) {
			
			 throw new UsernameNotFoundException("User '" + username + "' can not be found");
			 
		}
       
	}


}
