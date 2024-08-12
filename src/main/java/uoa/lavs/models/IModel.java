package uoa.lavs.models;

/**
 * Models should have a full-model validate method that is called in the constructor, as well as
 * per-field validation methods that are called in the setters.
 *
 * <p>Models are in charge of their own persistence, retrival, and deletion from the database. When
 * a model is persisted, the entire model is overwrittern in the database.
 *
 * <p>Where relevant, the model should also have a static getAll method.
 */
public interface IModel<T extends IModel<T>> {
  boolean validate();

  T persist();

  void delete();

  T get(String id);
}
