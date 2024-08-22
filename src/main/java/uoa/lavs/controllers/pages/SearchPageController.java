package uoa.lavs.controllers.pages;

import java.util.List;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.State;
import uoa.lavs.models.Customer;
import uoa.lavs.services.CustomerService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;

public class SearchPageController extends AnchorPane implements IPage {
  private static final Logger logger = LoggerFactory.getLogger(SearchPageController.class);
  private static final int SEARCH_DELAY_SECONDS = 1;

  private static enum SearchTypes {
    ByName,
    ByID
  }

  @FXML private Button addButton;
  @FXML private Button goButton;
  @FXML private TextField customerIdInput;
  @FXML private ListView<Customer> customerList;
  @FXML private ChoiceBox<String> searchType;
  @FXML private Label notification;

  public SearchPageController() {
    ControllerUtils.loadFxml(this, "pages/search-page.fxml");
  }

  @FXML
  private void initialize() {
    searchType.getItems().addAll(SearchTypes.values().toString());
    searchType.setValue(SearchTypes.ByName.toString());

    PauseTransition searchPause = new PauseTransition(Duration.seconds(SEARCH_DELAY_SECONDS));
    searchPause.setOnFinished(e -> searchCustomer());

    // only when the user stops typing, we search for the customer ^
    customerIdInput
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              notification.setVisible(false);
              notification.setVisible(false);
              searchPause.stop();
              searchPause.play();
            });
    customerIdInput.setOnAction(e -> searchCustomer());
    goButton.setOnAction(e -> searchCustomer());

    addButton.setOnAction(e -> addCustomer());
  }

  private void searchCustomer() {
    logger.debug("Searching for customer with id: " + customerIdInput.getText());
    String inputString = customerIdInput.getText();
    if (inputString.isEmpty()) {
      displayNotFoundNotification();
      return;
    }

    if (searchType.getValue().equals(SearchTypes.ByName.toString())) {
      searchCustomerByName(inputString);
    } else if (searchType.getValue().equals(SearchTypes.ByID.toString())) {
      searchCustomerById(inputString);
    }
    AsyncUtils.promise(
        () -> CustomerService.getCustomer(inputString),
        (customer) -> {
          displayFoundById(customer.getName());
          State.customerFromSearch.setValue(customer);
        },
        (Throwable e) -> {
          logger.debug("Error searching for customer: " + e.getMessage());
          displayNotFoundNotification();
          State.customerFromSearch.setValue(null);
        });
  }

  private void searchCustomerByName(String inputString) {
    logger.debug("Searching for customer with name: " + inputString);
    AsyncUtils.promise(
        () -> CustomerService.getCustomerListByName(inputString),
        (customers) -> {
          displayFoundCustomerNames(customers);
        },
        (Throwable e) -> {
          logger.debug("Error searching for customer by name: " + e.getMessage());
          displayNotFoundNotification();
        });
  }

  private void displayFoundCustomerNames(List<Customer> customers) {
    customerList.getItems().clear();
    customerList.getItems().addAll(customers);
    customerList.setVisible(true);
  }

  private void searchCustomerById(String inputString) {
    logger.debug("Searching for customer with id: " + inputString);
    AsyncUtils.promise(
        () -> CustomerService.getCustomer(inputString),
        (customer) -> {
          customerList.getItems().clear();
          customerList.getItems().add(customer);
          displayFoundById(customer.getName());
          State.customerFromSearch.setValue(customer);
        },
        (Throwable e) -> {
          logger.debug("Error searching for customer by id: " + e.getMessage());
          displayNotFoundNotification();
          State.customerFromSearch.setValue(null);
        });
  }

  private void goToCustomerPage() {
    if (State.customerFromSearch.getValue() != null
        && customerIdInput.getText().equals(State.customerFromSearch.getValue().getId())) {
      logger.debug("Going to customer page");
      customerIdInput.setText("");
      App.getMainController().switchPage(SummaryPageController.class);
    }
  }

  private void addCustomer() {
    logger.debug("Adding new customer");
    customerIdInput.setText("");
    State.customerFromSearch.setValue(null);
    App.getMainController().switchPage(SummaryPageController.class);
  }

  private void displayFoundById(String customerName) {
    notification.setText("Customer found: " + customerName);
    notification.setVisible(true);
  }

  private void displayNotFoundNotification() {
    notification.setText("Customer not found.");
    notification.setVisible(true);
  }
}
