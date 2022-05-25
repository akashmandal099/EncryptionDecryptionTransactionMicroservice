package com.akash.DataDecryptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="data-encryptor",url = "localhost:8100")
public interface getEncrDataProxy {
	
	@GetMapping("/encrypt")
	public encryptedData getDataQ();

}
