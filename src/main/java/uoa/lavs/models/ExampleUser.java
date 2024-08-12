package uoa.lavs.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.dizitart.no2.exceptions.ValidationException;

/** This is an example model */
public final class ExampleUser implements IModel<ExampleUser> {

  // This usually isn't here, instead persist would call tha mainframe
  private static final Map<String, ExampleUser> exampleUserDatabase = new HashMap<>();

  private String name;

  public ExampleUser(String name) {
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
  public ExampleUser persist() {
    exampleUserDatabase.put(name, this);
    return this;
  }

  @Override
  public void delete() {
    exampleUserDatabase.remove(name);
  }

  @Override
  public ExampleUser get(String id) {
    return exampleUserDatabase.get(id);
  }

  @Override
  public Set<ExampleUser> getAll() {
    return Set.copyOf(exampleUserDatabase.values());
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

  @Override
  public String toString() {
    return "{ name: " + name + " }";
  }
}
