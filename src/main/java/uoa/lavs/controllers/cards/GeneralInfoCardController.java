package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.time.LocalDate;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoCardController extends ICard<Customer> {

  private static final Logger logger = LoggerFactory.getLogger(GeneralInfoCardController.class);

  @FXML private MFXTextField title;
  @FXML private MFXTextField name;
  @FXML private MFXDatePicker dateOfBirth;
  @FXML private MFXTextField citizenship;
  @FXML private MFXTextField visaType;
  @FXML private MFXTextField occupation;
  @FXML private MFXTextField status;

  public GeneralInfoCardController() {
    ControllerUtils.loadFxml(this, "cards/general-info-card.fxml");
  }

  @FXML
  private void initialize() {
    name.textProperty().bindBidirectional(State.customerName);
  }

  @Override
  public void render(Customer customer) {
    ControllerUtils.renderText(title, customer.getTitle());
    ControllerUtils.renderText(name, customer.getName());
    ControllerUtils.renderDate(dateOfBirth, customer.getDateOfBirth());
    ControllerUtils.renderText(citizenship, customer.getCitizenship());
    ControllerUtils.renderText(visaType, customer.getVisa());
    ControllerUtils.renderText(occupation, customer.getOccupation());
    ControllerUtils.renderText(status, customer.getStatus());
  }

  @Override
  public void clear() {
    title.clear();
    name.clear();
    dateOfBirth.clear();
    citizenship.clear();
    visaType.clear();
    occupation.clear();
    status.clear();
  }

  @Override
  public Customer assemble() {
    Customer customer = new Customer();
    customer.setTitle(title.getText());
    customer.setName(name.getText());
    customer.setDateOfBirth(dateOfBirth.getValue());
    customer.setCitizenship(citizenship.getText());
    customer.setVisa(visaType.getText());
    customer.setOccupation(occupation.getText());
    customer.setStatus(status.getText());
    return customer;
  }
}
