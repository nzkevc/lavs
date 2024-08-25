package uoa.lavs.controllers.pages;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.App;
import uoa.lavs.State;
import uoa.lavs.controllers.cards.AddressCardController;
import uoa.lavs.controllers.cards.ContactCardController;
import uoa.lavs.controllers.cards.EmailCardController;
import uoa.lavs.controllers.cards.EmployerCardController;
import uoa.lavs.controllers.cards.GeneralInfoCardController;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.controllers.cards.LoanCardController;
import uoa.lavs.controllers.cards.NoteCardController;
import uoa.lavs.controllers.cards.PhoneCardController;
import uoa.lavs.controllers.fragments.ScrollerController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Employers;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Loans;
import uoa.lavs.models.Phone;
import uoa.lavs.models.Phones;
import uoa.lavs.services.CustomerService;
import uoa.lavs.services.LoanService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.ContactInfo;
import uoa.lavs.utils.objects.DevEntityCreator;

public class SummaryPageController extends IPage {

  private static final Logger logger = LoggerFactory.getLogger(SummaryPageController.class);

  private final Map<Class<? extends ICard<?>>, Parent> cards = new HashMap<>();
  private Class<? extends ICard<?>> currentCard;

  @FXML private Label errorLbl;

  @FXML private Label customerName;
  @FXML private Label customerID;

  @FXML private AnchorPane infoCard;

  public SummaryPageController() {
    ControllerUtils.loadFxml(this, "pages/summary-page.fxml");
  }

  @FXML
  private void initialize() {
    setUpCards();
    setUpBindings();
    State.setAssembleCustomerFunction(this::assembleCustomer);

    // Rerender customer when customerFromSearch changes
    State.customerFromSearch.addListener(
        (obs, oldCustomer, newCustomer) -> {
          if (newCustomer == null) {
            clearAll();
            return;
          }
          logger.trace("Rendering customer...");
          renderCustomer(newCustomer);
        });
  }

  private void setUpCards() {
    cards.put(GeneralInfoCardController.class, new GeneralInfoCardController());

    cards.put(EmployerCardController.class, new ScrollerController<>(EmployerCardController.class, "Employer"));

    cards.put(
        AddressCardController.class,
        new ScrollerController<>(AddressCardController.class, "Address"));
    cards.put(PhoneCardController.class, new ScrollerController<>(PhoneCardController.class, "Phone"));
    cards.put(EmailCardController.class, new ScrollerController<>(EmailCardController.class, "Email"));

    // Not visible but let's keep it for rendering and assembling Address, Phone, Email
    cards.put(
        ContactCardController.class,
        new ContactCardController(getAddressCard(), getPhoneCard(), getEmailCard()));

    cards.put(NoteCardController.class, new NoteCardController());
    cards.put(LoanCardController.class, new ScrollerController<>(LoanCardController.class, "Loan"));

    switchCard(GeneralInfoCardController.class);
  }

  private void switchCard(Class<? extends ICard<?>> card) {
    logger.debug("Switching to card: " + card.getSimpleName());
    ControllerUtils.swapComponent(infoCard, cards.get(card));
    currentCard = card;
  }

  private void setUpBindings() {
    // Info pane
    customerName
        .textProperty()
        .bind(
            State.customerName.map(
                name -> {
                  logger.debug("Name changed: " + name);
                  return name.isEmpty() ? "New Customer" : name;
                }));
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
    if (cards.get(currentCard) == getPhoneCard() || cards.get(currentCard) == getEmailCard()) {
      return;
    }
    switchCard(AddressCardController.class);
  }

  @FXML
  private void onContactAddressBtnClick(Event event) {
    event.consume();
    switchCard(AddressCardController.class);
  }

  @FXML
  private void onContactPhoneBtnClick(Event event) {
    event.consume();
    switchCard(PhoneCardController.class);
  }

  @FXML
  private void onContactEmailBtnClick(Event event) {
    event.consume();
    switchCard(EmailCardController.class);
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

  private ScrollerController<Address> getAddressCard() {
    return (ScrollerController<Address>) cards.get(AddressCardController.class);
  }

  private ScrollerController<Email> getEmailCard() {
    return (ScrollerController<Email>) cards.get(EmailCardController.class);
  }

  private ScrollerController<Phone> getPhoneCard() {
    return (ScrollerController<Phone>) cards.get(PhoneCardController.class);
  }

  private ScrollerController<Employer> getEmployerCard() {
    return (ScrollerController<Employer>) cards.get(EmployerCardController.class);
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

    Addresses addresses = customer.getAddresses();
    Phones phones = customer.getPhones();
    Emails emails = customer.getEmails();
    getContactCard().render(new ContactInfo(addresses, phones, emails));

    getEmployerCard().render(customer.getEmployers().getEmployers());
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

  private Customer assembleCustomer() {
    logger.debug("Assembling customer...");
    Customer customer = getGeneralInfoCard().assemble();
    customer.setId(State.customerId.getValue());

    Addresses addresses = getContactCard().assemble().addresses();
    Phones phones = getContactCard().assemble().phones();
    Emails emails = getContactCard().assemble().emails();
    customer.setAddresses(addresses);
    customer.setPhones(phones);
    customer.setEmails(emails);

    customer.setEmployers(new Employers(getEmployerCard().assemble()));
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
  private void onTestSaveBtnClick() {
    State.saveState();
  }

  @FXML
  private void onTestLoadBtnClick() {
    State.loadState();
  }

  private void handleException(Throwable e) {
    State.setMessageError(e.getMessage());
    e.printStackTrace();
  }
}
