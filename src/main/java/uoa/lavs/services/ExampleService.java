package uoa.lavs.services;

import javafx.application.Platform;
import uoa.lavs.State;
import uoa.lavs.models.ExampleUser;

public class ExampleService implements IService {

  // You'd never have both a sync and async method for the same operation but for demo purposes
  public static ExampleUser createUser(String name) {
    ExampleUser user = new ExampleUser(name);

    // Just to demo properties and state
    Platform.runLater(() -> State.exampleTitle.setValue("User created: " + name));

    return user.persist();
  }
}
