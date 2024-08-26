package uoa.lavs.controllers.cards;

import uoa.lavs.controllers.IController;

public abstract class ICard<T> extends IController {

  public abstract void render(T data); // Service --T-> Screen

  public abstract void clear(); // Clear the screen

  public abstract void validate(); // Validate the fields

  public abstract T assemble(); // Screen --T-> Service
}
