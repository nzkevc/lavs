package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.utils.ControllerUtils;

public class NoteCardController extends AnchorPane implements ICard<String> {

  private static final Logger logger = LoggerFactory.getLogger(NoteCardController.class);

  @FXML private TextArea noteField;

  public NoteCardController() {
    ControllerUtils.loadFxml(this, "cards/customer/notes.fxml");
  }

  @Override
  public void render(String note) {
    noteField.setText(note);
  }

  @Override
  public void clear() {
    noteField.clear();
  }

  @Override
  public String assemble() {
    return noteField.getText();
  }
}
