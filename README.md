# Countrywide Banking Corperation Loan Management System

### SOFTENG 306 Project 01 Team 18

## Running the Applicatoin

To run the application run via **_mvn clean javafx:run_** from the commandline.

## Testing the Application

Unit tests can be found in the **_test_** directory. To run the tests use **_mvn test_** from the commandline.

## Changing Mainframe Connections

Currently, connections are being handled by the **_ConnectionInstance.java_** class which is located within the **_utils/objects_** directory. The connection is initialised in **_App.java_**. To set this connection, you can use the following code to set the type of connection and the data path:

```java
Connection connection = new Connection(<dataPath>);
ConnectionInstance.setConnection(connection);
```
