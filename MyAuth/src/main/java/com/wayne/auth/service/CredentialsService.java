/**
 * 
 */
package com.wayne.auth.service;

import com.wayne.auth.dao.CredentialDao;
import com.wayne.auth.exception.UserNotFoundException;


/**
 * @author Wang Wei (a_win@163.com)
 *
 */
public interface CredentialsService {
	public CredentialDao findByUserName(String userName) throws UserNotFoundException;
	//public CredentialDao verify(String username,String password) throws UserAndPasswordUnmatchException;
}
