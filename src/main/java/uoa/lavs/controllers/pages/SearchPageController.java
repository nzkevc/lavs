package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.State;
import uoa.lavs.services.CustomerService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;

public class SearchPageController extends AnchorPane implements IPage {
  private static final Logger logger = LoggerFactory.getLogger(SearchPageController.class);

  @FXML private Button addButton;
  @FXML private Button goButton;
  @FXML private TextField customerIdInput;
  @FXML private Label notification;

  public SearchPageController() {
    ControllerUtils.loadFxml(this, "pages/search-page.fxml");
  }

  @FXML
  private void initialize() {
    customerIdInput
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              notification.setVisible(false);
              searchCustomer();
            });
    customerIdInput.setOnAction(e -> goToCustomerPage());
    goButton.setOnAction(e -> goToCustomerPage());
    addButton.setOnAction(e -> addCustomer());
  }

  private void searchCustomer() {
    logger.debug("Searching for customer with id: " + customerIdInput.getText());
    String inputString = customerIdInput.getText();
    if (inputString.isEmpty()) {
      displayNotFoundNotification();
      return;
    }

    AsyncUtils.promise(
        () -> CustomerService.getCustomer(inputString),
        (customer) -> {
          displayFoundNotification(customer.getName());
          State.customerFromSearch.setValue(customer);
        },
        (Throwable e) -> {
          logger.debug("Error searching for customer: " + e.getMessage());
          displayNotFoundNotification();
          State.customerFromSearch.setValue(null);
        });
  }

  private void goToCustomerPage() {
    if (customerIdInput.getText().equals(State.customerFromSearch.getValue().getId())) {
      logger.debug("Going to customer page");
      App.getMainController().switchPage(SummaryPageController.class);
    }
  }

  private void addCustomer() {
    logger.debug("Adding new customer");
    customerIdInput.setText("");
    State.customerFromSearch.setValue(null);
    App.getMainController().switchPage(SummaryPageController.class);
  }

  private void displayFoundNotification(String customerName) {
    notification.setText("Customer found: " + customerName);
    notification.setVisible(true);
  }

  private void displayNotFoundNotification() {
    notification.setText("Customer not found.");
    notification.setVisible(true);
  }
}
