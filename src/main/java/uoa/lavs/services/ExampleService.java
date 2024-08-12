package uoa.lavs.services;

import java.util.concurrent.FutureTask;
import uoa.lavs.models.ExampleUser;

public class ExampleService implements IService {

  public static FutureTask<ExampleUser> createUserAsync(String name) {
    ExampleUser user = new ExampleUser(name);
    return new FutureTask<>(() -> user.persist());
  }

  // You'd never have both a sync and async method for the same operation but for demo purposes
  public static ExampleUser createUserSync(String name) {
    ExampleUser user = new ExampleUser(name);
    return user.persist();
  }
}
