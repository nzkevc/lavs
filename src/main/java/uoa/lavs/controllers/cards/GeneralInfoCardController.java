package uoa.lavs.controllers.cards;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoCardController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(GeneralInfoCardController.class);

  @FXML private FieldController name;
  @FXML private FieldController dateOfBirth;
  @FXML private FieldController citizenship;
  @FXML private FieldController visaType;
  @FXML private FieldController occupation;
  @FXML private FieldController status;

  public GeneralInfoCardController() {
    ControllerUtils.loadFxml(this, "cards/customer/general-info.fxml");
  }

  public void renderCustomer(Customer customer) {
    name.setValue(customer.getName());
    dateOfBirth.setValue(customer.getDateOfBirth().toString());
    citizenship.setValue(customer.getCitizenship());
    visaType.setValue(customer.getVisa());
    occupation.setValue(customer.getOccupation());
    status.setValue(customer.getStatus());
  }

  public void clearFields() {
    name.clearValue();
    dateOfBirth.clearValue();
    citizenship.clearValue();
    visaType.clearValue();
    occupation.clearValue();
    status.clearValue();
  }

  public Customer getCustomer() {
    Customer customer = new Customer();
    customer.setName(name.getValue());
    customer.setDateOfBirth(LocalDate.parse(dateOfBirth.getValue())); // Check this
    customer.setCitizenship(citizenship.getValue());
    customer.setVisa(visaType.getValue());
    customer.setOccupation(occupation.getValue());
    customer.setStatus(status.getValue());
    return customer;
  }
}
