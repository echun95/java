package com.tistory.offbyone.main;

import com.tistory.offbyone.crypto.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		String plainText = "Hello, World!";
		String key = "secret key";
		
		
		String encrypted = CryptoUtil.encryptAES256(plainText, key);
		System.out.println("암호화 하기전 문자 - " + plainText);
		System.out.println("AES-256 : 암호화 후 - " + encrypted);
		System.out.println("AES-256 : 복호화 후 - " + CryptoUtil.decryptAES256(encrypted, key));
	}

}
