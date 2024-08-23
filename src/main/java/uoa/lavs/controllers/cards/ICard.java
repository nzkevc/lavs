package uoa.lavs.controllers.cards;

import uoa.lavs.controllers.IController;

public abstract class ICard<T> extends IController {

  abstract public void render(T data); // Service --T-> Screen

  abstract public void clear(); // Clear the screen

  abstract public T assemble(); // Screen --T-> Service
}
