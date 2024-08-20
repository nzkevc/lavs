package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.services.CustomerService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ControllerUtils;

public class SearchPageController extends AnchorPane implements IPage {
  private static final Logger logger = LoggerFactory.getLogger(SearchPageController.class);

  @FXML private Button addButton;
  @FXML private Button goButton;
  @FXML private TextField customerIdInput;
  @FXML private Label notification;

  private String inputString;

  public SearchPageController() {
    ControllerUtils.loadFxml(this, "pages/search-page.fxml");
  }

  @FXML
  private void initialize() {
    customerIdInput
        .textProperty()
        .addListener((observable, oldValue, newValue) -> notification.setVisible(false));
    customerIdInput.setOnAction(e -> searchCustomer());
    goButton.setOnAction(e -> searchCustomer());

    // TODO: implement
    addButton.setOnAction(
        e -> System.out.println("TODO: implement switching to create customer page"));
  }

  private void searchCustomer() {
    logger.debug("Searching for customer with id: " + customerIdInput.getText());
    inputString = customerIdInput.getText();
    if (inputString.isEmpty()) {
      displayNotFoundNotification();
      return;
    }

    AsyncUtils.promise(
        () -> CustomerService.getCustomer(inputString),
        (customer) -> {
          displayFoundNotification(customer.getName());
        },
        (Throwable e) -> {
          logger.error("Error searching for customer: " + e.getMessage());
          displayNotFoundNotification();
        });
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
