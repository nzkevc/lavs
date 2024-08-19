package uoa.lavs;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uoa.lavs.utils.objects.Component;

/**
 * State is a class that contains public static properties to represent global state. It can also
 * contain methods to act on the state. Controllers will listen to these properties to update their
 * views (see ExampleController).
 */
public class State {
  public static final Property<String> exampleTitle = new SimpleStringProperty();
  public static final Property<Component<?>> page = new SimpleObjectProperty<>();

  public static void reset() {
    exampleTitle.setValue("Example Title");
    // page.setValue(ExampleController.getSingleton());
  }
}
