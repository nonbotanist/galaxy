package com.wayne.auth.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.wayne.auth.dao.CredentialDao;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Mapper
public interface CredentialMapper {
	@Select("select credentials.id, credentials.user_name , credentials.password, credentials.enabled from credentials "
			+ "where credentials.enabled =1 "
			+ "and credentials.user_name = #{userName}")
	@Results(id = "userMap", value = {
	    @Result(id = true, column = "id", property = "id"),
	    @Result(column = "enabled", property = "isEnabled", jdbcType=JdbcType.TINYINT),
        @Result(column = "password", property = "pwd"),
        @Result(column = "user_name", property = "name"),
        @Result(column = "id",property = "authorities", many =@Many(select="com.wayne.auth.dao.mapper.AuthorityMapper.findByUserId",fetchType = FetchType.LAZY))
    })
	CredentialDao findByUserName(@Param("userName") String userName);
	
//	@Select("select credentials.id, credentials.user_name , credentials.password, credentials.enabled from credentials "
//			+ "where credentials.enabled =1 "
//			+ "and credentials.user_name = #{userName}"
//			+ "and credentials.password = #{password}")
//	@ResultMap(value ={"userMap"} )
//	CredentialDao findByUserNamePassword(@Param("userName") String userName,@Param("password") String password);


}
