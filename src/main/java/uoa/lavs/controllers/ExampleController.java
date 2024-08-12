package uoa.lavs.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.ExampleUser;
import uoa.lavs.services.ExampleService;

public class ExampleController {

  private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

  @FXML
  private void createUser() throws IOException {
    // Imagine this is read from a text field (view -> controller)
    String name = "John Doe";

    // Offload the business logic to the service (controller -> service)
    ExampleUser createdUser = ExampleService.createUserSync(name);

    // Imagine this is sent to the view (controller -> view)
    logger.debug(createdUser.toString());
  }
}
