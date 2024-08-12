package uoa.lavs.services;

import java.util.ArrayList;
import java.util.List;
import uoa.lavs.models.User;

public class UserService {

  private static final List<User> database = new ArrayList<>();

  public static List<User> createUser(String name /* (controller -> service) */) {

    // [ Insert complicated business logic here ]
    // (That's why we offload it to the service layer)

    User user = new User(name); // (service <-> model)
    database.add(user);

    // Okay it's a bit weird to return the database, but imagine another function
    // where we need to return the database e.g. see all users
    return database; // (service -> controller)
  }
}
