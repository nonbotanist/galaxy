package com.wayne.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MyAuthApplicationTests {


	    @Test
	    public void testBCryptPasswordEncoder() {
	    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;
	        for (int i = 0; i < 10; i++) {
	            String password = "12345";
	            String hashed = bCryptPasswordEncoder.encode(password);
	            System.out.println(hashed);
	        }
	    }
	 

}
