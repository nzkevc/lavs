package uoa.lavs.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.models.ExampleUser;
import uoa.lavs.services.ExampleService;

public class ExampleController implements Initializable {

  private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

  @FXML private Label lblTitle;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    State.exampleTitle.addListener((observable, oldValue, newValue) -> lblTitle.setText(newValue));
  }

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
