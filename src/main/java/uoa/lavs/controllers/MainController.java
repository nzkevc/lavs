package uoa.lavs.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import uoa.lavs.utils.ControllerUtils;

public class MainController implements IController, Initializable {

  Logger logger = LoggerFactory.getLogger(MainController.class);

  @FXML private Pane panPage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("Test");
    logger.debug(panPage.toString());

    Node temp = panPage.getChildren().get(0);
    ControllerUtils.swapComponent(panPage, temp);
  }
}
