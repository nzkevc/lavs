package uoa.lavs.services;

import java.util.concurrent.FutureTask;
import uoa.lavs.State;
import uoa.lavs.models.ExampleUser;

public class ExampleService implements IService {

  // A FutureTask is kinda like a Promise in JS
  public static FutureTask<ExampleUser> createUserAsync(String name) {
    ExampleUser user = new ExampleUser(name);
    State.exampleTitle.setValue("User created: " + name); // Just to demo properties and state
    return new FutureTask<>(() -> user.persist());
  }

  // You'd never have both a sync and async method for the same operation but for demo purposes
  public static ExampleUser createUserSync(String name) {
    ExampleUser user = new ExampleUser(name);
    State.exampleTitle.setValue("User created: " + name); // Just to demo properties and state
    return user.persist();
  }
}
