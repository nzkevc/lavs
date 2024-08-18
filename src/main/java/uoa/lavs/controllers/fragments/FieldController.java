package uoa.lavs.controllers.fragments;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.App;
import uoa.lavs.controllers.IController;

public class FieldController extends AnchorPane implements IController {

  @FXML private Label lblKey;
  @FXML private TextField txtValue;
  @FXML private Label lblError;

  public FieldController() {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/fragments/field.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setKey(String key) {
    lblKey.setText(key);
  }

  public String getKey() {
    return lblKey.getText();
  }

  public String getValue() {
    return txtValue.getText();
  }

  public void setError(String error) {
    lblError.setText(error);
  }

  public void clearError() {
    lblError.setText("");
  }
}
