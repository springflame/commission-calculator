### About

This repository contains the source code for commissionapp, an application that calculates transaction commissions.

### System requirements

1. You must have a JRE capable of running Java 18 applications. 

   -> Download an appropriate installer [here](https://www.oracle.com/java/technologies/downloads/) and run it.

2. You must be able to run shell scripts.
   
    -> Windows: download and install [Git Bash](https://git-scm.com/downloads).

3. You must have [postman](https://www.postman.com/downloads/) on your machine if you wish to perform manual tests.

### Instructions

1. To run automated tests, execute [this script](./tools/run-tests.sh).


2. To run the application, execute [this script](./tools/run-app.sh).

   The application then starts listening for POST requests at [https://localhost:8080/api/transactions](https://localhost:8080/api/transactions).

   
3. Manual tests:

   Sample test cases are available [here](./commissionapp/src/test/resources/test-cases).

   You can feed them to postman, but please note that they are already covered by the automated test suite.

      Each test case there (represented by a folder, e.g. [high-turnover-reset-next-month](./commissionapp/src/test/resources/test-cases/high-turnover-reset-next-month)) contains an ordered list of 1+ request-response pairs.
   Note that the application has a state and the test cases are based on this assumption; for that reason, requests in the list need to be fed to the REST API in the defined order for the relationship between each request and its corresponding response to hold.


### Notes and warnings

_**_Due to the fact that the application uses an in-memory database, the state does not survive application restarts._**_

Starter generated with [Spring initializr](https://start.spring.io/).