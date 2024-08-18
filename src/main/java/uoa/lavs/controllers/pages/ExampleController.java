package uoa.lavs.controllers.pages;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uoa.lavs.State;
import uoa.lavs.controllers.IController;
import uoa.lavs.services.ExampleService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class ExampleController implements IController {

  private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

  private static final Component<ExampleController> singleton =
      ResourceUtils.loadFxml("pages/example-page.fxml");

  @FXML private Label lblTitle;

  public static Component<ExampleController> getSingleton() {
    return singleton;
  }

  @FXML
  public void initialize() {
    State.exampleTitle.addListener((observable, oldValue, newValue) -> lblTitle.setText(newValue));
  }

  @FXML
  private void createUser() throws IOException, InterruptedException, ExecutionException {

    String name = "John Doe"; // Imagine this is read from a text field

    AsyncUtils.promise(() -> ExampleService.createUser(name))
        .addListener(() -> logger.info("User created successfully"), Runnable::run);
  }
}
