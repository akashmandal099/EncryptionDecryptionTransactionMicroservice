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
//		System.out.println("find by account no:" + repo.findTransactionAmountByAccountNo(data.accountNo));
//		
//		long balance = repo.getById(data.accountNo).transactionAmount;
//		try {
//			System.out.println("From database:" + repo.findByAccountNo(data.accountNo).transactionAmount);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		repo.save(data);
//		if (repo.getById(data.accountNo) == null) {
//			repo.save(data);
//			System.out.println("data added"+data);
//		} else {
////			Long balance = repo.getTransactionAmountByAccountNo(data.accountNo);
//			if(data.transactionType == "Debit") {
//				data.setTransactionAmount(balance - data.transactionAmount);
//			} else {
//				data.setTransactionAmount(balance + data.transactionAmount);
//			}
//		}
		return data;
	}

}
