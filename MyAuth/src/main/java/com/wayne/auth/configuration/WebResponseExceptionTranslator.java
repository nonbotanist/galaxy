package com.wayne.auth.configuration;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.wayne.auth.exception.MyOAuth2Exception;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Component
public class WebResponseExceptionTranslator
		implements org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator {

	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		// Try to extract a SpringSecurityException from the stacktrace
		Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

		// 异常栈获取 OAuth2Exception 异常
		Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

		// 异常栈中有OAuth2Exception
		if (ase != null) {
			return handleOAuth2Exception((OAuth2Exception) ase);
		}

		ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
				causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
		}

		ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class,
				causeChain);
		if (ase instanceof AccessDeniedException) {
			return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
		}

		ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer
				.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
		if (ase instanceof HttpRequestMethodNotSupportedException) {
			return handleOAuth2Exception(new MethodNotAllowed(ase.getMessage(), ase));
		}

		// 不包含上述异常则服务器内部错误
		return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));
	}

	private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {
		int status = e.getHttpErrorCode();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		if (status == HttpStatus.UNAUTHORIZED.value() || e instanceof InsufficientScopeException) {
			headers.set("WWW-Authenticate", String.format("%s %s", "Bearer", e.getSummary()));
		}
		OAuth2Exception exception = new MyOAuth2Exception(e.getMessage(), e);
		ResponseEntity<OAuth2Exception> response = new ResponseEntity<OAuth2Exception>(exception, headers,
				HttpStatus.valueOf(status));
		return response;
	}

	/**
	 * 
	 * @author Wang Wei (a_win@163.com)
	 *
	 */
	private static class MethodNotAllowed extends OAuth2Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4063114616546263725L;

		public MethodNotAllowed(String msg, Throwable t) {
			super(msg, t);
		}

		@Override
		public String getOAuth2ErrorCode() {
			return "method_not_allowed";
		}

		@Override
		public int getHttpErrorCode() {
			return 405;
		}
	}

	/**
	 * 
	 * @author Wang Wei (a_win@163.com)
	 *
	 */
	private static class UnauthorizedException extends OAuth2Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5227653492852040104L;

		public UnauthorizedException(String msg, Throwable t) {
			super(msg, t);
		}

		@Override
		public String getOAuth2ErrorCode() {
			return "unauthorized";
		}

		@Override
		public int getHttpErrorCode() {
			return 401;
		}
	}

	/**
	 * 
	 * @author Wang Wei (a_win@163.com)
	 *
	 */
	private static class ServerErrorException extends OAuth2Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6112566642829633067L;

		public ServerErrorException(String msg, Throwable t) {
			super(msg, t);
		}

		@Override
		public String getOAuth2ErrorCode() {
			return "server_error";
		}

		@Override
		public int getHttpErrorCode() {
			return 500;
		}
	}

	/**
	 * 
	 * @author Wang Wei (a_win@163.com)
	 *
	 */
	private static class ForbiddenException extends OAuth2Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5427884514818042627L;

		public ForbiddenException(String msg, Throwable t) {
			super(msg, t);
		}

		@Override
		public String getOAuth2ErrorCode() {
			return "access_denied";
		}

		@Override
		public int getHttpErrorCode() {
			return 403;
		}
	}

}
