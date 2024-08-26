package uoa.lavs.utils.objects;

/** Thrown by models when attempting to set invalid fields or create a model with invalid fields */
public class ValidationException extends IllegalArgumentException {
  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
