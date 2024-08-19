package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ControllerUtils;

public class NoteController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

  @FXML private TextArea note;

  public NoteController() {
    ControllerUtils.loadFxml(this, "fragments/cards/customer/notes.fxml");
  }
}
