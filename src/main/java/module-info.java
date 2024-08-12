module uoa.lavs {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.dizitart.no2;
  requires org.dizitart.no2.mvstore;
  requires commons.math3;
  requires org.slf4j;

  opens uoa.lavs to
      javafx.fxml;
  opens uoa.lavs.controllers to
      javafx.fxml;

  exports uoa.lavs;
  exports uoa.lavs.controllers;

  requires transitive javafx.graphics;
}
