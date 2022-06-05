# Encryption Decryption Transaction Microservice
This Microservice takes data from client and encrypts it by DataEncryptor with Public Key using Assymetric Encryption (RSA algorithm) and sends the data to the DataDecryptor Microservice, which then derypts the data using Private key. After decrypting the data it update the data in the data base accrodingly. 

## Data has 4 values which client sends:
1. Account Number (Type-long),
2. Account Holder Name (Type-String),
3. Transaction Type (Debit or Credit)(Type-String),
4. Transaction Amount (Type-long)

First, Create Public key and Private key using PublicPrivateKeyGenerator. It writes these keys in two different files and then we copy these files for Encryption and Decryption respectively.

## Microservice Architecture
![alt text](https://github.com/akashmandal099/EncryptionDecryptionTransactionMicroservice/blob/main/Documents/EncryptionDecryptionArchitecture.png)

## This microservice has total 4 components:
1. DataEncryptor: Application name is "data-encryptor". This Microservice encrypts Data using Public Key and stores encrypted Data in a Message queue. This data is send one after another when Dycryptor sends get request to it.
2. DataEncryptor: Application name is "data-decryptor". This Microservice fetch encrypted Data one after another from the queue of Encryptor Microservice. Then it add the data to the H2-database. It has two types of transaction one is debit and credit. For debit it adds value and for credit it substracts value.
3. NamingServer: It uses Eureka server to register all the Microservices.
4. Api-Gateway: It creates gateway to call the Microservices.

## DataEncryptor
Encryption POST request to http://localhost:8765/data-encryptor/encrypt

![alt text](https://github.com/akashmandal099/EncryptionDecryptionTransactionMicroservice/blob/main/Documents/EncryptPostman.png)

## DataDecryptor
Test Decryption by POST request to http://localhost:8765/data-decryptor/decrypt

![alt text](https://github.com/akashmandal099/EncryptionDecryptionTransactionMicroservice/blob/main/Documents/DecryptionPostman.png)
