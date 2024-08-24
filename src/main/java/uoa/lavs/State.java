package uoa.lavs;

import java.io.IOException;
import java.io.Serializable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.CacheUtils;

/**
 * State is a class that contains public static properties to represent global state. It can also
 * contain methods to act on the state. Controllers will listen to these properties to update their
 * views (see ExampleController).
 */
public class State implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(State.class);
  private static final State instance = new State();

  public final Property<String> customerName = new SimpleStringProperty();

  // "" means no customer is selected - null breaks JavaFX bindings
  public final Property<String> customerId = new SimpleStringProperty();

  public final Property<String> summaryMessage = new SimpleStringProperty();
  public final Property<Boolean> summaryMessageIsError = new SimpleBooleanProperty();

  public final Property<Customer> customerFromSearch = new SimpleObjectProperty<>();

  public static State getInstance() {
    return instance;
  }

  public State() {
    reset();
  }

  public void reset() {
    customerName.setValue("");
    customerId.setValue("");
    summaryMessage.setValue("");
    summaryMessageIsError.setValue(false);
    customerFromSearch.setValue(null);
  }

  public void saveState() {
    logger.info("Saving state");
    CacheUtils.saveToCache("state", this);
  }

  public static void loadState() {
    logger.info("Loading state");
    try {
      State state = CacheUtils.loadFromCache("state");
      instance.customerName.setValue(state.customerName.getValue());
      instance.customerId.setValue(state.customerId.getValue());
      instance.summaryMessage.setValue(state.summaryMessage.getValue());
      instance.summaryMessageIsError.setValue(state.summaryMessageIsError.getValue());
      instance.customerFromSearch.setValue(state.customerFromSearch.getValue());
    } catch (IOException e) {
      logger.error("Failed to load state from cache");
      instance.reset();
    }
  }
}
