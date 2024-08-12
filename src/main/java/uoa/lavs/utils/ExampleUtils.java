package uoa.lavs.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;

/** Utility classes should contain only static, stateless methods and not call the mainframe. */
public class ExampleUtils {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ExampleUtils.class);

  public static String getGreeting() {
    String systemName;
    try {
      systemName = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      systemName = "world";
    }

    return String.format("Hello, %s!", systemName);
  }

  public static void logGreeting() {
    System.out.println(getGreeting());
  }
}
