package com.akash.DataDecryptor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Decryptor {
	
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
	
	public static String decrypt(String encryptedText, byte[] privateKeyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
//		// Read private key from file
//		File keyFile = new File(privateKeyFile);
//		byte[] privateKeyBytes=null;
//		try {
//			privateKeyBytes = Files.readAllBytes(keyFile.toPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		// Given private key as string
//		String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALWkNSx5Gd9fQs1uiIVr+hZjxmtje6MzZQljenmgVJm9NqeWE37p/STP3SpTh3IhN74lAv1zhiCQH/7q3eVeh0nRi/KIvFEPqadFmB2PbErNKPxN/JCWRaKNkqBMCD3ii9xLMAS43BP53P8vUmZV34PRBUVHHCmI8H/GsEfsDX0TAgMBAAECgYABtaK6etLl6bBoECfaYAKU8cNzgONjZp+gz8refvlmddCgRMCFa6mL2yXEH2nuIEBO2p8e+4nCBkxtMPcXQ/IWzIIqB5MF8a9iGGDl/++0uqpNE+LOcusmQ5ymcEAac5ITlu83hjEiWNTcEjvHWa5Y0j3UJ1bSsJzyTs4IuX7IYQJBANcltqYuXEP+jA/hjNMbol9kqQariZEXWzDpBASpeqQ9LB2yM9xhbGRWHfuvWCCAcdnEA3K93vTPozl628AqSO0CQQDYIcbzYilLnZV/gsbX1Ig7syqGyJZoddb705eWqwF4MdFLNL5YuDCQNib5QsWM/RO2vHHSeAMXxAHXxmK83B3/AkBfCcPTSOttBcatRT9GChEB/p9D2Qad1ylrQ6OIw6zvXC5et9d85HRTJk98XIqbYOHqBlJXn1Qb5JT/0Su9iXBJAkEAwtPn6NuSyCP8cJ4tqr9eMxq1+hv1YaLgMc+yNVJfl76ooPehoo4e0NDP3x/uvsIftDk5DXW7q/+3fY/Kz95IdwJAK/pGxvtYhGA9APvEzT4hxQ0MbCaf4rJfZhcS07je6cTq5G03TCICCqq5beLiK/+VXkZzQlb4EWfQpgYjM9DbyQ==";
//		byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr.getBytes());
		
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		
		Cipher decryptCipher = Cipher.getInstance("RSA");
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		byte[] encryptedTextBytes = encryptedText.getBytes(StandardCharsets.UTF_8);
		byte[] textBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedTextBytes));
		String decryptedMessage = new String(textBytes, StandardCharsets.UTF_8);
		return decryptedMessage;
	}

}
