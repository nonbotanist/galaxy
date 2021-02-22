package com.wayne.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wayne.auth.dao.AuthorityDao;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Mapper
public interface AuthorityMapper {
	@Select("select authorities.authority from authorities "
			+ "left join credentials_authorities "
			+ "on authorities.id = credentials_authorities.authority_id "
			+ " where credentials_authorities.credential_id =#{userId};")
	@Results(id = "userMap", value = {
	    @Result(id = true, column = "id", property = "id"),
        @Result(column = "name", property = "name")
    })
	List<AuthorityDao> findByUserId(@Param("userId") String userId);
}
