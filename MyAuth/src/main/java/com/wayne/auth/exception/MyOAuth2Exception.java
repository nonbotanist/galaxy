package com.wayne.auth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class MyOAuth2Exception extends OAuth2Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2403142544054241741L;
	
	private Integer status = 400;
	
	public MyOAuth2Exception(String msg) {
		super(msg);
	
	}
	
	public MyOAuth2Exception(String message, Throwable t) {
        super(message, t);
		status = ((OAuth2Exception)t).getHttpErrorCode();
    }
	
	@Override
    public int getHttpErrorCode() {
        return status;
    }



}
