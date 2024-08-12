package uoa.lavs.models;

import java.util.Set;

/**
 * Models should have a full-model validate method that is called in the constructor, as well as
 * per-field validation methods that are called in the setters.
 *
 * <p>Models are in charge of their own persistence and deletion from the database. When a model is
 * persisted, the entire model is overwrittern in the database.
 */
public interface IModel<T extends IModel<T>> {
  boolean validate();

  T persist();

  void delete();

  T get(String id);

  Set<T> getAll();
}
