package com.akash.DataDecryptor;

public class encryptedData {
	
	String accountNo;
	String accountName;
	String transactionType;
	String transactionAmount;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
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
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public encryptedData() {
		super();
	}
	public encryptedData(String accountNo, String accountName, String transactionType, String transactionAmount) {
		super();
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}
	@Override
	public String toString() {
		return "encryptedData [accountNo=" + accountNo + ", accountName=" + accountName + ", transactionType="
				+ transactionType + ", transactionAmount=" + transactionAmount + "]";
	}
	
	
}
