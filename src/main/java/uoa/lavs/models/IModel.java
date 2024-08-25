package uoa.lavs.models;

import java.io.Serializable;

/**
 * Models should have a full-model validate method that is called in the constructor.
 *
 * <p>If the model is invalid, the constructor should throw a custom exception that is caught at the
 * controller level, with fields indicating what subfields are invalid.
 */
public interface IModel<T extends IModel<T>> extends Serializable {
  boolean validate();
}
