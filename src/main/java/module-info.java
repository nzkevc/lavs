/**
 * This module-info.java file is used to define the module and its dependencies. You may need to add
 * the dependency here if you ever get a warning like this:
 *
 * <p>package org.slf4j is not visible (package org.slf4j is declared in module org.slf4j, but
 * module uoa.lavs does not read it)
 */
module uoa.lavs {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.dizitart.no2;
  requires org.dizitart.no2.mvstore;
  requires commons.math3;
  requires org.slf4j;
  requires com.google.common;

  opens uoa.lavs to
      javafx.fxml;
  opens uoa.lavs.controllers to
      javafx.fxml;

  exports uoa.lavs;
  exports uoa.lavs.controllers;

  requires transitive javafx.graphics;
}
