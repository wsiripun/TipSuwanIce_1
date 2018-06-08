package com.erp.tipsuwan.db.common;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecurePassword {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
		String  originalPassword = "password";
		String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
		System.out.println(generatedSecuredPasswordHash);
		
		boolean matched = validatePassword("password", generatedSecuredPasswordHash);
		System.out.println(matched);
		
		matched = validatePassword("password1", generatedSecuredPasswordHash);
		System.out.println(matched);
		
		String randomPassword = generateRandomPassword(8);
		System.out.println(randomPassword);
	}
	
	private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] testHash = skf.generateSecret(spec).getEncoded();
		
		int diff = hash.length ^ testHash.length;
		
		for(int i = 0; i < hash.length && i < testHash.length; i++)
		{
			diff |= hash[i] ^ testHash[i];
		}
		
		return diff == 0;
	}
	
	private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();
		
		System.out.println(salt);
		
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
				
	}
	
	  public static String generateRandomPassword(int length) {
		    StringBuilder sb = new StringBuilder(length);
		    for (int i = 0; i < length; i++) {
		      int c = new SecureRandom().nextInt(62);
		      if (c <= 9) {
		        sb.append(String.valueOf(c));
		      } else if (c < 36) {
		        sb.append((char) ('a' + c - 10));
		      } else {
		        sb.append((char) ('A' + c - 36));
		      }
		    }
		    return sb.toString();
		  }
	
	private static byte[] getSalt() throws NoSuchAlgorithmException
	{
		
		byte[] salt = new byte[16];
		new SecureRandom().nextBytes(salt);
		for(int i = 0; i<16; i++) {
            System.out.print(salt[i] & 0x00FF);
            System.out.print(" ");
        }
		return salt;
	}
	
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0)
		{
			return String.format("%0"  +paddingLength + "d", 0) + hex;
		}else{
			return hex;
		}
	}
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	{
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i<bytes.length ;i++)
		{
			bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
