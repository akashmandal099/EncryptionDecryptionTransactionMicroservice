package com.akash.DataEncryptor;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	byte[] publicKey = Encryptor.readKey("public.key");
	
	Queue<encryptedData> dataQ = new LinkedList<>(); 
	
	@PostMapping("/encrypt")
	public encryptedData encryptData(@RequestBody Data data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		
		encryptedData encrData = new encryptedData();
		encrData.setAccountNo(Encryptor.encrypt(data.getAccountNo(), publicKey));
		encrData.setAccountName(Encryptor.encrypt(data.getAccountName(), publicKey));
		encrData.setTransactionType(Encryptor.encrypt(data.getTransactionType(), publicKey));
		encrData.setTransactionAmount(Encryptor.encrypt(data.getTransactionAmount(), publicKey));
		
		dataQ.add(encrData);
		
		return encrData;
	}
	
	@GetMapping("/encrypt")
	public encryptedData getDataQ() {
		if (!dataQ.isEmpty()) {
			return dataQ.poll();
		} 
		else {
			return null;
		}
	}

}
