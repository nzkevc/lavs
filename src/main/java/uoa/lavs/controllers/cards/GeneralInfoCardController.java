package uoa.lavs.controllers.cards;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoCardController extends AnchorPane implements ICard<Customer> {

  private static final Logger logger = LoggerFactory.getLogger(GeneralInfoCardController.class);

  @FXML private FieldController title;
  @FXML private FieldController name;
  @FXML private FieldController dateOfBirth;
  @FXML private FieldController citizenship;
  @FXML private FieldController visaType;
  @FXML private FieldController occupation;
  @FXML private FieldController status;

  public GeneralInfoCardController() {
    ControllerUtils.loadFxml(this, "cards/general-info-card.fxml");
  }

  @FXML
  private void initialize() {
    name.valueProperty().bindBidirectional(State.customerName);
  }

  @Override
  public void render(Customer customer) {
    title.setValue(customer.getTitle());
    // name.setValue(customer.getName()); // No name setter - this is tied to global state
    dateOfBirth.setValue(customer.getDateOfBirth().toString());
    citizenship.setValue(customer.getCitizenship());
    visaType.setValue(customer.getVisa());
    occupation.setValue(customer.getOccupation());
    status.setValue(customer.getStatus());
  }

  @Override
  public void clear() {
    title.clearValue();
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
    customer.setTitle(title.getValue());
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
