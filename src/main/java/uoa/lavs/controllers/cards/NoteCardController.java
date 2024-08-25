package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.utils.ControllerUtils;

public class NoteCardController extends ICard<String> {

  private static final Logger logger = LoggerFactory.getLogger(NoteCardController.class);

  @FXML private TextArea noteField;

  public NoteCardController() {
    ControllerUtils.loadFxml(this, "cards/notes-card.fxml");
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
