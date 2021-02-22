package com.wayne.auth.service;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
public interface CaptchaService {
	/**
     * 创建验证码
     *
     * @param request 请求
     * @throws Exception 异常
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证验证码
     *
     * @param request 请求
     */
    void validate(ServletWebRequest request);
}
