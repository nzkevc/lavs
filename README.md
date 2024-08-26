# Countrywide Banking Corporation Loan Management System

## SOFTENG 306 Project 01 Team 18

Team members and respective GitHub Logins:

- Adam Bodicoat - adxmb
- Kevin Cheung - nzkevc
- Hayley Smith - hayley9080
- Nate Williamson - oculux314

## Running the Application

To run the Countrywide Banking application, run `mvn clean javafx:run` from the command line.

## Testing the Application

Unit tests can be found under `src/test/java/uoa/lavs/`. These tests cover the following folders under `src/main/java/uoa/lavs/`:
```
- mainframe
- utility
- models
- repository
- services
```
To run the unit tests, run `mvn test` from the command line.

## Changing Mainframe Connections

Currently, connections are being handled by the `ConnectionInstance.java`class, located within the `src/main/java/uoa/lavs/utils/objects` directory. The connection is initialised in `App.java`. To set this connection, you can use the following code to set the type of connection and the data path:

```java
Connection connection = new Connection(<dataPath>);
ConnectionInstance.setConnection(connection);
```

## User Interface
For our UI, we used JavaFX with MaterialFX. [More information of MaterialFX can be found here](https://github.com/palexdev/MaterialFX).
