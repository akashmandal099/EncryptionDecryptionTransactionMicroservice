package com.akash.DataDecryptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataDecryptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataDecryptorApplication.class, args);
	}

}
