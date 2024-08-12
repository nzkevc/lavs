package uoa.lavs.models;

import org.dizitart.no2.exceptions.ValidationException;

/** This is an example model */
public final class Example implements IModel {
  private String name;

  public Example(String name) {
    this.name = name;
    if (!validate()) {
      throw new ValidationException("Invalid model");
    }
  }

  @Override
  public boolean validate() {
    return validateName(name);
  }

  @Override
  public void persist() {
    // Persist the model
  }

  @Override
  public void delete() {
    // Delete the model
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (validateName(name)) {
      this.name = name;
    } else {
      throw new ValidationException("Invalid name");
    }
  }

  public boolean validateName(String name) {
    return name != null && name.length() > 0;
  }
}
