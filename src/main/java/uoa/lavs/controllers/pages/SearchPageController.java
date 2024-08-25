package uoa.lavs.controllers.pages;

import java.util.List;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.State;
import uoa.lavs.models.Customer;
import uoa.lavs.services.CustomerService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;

public class SearchPageController extends IPage {
  private static final Logger logger = LoggerFactory.getLogger(SearchPageController.class);
  private static final int SEARCH_DELAY_SECONDS = 1;
  private static final List<String> SearchTypes = List.of("By Name", "By ID");

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
    searchType.getItems().addAll(SearchTypes);
    searchType.setValue(SearchTypes.getFirst());

    PauseTransition searchPause = new PauseTransition(Duration.seconds(SEARCH_DELAY_SECONDS));
    searchPause.setOnFinished(
        e -> {
          if (!customerIdInput.getText().isEmpty()) {
            searchCustomer();
          }
        });

    // only when the user stops typing, we search for the customer ^
    customerIdInput
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              notification.setVisible(false);
              customerList.setVisible(false);
              searchPause.stop();
              searchPause.play();
            });
    customerIdInput.setOnAction(e -> searchCustomer());
    goButton.setOnAction(e -> searchCustomer());

    customerList
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue != null) {
                goToCustomerPage(newValue);
              }
            });

    addButton.setOnAction(e -> addCustomer());
  }

  private void searchCustomer() {
    String inputString = customerIdInput.getText();

    if (searchType.getValue().equals("By Name")) {
      searchCustomerByName(inputString);
    } else if (searchType.getValue().equals("By ID")) {
      searchCustomerById(inputString);
    }
  }

  private void searchCustomerByName(String inputString) {
    logger.debug("Searching for customer with name: " + inputString);
    AsyncUtils.promise(
        () -> CustomerService.getCustomerListByName(inputString),
        (customers) -> {
          if (customers.isEmpty()) {
            displayNotFoundNotification();
            return;
          }
          displayFoundCustomerNames(customers);
        },
        (Throwable e) -> {
          logger.debug("Error searching for customer by name: " + e.getMessage());
          displayNotFoundNotification();
        });
  }

  private void searchCustomerById(String inputString) {
    logger.debug("Searching for customer with id: " + inputString);
    AsyncUtils.promise(
        () -> CustomerService.getCustomer(inputString),
        (customer) -> {
          displayFoundById(customer);
          State.customerFromSearch.setValue(customer);
        },
        (Throwable e) -> {
          logger.debug("Error searching for customer by id: " + e.getMessage());
          displayNotFoundNotification();
          State.customerFromSearch.setValue(null);
        });
  }

  private void goToCustomerPage(Customer partialCustomer) {
    AsyncUtils.promise(
        () -> CustomerService.getCustomer(partialCustomer.getId()),
        (customer) -> {
          State.customerFromSearch.setValue(customer);
          logger.debug("Going to customer page");
          customerIdInput.setText("");
          App.getMainController().switchPage(SummaryPageController.class);
        },
        (Throwable e) -> {
          logger.debug("Error getting customer from list view: " + e.getMessage());
          displayNotFoundNotification();
          State.customerFromSearch.setValue(null);
        });
  }

  private void addCustomer() {
    logger.debug("Adding new customer");
    customerIdInput.setText("");
    State.customerFromSearch.setValue(null);
    App.getMainController().switchPage(SummaryPageController.class);
  }

  private void displayFoundById(Customer customer) {
    notification.setText("Customer found: " + customer.getName());
    notification.setVisible(true);

    customerList.getItems().clear();
    customerList.getItems().add(customer);
    customerList.setVisible(true);
  }

  private void displayFoundCustomerNames(List<Customer> customers) {
    notification.setText("Customers found:");
    notification.setVisible(true);

    customerList.getItems().clear();
    customerList.getItems().addAll(customers);
    customerList.setVisible(true);
  }

  private void displayNotFoundNotification() {
    notification.setText("Customer not found.");
    notification.setVisible(true);
  }
}
