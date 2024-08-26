package uoa.lavs.utils;

import uoa.lavs.utils.objects.ValidationException;

/** Utility classes should contain only static, stateless methods and not call the mainframe. */
public class ValidationUtils {

  public static void validateFieldExists(String value) throws ValidationException {
    if (isNullOrBlank(value)) {
      throw new ValidationException("Missing fields are required.");
    }
  }

  public static void validateFieldExists(String value, String message) throws ValidationException {
    if (isNullOrBlank(value)) {
      throw new ValidationException(message);
    }
  }

  public static boolean isNullOrBlank(Object obj) {
    return obj == null || String.valueOf(obj).isBlank();
  }
}
