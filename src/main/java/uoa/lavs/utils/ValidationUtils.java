package uoa.lavs.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.objects.ValidationException;

/** Utility classes should contain only static, stateless methods and not call the mainframe. */
public class ValidationUtils {

  private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

  public static LocalDate getDateFromField(FieldController field) {
    try {
      return LocalDate.parse(field.getValue());
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid date of birth - use YYYY-MM-DD format", e);
    }
  }

  public static void validateFieldExists(String value) throws ValidationException {
    if (value == null || value.isEmpty()) {
      throw new ValidationException("Field is required.");
    }
  }

  public static void validateFieldExists(String value, String message) throws ValidationException {
    if (value == null || value.isEmpty()) {
      throw new ValidationException(message);
    }
  }
}
