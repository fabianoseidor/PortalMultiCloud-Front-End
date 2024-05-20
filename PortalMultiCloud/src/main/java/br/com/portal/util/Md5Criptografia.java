package br.com.portal.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Criptografia {
	public static String convertPasswordToMD5(String password) throws NoSuchAlgorithmException {
		
		if(password != null && !password.isEmpty()) {
		   MessageDigest md = MessageDigest.getInstance("MD5");
           BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
           return String.format("%32x", hash);
		}
		
		return null;
	}

}
