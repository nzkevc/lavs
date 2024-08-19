package uoa.lavs.controllers.cards;

import uoa.lavs.controllers.IController;

public interface ICard<T> extends IController {
  void render(T data);

  void clear();

  T assemble();
}
