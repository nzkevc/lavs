# Countrywide Banking Corporation Loan Management System

## SOFTENG 306 Project 01 Team 18

Team members and respective GitHub Logins:

- Adam Bodicoat - adxmb
- Kevin Cheung - nzkevc
- Hayley Smith - hayley9080
- Nate Williamson - oculux314

## Running the Application

To run the application, run **_mvn clean javafx:run_** from the command line.

## Testing the Application

Unit tests can be found in the **_test_** directory. To run the unit tests use **_mvn test_** from the command line.

## Changing Mainframe Connections

Currently, connections are being handled by the **_ConnectionInstance.java_** class, located within the **_utils/objects_** directory. The connection is initialised in **_App.java_**. To set this connection, you can use the following code to set the type of connection and the data path:

```java
Connection connection = new Connection(<dataPath>);
ConnectionInstance.setConnection(connection);
```
