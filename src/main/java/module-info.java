/**
 * This module-info.java file is used to define the module and its dependencies. You may need to add
 * the dependency here if you ever get a warning like this:
 *
 * <p>Open to allow JavaFX to reflect on our controllers.
 */
open module uoa.lavs {
  // 'Import' modules that this module depends on
  requires transitive javafx.graphics; // So that clients of this module can pass in a Stage object
  requires javafx.controls;
  requires javafx.fxml;
  requires org.dizitart.no2;
  requires org.dizitart.no2.mvstore;
  requires commons.math3;
  requires org.slf4j;
  requires com.google.common;
  requires okhttp3;
  requires MaterialFX;



  // Export App.java entrypoint
  exports uoa.lavs;
}
