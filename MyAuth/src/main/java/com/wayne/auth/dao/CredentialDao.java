/**
 * 
 */
package com.wayne.auth.dao;

import java.io.Serializable;
import java.util.List;

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
public class CredentialDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4955023573701012869L;
	
	private String id;
    private boolean isEnabled;
    private String pwd;
    private String name;
    private List<AuthorityDao> authorities;
    
}
