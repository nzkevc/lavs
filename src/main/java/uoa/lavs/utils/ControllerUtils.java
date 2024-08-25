package uoa.lavs.utils;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.controllers.IController;

public class ControllerUtils {

  private static final Logger logger = LoggerFactory.getLogger(ControllerUtils.class);

  public static void swapComponent(Pane parent, Node newChild) {
    parent.getChildren().clear();
    parent.getChildren().add(newChild);
  }

  /**
   * Load an FXML file into a controller. Call this in the constructor of the controller.
   *
   * @param controller the controller to load the FXML into (pass in `this`)
   */
  public static void loadFxml(IController controller, String url) {
    logger.trace("Loading FXML: " + url);
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + url));
    fxmlLoader.setRoot(controller);
    fxmlLoader.setController(controller);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      logger.error("Error loading FXML", e);
    }
  }

  public static void renderText(TextField field, Object text) {
    renderText(field, text, null);
  }

  public static void renderText(TextField field, Object text, String fallBackIfNull) {
    if (fallBackIfNull == null) {
      fallBackIfNull = "";
    }
    field.setText(text == null ? fallBackIfNull : String.valueOf(text));
  }

  public static <T> void renderCombo(MFXComboBox<T> combo, T value) {
    renderCombo(combo, value, value);
  }

  public static <T> void renderCombo(MFXComboBox<T> combo, T value, T fallBackIfNull) {
    combo.getSelectionModel().selectItem(ValidationUtils.isNullOrBlank(value) ? fallBackIfNull : value);
  }

  public static void renderDate(MFXDatePicker field, LocalDate date) {
    field.setValue(ValidationUtils.isNullOrBlank(date) ? LocalDate.EPOCH : date);
  }
}
