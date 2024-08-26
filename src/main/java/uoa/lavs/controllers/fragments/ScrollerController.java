package uoa.lavs.controllers.fragments;

import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.ReflectionUtils;

public class ScrollerController<T> extends ICard<Set<T>> {

  private static final Logger logger = LoggerFactory.getLogger(ScrollerController.class);

  @FXML private VBox displayVbox;
  @FXML private Button addBtn;

  private Class<? extends ICard<T>> cardControllerClass;

  public ScrollerController() {
    ControllerUtils.loadFxml(this, "fragments/scroller.fxml");
  }

  public ScrollerController(Class<? extends ICard<T>> cardControllerClass) {
    this();
    this.cardControllerClass = cardControllerClass;
  }

  public ScrollerController(Class<? extends ICard<T>> cardControllerClass, String modelName) {
    this(cardControllerClass);
    addBtn.setText("Add " + modelName);
  }

  @Override
  public void render(Set<T> models) {
    clear();
    for (T model : models) {
      ICard<T> cardController = ReflectionUtils.instantiate(cardControllerClass);
      cardController.render(model);
      displayVbox.getChildren().add(cardController);
    }
  }

  @Override
  public void clear() {
    displayVbox.getChildren().clear();
  }

  @Override
  public Set<T> assemble() {
    Set<T> models = new HashSet<>();
    for (Node node : displayVbox.getChildren()) {
      T model = (cardControllerClass.cast(node)).assemble();
      models.add(model);
    }
    return models;
  }

  @FXML
  private void onAddClick() {
    ICard<T> cardController = ReflectionUtils.instantiate(cardControllerClass);
    cardController.clear();
    displayVbox.getChildren().add(cardController);
  }
}
