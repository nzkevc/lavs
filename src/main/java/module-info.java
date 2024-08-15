/**
 * This module-info.java file is used to define the module and its dependencies. You may need to add
 * the dependency here if you ever get a warning like this:
 *
 * <p>package org.slf4j is not visible (package org.slf4j is declared in module org.slf4j, but
 * module uoa.lavs does not read it)
 */
module uoa.lavs {
  // 'Import' modules that this module depends on
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires org.dizitart.no2;
  requires org.dizitart.no2.mvstore;
  requires commons.math3;
  requires org.slf4j;
  requires com.google.common;
  requires okhttp3;

  // Allow javafx to perform reflection on controllers (to inject FXML)
  opens uoa.lavs.controllers to
      javafx.fxml;

  // Export App.java entrypoint
  exports uoa.lavs;
}
