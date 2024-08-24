package uoa.lavs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uoa.lavs.State;

public class MessageUtils {

  private static final Logger logger = LoggerFactory.getLogger(State.class);

  public static void setMessageSuccess(String msg) {
    logger.debug(msg);
    State state = State.getInstance();
    state.summaryMessage.setValue(msg);
    state.summaryMessageIsError.setValue(false);
  }

  public static void setMessageError(String msg) {
    logger.warn(msg);
    State state = State.getInstance();
    state.summaryMessage.setValue(msg);
    state.summaryMessageIsError.setValue(true);
  }

  public static void clearMessage() {
    State state = State.getInstance();
    state.summaryMessage.setValue("");
  }
}
