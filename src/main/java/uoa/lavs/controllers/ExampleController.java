package uoa.lavs.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.services.ExampleService;
import uoa.lavs.utils.AsyncUtils;

public class ExampleController implements Initializable {

  private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

  @FXML private Label lblTitle;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    State.exampleTitle.addListener((observable, oldValue, newValue) -> lblTitle.setText(newValue));
  }

  @FXML
  private void createUser() throws IOException, InterruptedException, ExecutionException {

    String name = "John Doe"; // Imagine this is read from a text field

    AsyncUtils.promise(() -> ExampleService.createUser(name))
        .addListener(() -> logger.info("User created successfully"), Runnable::run);
  }
}
