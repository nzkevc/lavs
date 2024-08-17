package uoa.lavs.utils;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ControllerUtils {

  public static void swapComponent(Pane parent, Node newChild) {
    parent.getChildren().clear();
    parent.getChildren().add(newChild);
  }
}
