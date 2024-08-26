package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.ValidationException;

public class NoteCardController extends ICard<String> {

  private static final Logger logger = LoggerFactory.getLogger(NoteCardController.class);

  @FXML private TextArea noteField;

  public NoteCardController() {
    ControllerUtils.loadFxml(this, "cards/notes-card.fxml");
  }

  @FXML
  private void initialize() {
    // TODO: see if we can add text limit
  }

  @Override
  public void render(String note) {
    noteField.setText(note);
  }

  @Override
  public void clear() {
    noteField.clear();
  }

  public void validate() {
    try {
      Customer.validateNotes(noteField.getText());
    } catch (ValidationException e) {
      logger.error("Note validation failed:", e.getMessage());
      throw e;
    }
  }

  @Override
  public String assemble() {
    return noteField.getText();
  }
}
