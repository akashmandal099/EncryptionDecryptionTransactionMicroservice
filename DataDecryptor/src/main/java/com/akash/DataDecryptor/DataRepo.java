package com.akash.DataDecryptor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepo extends JpaRepository<Data, Long> {
	
	Data findByAccountNo(long accountNo);
//	Data findOne(Long accountNo);
	Long findTransactionAmountByAccountNo(Long accountNo);

}
