/**
 * 
 */
package com.wayne.auth.dao;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OauthClientDetailsDao implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4370461811824532005L;

	private String clientId;

	private String resourceIds;

	/**
	 * Encrypted
	 */
	private String clientSecret;
	/**
	 * Available values: read,write
	 */
	private String scope;

	/**
	 * grant types include "authorization_code", "password", "assertion", and
	 * "refresh_token". Default value is "authorization_code,refresh_token".
	 */
	private String authorizedGrantTypes = "authorization_code,refresh_token";

	/**
	 * The re-direct URI(s) established during registration (optional, comma
	 * separated).
	 */
	private String webServerRedirectUri;

	/**
	 * Authorities that are granted to the client (comma-separated). Distinct from
	 * the authorities granted to the user on behalf of whom the client is acting.
	 * <p/>
	 * For example: ROLE_USER
	 */
	private String authorities;

	/**
	 * The access token validity period in seconds (optional). If unspecified a
	 * global default will be applied by the token services.
	 */
	private Integer accessTokenValidity;

	/**
	 * The refresh token validity period in seconds (optional). If unspecified a
	 * global default will be applied by the token services.
	 */
	private Integer refreshTokenValidity;

	// optional
	private String additionalInformation;

	/**
	 * Value is 'true' or 'false', default 'false'
	 */
	private String autoApprove;
}
