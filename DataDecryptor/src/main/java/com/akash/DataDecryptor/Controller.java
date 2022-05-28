package com.akash.DataDecryptor;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	byte[] privateKey = Decryptor.readKey("private.key");

	@Autowired
	private getEncrDataProxy proxy;
	
	@Autowired
	private DataRepo repo;
	
	@PostMapping("/decrypt")
	public Data decryptData(@RequestBody encryptedData encrData) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		Data data = new Data();
		data.setAccountNo(Long.parseLong(Decryptor.decrypt(encrData.getAccountNo(), privateKey)));
		data.setAccountName(Decryptor.decrypt(encrData.getAccountName(), privateKey));
		data.setTransactionType(Decryptor.decrypt(encrData.getTransactionType(), privateKey));
		data.setTransactionAmount(Long.parseLong(Decryptor.decrypt(encrData.getTransactionAmount(), privateKey)));
		
		return data;
	}
	
	
	@GetMapping("/decrypt")
	public Data getEncrData() throws NumberFormatException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		encryptedData encrData = proxy.getDataQ();
		
		if (encrData == null) return null;
		
		Data data = new Data();
		data.setAccountNo(Long.parseLong(Decryptor.decrypt(encrData.getAccountNo(), privateKey)));
		data.setAccountName(Decryptor.decrypt(encrData.getAccountName(), privateKey));
		data.setTransactionType(Decryptor.decrypt(encrData.getTransactionType(), privateKey));
		data.setTransactionAmount(Long.parseLong(Decryptor.decrypt(encrData.getTransactionAmount(), privateKey)));
		
		System.out.println(data);
		try {
//			repo.findById(data.accountNo);
			Long balance = repo.findByAccountNo(data.accountNo).transactionAmount;
			System.out.println(balance);
			if(data.transactionType.equals("Debit") || data.transactionType.equals("debit")) {
				data.setTransactionAmount(balance - data.transactionAmount);
			} else if (data.transactionType.equals("Credit") || data.transactionType.equals("credit")){
				data.setTransactionAmount(balance + data.transactionAmount);
			} else {
				System.out.println("No implementations for "+ data.transactionType+".");
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		} 
		finally {
			repo.save(data);
		}
		return data;
	}

}
