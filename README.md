# Getting Started

### Reference Documentation for Payment Gateway Middleware
For further reference, please consider the following sections:

* Build instructions
* Run Instructions
* 
###  Build instructions
```
* Database setup ---- H2 username and password can be found in application-dev.yml
* Access H2 ui http://localhost:8080/gpay/h2-console
* pull the project from the git repo
* cd into the root directory
* write [mvn clean install] to build the application
* java -jar (your_directory)/target/fleetmgt.jar

```
###  Run instructions

```
*  java -jar (your_directory)/target/gpay.jar
```
The following guides illustrate how to use some features concretely:
This middleware has 3 endpoints found in the Api documentation.

###  Instructions
Step-by-step process
```
* pull the project from the git repo
* cd into the root directory
* write [mvn clean install] will also execute all the test clases
```


```
NOTE: RECORDS HAVE BEEN PRELOADED ONCE YOU RUN THE APPLICATION.

1. Users
2. Customer Cards
3. Accounts


```

1. Initiate Payment   -> http://localhost:8080/gpay/api/v1/pay


>INITIATE PAYMENT REQUEST BODY
```
{
  "orderId": "09999",
  "amount": 3000,
  "currency": "NGN",
  "cardName": "DAVID MARK",
  "cardNumber": "524621636858040",
  "expiryDate": "07/24",
  "cvv": 190
}
```
---------------------------------

2. Transaction status api  -> http://localhost:8080//api/v1/medications
> TRANSACTION STATUS REQUEST BODY
ContentType = {form-data}
```
{
  "transactionDateTime": "2024-03-21T11:54:53.349Z",
  "transactionReference": "888001230713212850000665732924"
}
```
 ---------------------------------
3. WebHook Notification - > http://localhost:8080/gpay/api/v1/webhook/transaction

>WEBHOOK NOTIFICATION REQUEST BODY
```
{
  "responseCode": "00",
  "sessionId": "8867676666676767666667",
  "eventType": "successful",
  "status": true,
  "transactionDate": "2024-03-21T12:52:25.677+01:00",
  "amount": 1000,
  "description": "payment completed successfully"
}
```

```
