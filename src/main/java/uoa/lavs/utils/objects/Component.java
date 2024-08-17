package uoa.lavs.utils.objects;

import javafx.scene.Parent;

/**
 * A class that represents a component in the application. Think of components as pairs of a View
 * and a Controller. More precisely, a 'View' is the root node of a FXML file, and a 'Controller' is
 * an instance of the controller class linked to in the fxml file. <T> is the type of the controller
 * class.
 *
 * <p>Note: Sorry for the confusing naming, but pages (and also main) are also considered
 * components.
 */
public class Component<T> {

  private final String name;
  private final Parent view;
  private final T controller;

  public Component(String name, Parent view, T controller) {
    this.name = name;
    this.view = view;
    this.controller = controller;
  }

  /** Name (fxml url location) of the component. */
  public String getName() {
    return name;
  }

  /** Get root node in fxml file. */
  public Parent getView() {
    return view;
  }

  /** Get INSTANCE of controller. */
  public T getController() {
    return controller;
  }
}
