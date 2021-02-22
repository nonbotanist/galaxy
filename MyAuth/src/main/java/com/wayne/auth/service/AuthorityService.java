package com.wayne.auth.service;

import java.util.List;

import com.wayne.auth.dao.AuthorityDao;
import com.wayne.auth.exception.UserNotFoundException;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
public interface AuthorityService {
	
	public List<AuthorityDao> findByUseId(String userId) throws UserNotFoundException;
	
}
