package com.wayne.auth.service;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
public interface CaptchaGeneratorService {
	 /**
     * 生成验证码
     *
     * @param request 请求
     * @return 生成结果
     */
    String generate(ServletWebRequest request);

}
