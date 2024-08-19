package uoa.lavs.controllers.cards;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoCardController extends AnchorPane implements ICard<Customer> {

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

  @Override
  public void render(Customer customer) {
    name.setValue(customer.getName());
    dateOfBirth.setValue(customer.getDateOfBirth().toString());
    citizenship.setValue(customer.getCitizenship());
    visaType.setValue(customer.getVisa());
    occupation.setValue(customer.getOccupation());
    status.setValue(customer.getStatus());
  }

  @Override
  public void clear() {
    name.clearValue();
    dateOfBirth.clearValue();
    citizenship.clearValue();
    visaType.clearValue();
    occupation.clearValue();
    status.clearValue();
  }

  @Override
  public Customer assemble() {
    Customer customer = new Customer();
    customer.setName(name.getValue());
    customer.setDateOfBirth(getDateFromField(dateOfBirth));
    customer.setCitizenship(citizenship.getValue());
    customer.setVisa(visaType.getValue());
    customer.setOccupation(occupation.getValue());
    customer.setStatus(status.getValue());
    return customer;
  }

  private LocalDate getDateFromField(FieldController field) {
    try {
      return LocalDate.parse(field.getValue());
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid date of birth - use YYYY-MM-DD format", e);
    }
  }
}
