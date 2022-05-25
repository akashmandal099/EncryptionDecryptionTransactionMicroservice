package com.akash.DataEncryptor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryptor {
	
	public static byte[] readKey(String file) {
		File keyFile = new File(file);
		byte[] publicKeyBytes=null;
		try {
			publicKeyBytes = Files.readAllBytes(keyFile.toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return publicKeyBytes;
	}
	
	public static String encrypt(String originalText, byte[] publicKeyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
//		// By reading public key from file
//		File keyFile = new File(publicKeyFile);
//		byte[] publicKeyBytes=null;
//		try {
//			publicKeyBytes = Files.readAllBytes(keyFile.toPath());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
//		// With public key as string
//		String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1pDUseRnfX0LNboiFa/oWY8ZrY3ujM2UJY3p5oFSZvTanlhN+6f0kz90qU4dyITe+JQL9c4YgkB/+6t3lXodJ0YvyiLxRD6mnRZgdj2xKzSj8TfyQlkWijZKgTAg94ovcSzAEuNwT+dz/L1JmVd+D0QVFRxwpiPB/xrBH7A19EwIDAQAB";
//		byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr.getBytes());
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		
		
		Cipher encryptCipher = Cipher.getInstance("RSA");
		try {
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] textByte = originalText.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedByte = encryptCipher.doFinal(textByte);
		
		return Base64.getEncoder().encodeToString(encryptedByte);
	}
	
	public static String encrypt(long text, byte[] publicKeyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
//		// By reading public key from file
//		File keyFile = new File(publicKeyFile);
//		byte[] publicKeyBytes=null;
//		try {
//			publicKeyBytes = Files.readAllBytes(keyFile.toPath());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
//		// With public key as string
//		String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1pDUseRnfX0LNboiFa/oWY8ZrY3ujM2UJY3p5oFSZvTanlhN+6f0kz90qU4dyITe+JQL9c4YgkB/+6t3lXodJ0YvyiLxRD6mnRZgdj2xKzSj8TfyQlkWijZKgTAg94ovcSzAEuNwT+dz/L1JmVd+D0QVFRxwpiPB/xrBH7A19EwIDAQAB";
//		byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr.getBytes());
		
		String originalText = String.valueOf(text);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		
		
		Cipher encryptCipher = Cipher.getInstance("RSA");
		try {
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] textByte = originalText.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedByte = encryptCipher.doFinal(textByte);
		
		return Base64.getEncoder().encodeToString(encryptedByte);
	}

}
