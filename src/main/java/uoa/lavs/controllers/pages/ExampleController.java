package uoa.lavs.controllers.pages;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.services.ExampleService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class ExampleController implements IController {

  private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

  private static final Component<ExampleController> singleton =
      ResourceUtils.loadFxml("pages/example-page.fxml");

  @FXML private Label lblTitle;

  @FXML private FieldController fieldController;

  public static Component<ExampleController> getSingleton() {
    return singleton;
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
          logger.info("User created: " + user);
        },
        throwable -> {
          logger.warn("Error creating user", throwable);
          Platform.runLater(() -> fieldController.setError("Error creating user"));
        });
  }
}
