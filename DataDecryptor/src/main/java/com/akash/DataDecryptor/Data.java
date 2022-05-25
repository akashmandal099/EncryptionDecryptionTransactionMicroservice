package com.akash.DataDecryptor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Data {
	@Id
	long accountNo;
	String accountName;
	String transactionType;
	long transactionAmount;
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public long getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Data() {
		super();
	}
	public Data(long accountNo, String accountName, String transactionType, long transactionAmount) {
		super();
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}
	@Override
	public String toString() {
		return "Data [accountNo=" + accountNo + ", accountName=" + accountName + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + "]";
	}
	
	

}
