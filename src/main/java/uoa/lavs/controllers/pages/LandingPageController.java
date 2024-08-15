package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;

public class LandingPageController implements IController {

  private static Parent root;

  @FXML private AnchorPane panRoot;

  public static Parent getRoot() {
    return root;
  }

  public void initialize() {
    root = panRoot;
  }
}
