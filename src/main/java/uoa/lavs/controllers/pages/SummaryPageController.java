package uoa.lavs.controllers.pages;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.App;
import uoa.lavs.controllers.cards.ContactCardController;
import uoa.lavs.controllers.cards.EmployerCardController;
import uoa.lavs.controllers.cards.GeneralInfoCardController;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.controllers.cards.NoteCardController;
import uoa.lavs.utils.ControllerUtils;

public class SummaryPageController extends AnchorPane implements IPage {

  private static final Logger logger = LoggerFactory.getLogger(SummaryPageController.class);

  private final Map<Class<? extends ICard<?>>, Parent> cards = new HashMap<>();

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
  private void initialize() {
    setUpCards();
  }

  private void setUpCards() {
    cards.put(GeneralInfoCardController.class, new GeneralInfoCardController());
    cards.put(EmployerCardController.class, new EmployerCardController());
    cards.put(ContactCardController.class, new ContactCardController());
    cards.put(NoteCardController.class, new NoteCardController());
    switchCard(GeneralInfoCardController.class);
  }

  private void switchCard(Class<? extends ICard<?>> card) {
    logger.debug("Switching to card: " + card.getSimpleName());
    ControllerUtils.swapComponent(infoCard, cards.get(card));
  }

  @FXML
  private void onBackBtnClick() {
    App.getMainController().switchPage(LandingPageController.class);
  }

  @FXML
  private void onGeneralBtnClick() {
    switchCard(GeneralInfoCardController.class);
  }

  @FXML
  private void onContactBtnClick() {
    switchCard(ContactCardController.class);
  }

  @FXML
  private void onEmployerBtnClick() {
    switchCard(EmployerCardController.class);
  }

  @FXML
  private void onNotesBtnClick() {
    switchCard(NoteCardController.class);
  }

  @FXML
  private void onLoansBtnClick() {
    logger.error("Loans not implemented");
  }
}
