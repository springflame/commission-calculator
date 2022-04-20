### About

This repository contains the source code for commissionapp, an application that calculates transaction commissions.

### System requirements

1. You must have a JRE capable of running Java 18 applications. 

   -> Download an appropriate installer [here](https://www.oracle.com/java/technologies/downloads/) and run it.

2. You must be able to run shell scripts.
   
    -> Windows: download and install [Git Bash](https://git-scm.com/downloads).

3. You must have [postman](https://www.postman.com/downloads/) on your machine if you wish to perform manual tests.

### Instructions

1. To run the application, execute [this script](./tools/run-app.sh).

   The application then starts listening for POST requests at [https://localhost:8080/api/transactions](https://localhost:8080/api/transactions).

   
2. Manual tests:

   Sample test cases are available [here](./commissionapp/src/test/resources/test-cases).

   You can feed them to postman if you wish, but please note that they are all covered by the automatic test suite.

      Each test case there (represented by a folder, e.g. [currency-conversion-1](./commissionapp/src/test/resources/test-cases/currency-conversion-1)) contains an ordered list of 1+ request-response pairs.
   All requests need to be fed to the application in the defined order for the relationship between the requests and responses to stand (as implied by Rule 3: High turnover discount). In other words, the application has a state and the test cases are based on this assumption.

### Notes and warnings

_**_Due to the fact that the application uses an in-memory database, the state does not survive application restarts._**_

Starter generated with [Spring initializr](https://start.spring.io/).