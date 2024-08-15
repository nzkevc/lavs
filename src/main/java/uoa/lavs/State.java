package uoa.lavs;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Parent;
import uoa.lavs.controllers.pages.LandingPageController;

/**
 * State is a class that contains public static properties to represent global state. It can also
 * contain methods to act on the state. Controllers will listen to these properties to update their
 * views (see ExampleController).
 */
public class State {
  public static Property<String> exampleTitle = new SimpleStringProperty();
  public static Property<Parent> page = new SimpleObjectProperty<>();

  public static void reset() {
    exampleTitle.setValue("Example Title");
    page.setValue(LandingPageController.getRoot());
  }
}
