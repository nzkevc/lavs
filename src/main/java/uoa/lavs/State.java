package uoa.lavs;

import java.io.IOException;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.CacheUtils;

/**
 * State is a singleton class that contains public properties to represent global state. It can also
 * contain methods to act on the state. Controllers will listen to these properties to update their
 * views (see ExampleController).
 */
public class State {

  private static final Logger logger = LoggerFactory.getLogger(State.class);
  private static final State instance = new State();

  public final Property<String> customerName = new SimpleStringProperty();

  // "" means no customer is selected - null breaks JavaFX bindings
  public final Property<String> customerId = new SimpleStringProperty();

  public final Property<String> summaryMessage = new SimpleStringProperty();
  public final Property<Boolean> summaryMessageIsError = new SimpleBooleanProperty();

  public final Property<Customer> customerFromSearch = new SimpleObjectProperty<>();

  private Supplier<Customer> assembleCustomerFunction;

  public static State getInstance() {
    return instance;
  }

  public State() {
    customerName.setValue("");
    customerId.setValue("");
    summaryMessage.setValue("");
    summaryMessageIsError.setValue(false);
    customerFromSearch.setValue(null);
    assembleCustomerFunction = () -> new Customer();
  }

  public void setAssembleCustomerFunction(Supplier<Customer> assembleCustomerFunction) {
    this.assembleCustomerFunction = assembleCustomerFunction;
  }

  public static void saveState() {
    logger.info("Saving state");
    instance.customerFromSearch.setValue(instance.assembleCustomerFunction.get());
    CacheUtils.saveToCache(instance.customerId.getValue(), instance.customerFromSearch.getValue());
  }

  public static void loadState() {
    logger.info("Loading state");
    try {
      Customer customer = CacheUtils.loadFromCache(instance.customerId.getValue());
      instance.customerFromSearch.setValue(customer);
      instance.customerId.setValue(customer.getId());
      instance.customerName.setValue(customer.getName());
    } catch (IOException e) {
      logger.error("Failed to load state from cache", e);
    }
  }
}
