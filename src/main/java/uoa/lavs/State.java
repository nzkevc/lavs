package uoa.lavs;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

/**
 * State is a class that contains public static properties to represent global state. It can also
 * contain methods to act on the state. Controllers will listen to these properties to update their
 * views (see ExampleController).
 */
public class State {
  public static Property<String> exampleTitle = new SimpleStringProperty();
}
