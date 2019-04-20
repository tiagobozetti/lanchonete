package com.finch.burguer.securities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MD5PasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			return String.format("%032x", new BigInteger(1, MessageDigest.getInstance("MD5").digest(rawPassword.toString().getBytes())));
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
		
		return new MD5PasswordEncoder().encode(rawPassword.toString())
                .equals(encodedPassword);
	}

}
