package com.wayne.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wayne.auth.dao.AuthorityDao;
import com.wayne.auth.dao.mapper.AuthorityMapper;
import com.wayne.auth.exception.UserNotFoundException;
import com.wayne.auth.service.AuthorityService;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityMapper authorityMapper;
	
	@Override
	public List<AuthorityDao> findByUseId(String userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return authorityMapper.findByUserId(userId);
	}

}
