package KeyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class KeyGenerator {
	
	public static void publicPrivateKeyWrite(String publicKeyFile, String privateKeyFile) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair pair = generator.generateKeyPair();
		
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		try (FileOutputStream fos = new FileOutputStream(privateKeyFile)) {
		    fos.write(privateKey.getEncoded());
		    System.out.println("Private Key:\n"+Base64.getEncoder().encodeToString(privateKey.getEncoded()));
		    
		}
		
		try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
		    fos.write(publicKey.getEncoded());
		    System.out.println("Public Key:\n"+Base64.getEncoder().encodeToString(publicKey.getEncoded()));
		}
		
	}
	
	public static String encryption(String originalText, String publicKeyFile) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		File keyFile = new File(publicKeyFile);
		byte[] publicKeyBytes=null;
		try {
			publicKeyBytes = Files.readAllBytes(keyFile.toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
	
	public static String decryption(String encryptedText, String privateKeyFile) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		File keyFile = new File(privateKeyFile);
		byte[] privateKeyBytes=null;
		try {
			privateKeyBytes = Files.readAllBytes(keyFile.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		// TODO Auto-generated method stub
		
		String publicKeyFile = "public.key";
		String privateKeyFile = "private.key";
		
		publicPrivateKeyWrite(publicKeyFile, privateKeyFile);
		
		String text = "text! this is for testing decryption algorithm.!!";
		
		String encryptedText = encryption(text, publicKeyFile);
		System.out.println("Encrypted text:");
		System.out.println(encryptedText);
		
		System.out.println("Decrypted text:");
		System.out.println(decryption(encryptedText, privateKeyFile));

	}

}
