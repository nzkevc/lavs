package uoa.lavs.controllers.pages;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.layout.AnchorPane;
import uoa.lavs.State;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.services.ExampleService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;

public class ExampleController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

  @FXML private Label lblTitle;

  @FXML private FieldController fieldController;

  public ExampleController() {
    ControllerUtils.loadFxml(this, "pages/example-page.fxml");
  }

  @FXML
  public void initialize() {
    State.exampleTitle.addListener((observable, oldValue, newValue) -> lblTitle.setText(newValue));
  }

  @FXML
  private void createUser() throws IOException, InterruptedException, ExecutionException {

    String name = fieldController.getValue();

    AsyncUtils.promise(
        () -> ExampleService.createUser(name),
        user -> {
          Platform.runLater(() -> fieldController.clearError());
          logger.info("User created: " + user);
        },
        throwable -> {
          // logger.warn("Error creating user", throwable);
          Platform.runLater(() -> fieldController.setError("Error creating user"));
        });
  }
}
