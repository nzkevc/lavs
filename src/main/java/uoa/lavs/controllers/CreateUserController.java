package uoa.lavs.controllers;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import uoa.lavs.models.User;
import uoa.lavs.services.ExampleService;

public class CreateUserController {

  @FXML
  private void createUser() throws IOException {
    // Imagine this is read from a text field (view -> controller)
    String name = "John Doe";

    // Offload the business logic to the service (controller -> service)
    List<User> database = ExampleService.createUser(name);

    // Imagine this is sent to the view (controller -> view)
    System.out.println(database);
  }
}
