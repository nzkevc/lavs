package uoa.lavs.controllers.fragments;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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

  public void setCardController(Class<? extends ICard<T>> cardControllerClass) {
    this.cardControllerClass = cardControllerClass;
  }

  @Override
  public void render(Set<T> models) {
    clear();
    for (T model : models) {
        ICard<T> cardController = ReflectionUtils.instantiate(cardControllerClass);
        cardController.render(model);
        displayVbox.getChildren().add((Node) cardController);
    }
    displayVbox.getChildren().add(addBtn);
  }

  @Override
  public void clear() {
    displayVbox.getChildren().clear();
  }

  @Override
  public Set<T> assemble() {
    Set<T> models = new HashSet<>();
    for (Node node : displayVbox.getChildren().filtered(child -> cardControllerClass.isInstance(child))) {
      T model = (cardControllerClass.cast(node)).assemble();
      models.add(model);
    }
    return models;
  }

  @FXML
  private void onAddClick() {
    ICard<T> cardController = ReflectionUtils.instantiate(cardControllerClass);
    cardController.clear();
    displayVbox.getChildren().remove(addBtn);
    displayVbox.getChildren().add(cardController);
    displayVbox.getChildren().add(addBtn);
  }
}
