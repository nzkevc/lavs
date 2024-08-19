package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ControllerUtils;

public class NoteCardController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(NoteCardController.class);

  @FXML private TextArea note;

  public NoteCardController() {
    ControllerUtils.loadFxml(this, "cards/customer/notes.fxml");
  }
}
