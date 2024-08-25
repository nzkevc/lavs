package uoa.lavs.controllers.fragments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uoa.lavs.State;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ControllerUtils;

public class FieldController extends IController {

  private static final Logger logger = LoggerFactory.getLogger(FieldController.class);

  @FXML private Label lblKey;
  @FXML private TextField txtValue;
  @FXML private Label lblError;

  public FieldController() {
    ControllerUtils.loadFxml(this, "fragments/field.fxml");
  }

  @FXML
  private void initialize() {
    txtValue.textProperty().addListener((observable, oldValue, newValue) -> clearError());
    txtValue
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              State.clearMessage();
            });
  }

  public void setKey(String key) {
    lblKey.setText(key);
  }

  public String getKey() {
    return lblKey.getText();
  }

  public void setValue(String value) {
    txtValue.setText(value);
  }

  public String getValue() {
    return txtValue.getText();
  }

  public void clearValue() {
    txtValue.clear();
  }

  public Property<String> valueProperty() {
    return txtValue.textProperty();
  }

  public void setError(String error) {
    lblError.setText(error);
  }

  public void clearError() {
    lblError.setText("");
  }

  public void setEditable(boolean editable) {
    txtValue.setDisable(!editable);
  }
}
