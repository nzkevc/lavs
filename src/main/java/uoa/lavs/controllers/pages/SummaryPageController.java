package uoa.lavs.controllers.pages;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.MainController;
import uoa.lavs.controllers.cards.ContactCardController;
import uoa.lavs.controllers.cards.EmployerCardController;
import uoa.lavs.controllers.cards.GeneralInfoCardController;
import uoa.lavs.controllers.cards.NoteCardController;
import uoa.lavs.utils.ControllerUtils;

public class SummaryPageController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(SummaryPageController.class);

  private final Map<Class<?>, Parent> cards = new HashMap<>();

  @FXML private Button backBtn;

  @FXML private Label errorLbl;
  @FXML private Button submitBtn;

  @FXML private AnchorPane infoPane;
  @FXML private Label customerName;
  @FXML private Label customerID;

  @FXML private Button generalBtn;
  @FXML private Button contactBtn;
  @FXML private Button employerBtn;
  @FXML private Button notesBtn;
  @FXML private Button loansBtn;

  @FXML private AnchorPane infoCard;

  public SummaryPageController() {
    ControllerUtils.loadFxml(this, "pages/summary-page.fxml");
  }

  @FXML
  public void initialize() {
    setUpCards();
  }

  private void setUpCards() {
    cards.put(GeneralInfoCardController.class, new GeneralInfoCardController());
    cards.put(EmployerCardController.class, new EmployerCardController());
    cards.put(ContactCardController.class, new ContactCardController());
    cards.put(NoteCardController.class, new NoteCardController());
    switchCard(GeneralInfoCardController.class);
  }

  public void switchCard(Class<?> card) {
    logger.debug("Switching to card: " + card.getSimpleName());
    ControllerUtils.swapComponent(infoPane, cards.get(card));
  }

  @FXML
  private void onBackBtnClick() {
    MainController.getInstance().switchPage(LandingPageController.class);
  }
}
