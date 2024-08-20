package uoa.lavs.controllers.pages;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.controllers.cards.ContactCardController;
import uoa.lavs.controllers.cards.EmployerCardController;
import uoa.lavs.controllers.cards.GeneralInfoCardController;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.controllers.cards.NoteCardController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Phone;
import uoa.lavs.services.CustomerService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.TestEntityCreator;

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

  private GeneralInfoCardController getGeneralInfoCard() {
    return (GeneralInfoCardController) cards.get(GeneralInfoCardController.class);
  }

  private ContactCardController getContactCard() {
    return (ContactCardController) cards.get(ContactCardController.class);
  }

  private EmployerCardController getEmployerCard() {
    return (EmployerCardController) cards.get(EmployerCardController.class);
  }

  private NoteCardController getNoteCard() {
    return (NoteCardController) cards.get(NoteCardController.class);
  }

  private void renderCustomer(Customer customer) {
    getGeneralInfoCard().render(customer);
    Address address = customer.getAddresses().getResidentialAddress();
    Phone phone = customer.getPhones().getPrimaryPhone();
    Email email = customer.getEmails().getPrimaryEmail();
    getContactCard().render(new ContactCardController.ContactTriple(address, phone, email));
    getEmployerCard().render(customer.getEmployer());
    getNoteCard().render(customer.getNotes());
  }

  private void clearAll() {
    getGeneralInfoCard().clear();
    getContactCard().clear();
    getEmployerCard().clear();
    getNoteCard().clear();
  }

  private Customer assembleCustomer() {
    Customer customer = getGeneralInfoCard().assemble();
    customer.getAddresses().setResidentialAddress(getContactCard().assemble().getAddress());
    customer.getAddresses().setMailingAddress(getContactCard().assemble().getAddress());
    customer.getPhones().setPrimaryPhone(getContactCard().assemble().getPhone());
    customer.getPhones().setTextPhone(getContactCard().assemble().getPhone());
    customer.getEmails().setPrimaryEmail(getContactCard().assemble().getEmail());
    customer.setEmployer(getEmployerCard().assemble());
    customer.setNotes(getNoteCard().assemble());
    return customer;
  }

  @FXML
  private void onSubmitBtnClick() {
    logger.debug("Creating customer...");
    AsyncUtils.promise(
        () -> {
          Customer customer = assembleCustomer();
          CustomerService.createCustomer(customer);
          return customer;
        },
        (customer) -> {
          clearMsgLabel();
          setSuccess("Customer created successfully " + customer.getId());
          renderCustomer(customer);
        },
        this::handleException);
  }

  @FXML
  private void onTestGetBtnClick() {
    logger.debug("Getting TestEntity customer...");
    AsyncUtils.promise(
        () -> TestEntityCreator.createFullCustomer(),
        (customer) -> renderCustomer(customer),
        this::handleException);
  }

  @FXML
  private void onTestClearBtnClick() {
    clearAll();
  }

  @FXML
  private void onTestErrorBtnClick() {
    handleException(new Exception("Submit is currently throwing, so this is redundant for now."));
  }

  private void handleException(Throwable e) {
    setError(e.getMessage());
  }

  private void setSuccess(String msg) {
    logger.debug(msg);
    Platform.runLater(
        () -> {
          errorLbl.setText(msg);
          errorLbl.setStyle("-fx-text-fill: green;");
        });
  }

  private void setError(String msg) {
    logger.warn(msg);
    Platform.runLater(
        () -> {
          errorLbl.setText(msg);
          errorLbl.setStyle("-fx-text-fill: red;");
        });
  }

  private void clearMsgLabel() {
    Platform.runLater(() -> errorLbl.setText(""));
  }
}
