package uoa.lavs.controllers.pages;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.State;
import uoa.lavs.controllers.cards.ContactCardController;
import uoa.lavs.controllers.cards.EmployerCardController;
import uoa.lavs.controllers.cards.GeneralInfoCardController;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.controllers.cards.LoanCardController;
import uoa.lavs.controllers.cards.NoteCardController;
import uoa.lavs.controllers.fragments.ScrollerController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Loans;
import uoa.lavs.models.Phone;
import uoa.lavs.services.CustomerService;
import uoa.lavs.services.LoanService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.DevEntityCreator;

public class SummaryPageController extends IPage {

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
    setUpBindings();

    // Rerender customer when customerFromSearch changes
    State.customerFromSearch.addListener(
        (obs, oldCustomer, newCustomer) -> {
          if (newCustomer == null) {
            clearAll();
            return;
          }
          renderCustomer(newCustomer);
        });
  }

  private void setUpCards() {
    cards.put(GeneralInfoCardController.class, new GeneralInfoCardController());
    cards.put(EmployerCardController.class, new EmployerCardController());
    cards.put(ContactCardController.class, new ContactCardController());
    cards.put(NoteCardController.class, new NoteCardController());
    cards.put(LoanCardController.class, new ScrollerController<>(LoanCardController.class));

    switchCard(GeneralInfoCardController.class);
  }

  private void switchCard(Class<? extends ICard<?>> card) {
    logger.debug("Switching to card: " + card.getSimpleName());
    ControllerUtils.swapComponent(infoCard, cards.get(card));
  }

  private void setUpBindings() {
    // Info pane
    customerName
        .textProperty()
        .bind(State.customerName.map(name -> name.isEmpty() ? "New Customer" : name));
    customerID.textProperty().bind(State.customerId.map(id -> "Customer ID: " + id));

    // Error/success message
    errorLbl.textProperty().bind(State.summaryMessage);
    errorLbl
        .styleProperty()
        .bind(
            State.summaryMessageIsError.map(
                isError -> isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;"));
  }

  @FXML
  private void onBackBtnClick() {
    clearAll();
    App.getMainController().switchPage(SearchPageController.class);
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
    switchCard(LoanCardController.class);
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

  private ScrollerController<Loan> getLoansCard() {
    return (ScrollerController<Loan>) cards.get(LoanCardController.class);
  }

  private void renderCustomer(Customer customer) {
    State.customerId.setValue(customer.getId() == null ? "" : customer.getId());
    State.customerName.setValue(customer.getName());
    getGeneralInfoCard().render(customer);
    Address address = customer.getAddresses().getResidentialAddress();
    Phone phone = customer.getPhones().getPrimaryPhone();
    Email email = customer.getEmails().getPrimaryEmail();
    getContactCard().render(new ContactCardController.ContactTriple(address, phone, email));

    // TODO: change this to render multiple employers
    getEmployerCard()
        .render(
            customer.getEmployers().getEmployers().stream()
                .min(Comparator.comparing(Employer::getNumber))
                .orElse(null));
    getNoteCard().render(customer.getNotes());
    getLoansCard().render(customer.getLoans().getLoans());
  }

  private void clearAll() {
    getGeneralInfoCard().clear();
    getContactCard().clear();
    getEmployerCard().clear();
    getNoteCard().clear();
    State.customerId.setValue("");
    State.customerName.setValue("");
    getLoansCard().clear();
  }

  // TODO: I have a feeling switching to sets means updating might not work anymore
  private Customer assembleCustomer() {
    Customer customer = getGeneralInfoCard().assemble();
    customer.setId(State.customerId.getValue());
    customer.getAddresses().addAddress(getContactCard().assemble().getAddress());
    customer.getAddresses().addAddress(getContactCard().assemble().getAddress());
    customer.getPhones().addPhone(getContactCard().assemble().getPhone());
    customer.getPhones().addPhone(getContactCard().assemble().getPhone());
    customer.getEmails().addEmail(getContactCard().assemble().getEmail());

    // TODO: This and a lot of these might need to change
    customer.getEmployers().addEmployer(getEmployerCard().assemble());
    customer.setNotes(getNoteCard().assemble());
    customer.setLoans(new Loans(getLoansCard().assemble()));
    return customer;
  }

  @FXML
  private void onSubmitBtnClick() {
    boolean isCreating = State.customerId.getValue().isEmpty();
    if (isCreating) {
      logger.debug("Creating new customer...");
    } else {
      logger.debug("Updating existing customer...");
    }
    AsyncUtils.promise(
        () -> {
          Customer customer = assembleCustomer();
          if (isCreating) {
            customer.setId(null);
            CustomerService.createCustomer(customer);
            LoanService.createLoansByCustomerId(customer.getId(), customer.getLoans());
          } else {
            CustomerService.updateCustomer(customer);
            Loans newLoans = new Loans();
            customer.getLoans().getLoans().stream()
                .filter(loan -> loan.getLoanId() == null || loan.getLoanId().isEmpty())
                .forEach((loan) -> newLoans.addLoan(loan));
            LoanService.createLoansByCustomerId(customer.getId(), newLoans);
          }
          return customer;
        },
        (customer) -> {
          renderCustomer(customer);
          if (isCreating) {
            State.setMessageSuccess("Customer created successfully with id: " + customer.getId());
          } else {
            State.setMessageSuccess("Customer updated successfully with id: " + customer.getId());
          }
        },
        this::handleException);
  }

  @FXML
  private void onTestGetBtnClick() {
    logger.debug("Getting TestEntity customer...");
    AsyncUtils.promise(
        () -> DevEntityCreator.createFullCustomer(),
        (customer) -> State.customerFromSearch.setValue(customer),
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
    State.setMessageError(e.getMessage());
  }
}
