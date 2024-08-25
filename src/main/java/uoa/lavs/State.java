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

  public static final Property<String> customerName = new SimpleStringProperty();

  // "" means no customer is selected - null breaks JavaFX bindings
  public static final Property<String> customerId = new SimpleStringProperty();

  public static final Property<String> summaryMessage = new SimpleStringProperty();
  public static final Property<Boolean> summaryMessageIsError = new SimpleBooleanProperty();

  public static final Property<Customer> customerFromSearch = new SimpleObjectProperty<>();

  private static Supplier<Customer> assembleCustomerFunction;

  public static void reset() {
    customerName.setValue("");
    customerId.setValue("");
    summaryMessage.setValue("");
    summaryMessageIsError.setValue(false);
    customerFromSearch.setValue(null);
    assembleCustomerFunction = () -> new Customer();
  }

  public static void setAssembleCustomerFunction(Supplier<Customer> assembleCustomerFunction) {
    State.assembleCustomerFunction = assembleCustomerFunction;
  }

  public static void saveState() {
    logger.info("Saving state");
    customerFromSearch.setValue(assembleCustomerFunction.get());
    CacheUtils.saveToCache(customerId.getValue(), customerFromSearch.getValue());
  }

  public static void loadState() {
    logger.info("Loading state");
    try {
      Customer customer = CacheUtils.loadFromCache(customerId.getValue());
      customerFromSearch.setValue(customer);
      customerId.setValue(customer.getId());
      customerName.setValue(customer.getName());
    } catch (IOException e) {
      logger.error("Failed to load state from cache", e);
    }
  }

  public static void setMessageSuccess(String msg) {
    logger.debug(msg);
    summaryMessage.setValue(msg);
    summaryMessageIsError.setValue(false);
  }

  public static void setMessageError(String msg) {
    logger.warn(msg);
    summaryMessage.setValue(msg);
    summaryMessageIsError.setValue(true);
  }

  public static void clearMessage() {
    summaryMessage.setValue("");
  }
}
