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
public class AuthorityDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8804485039714170108L;
	
	private int id;
	private String authority;
}
