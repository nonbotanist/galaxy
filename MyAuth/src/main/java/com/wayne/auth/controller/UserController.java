package com.wayne.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Wei (a_win@163.com)
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping("/userinfo")
	public Principal getCurrentUser(Principal principal) {
		return principal;
	}
}
