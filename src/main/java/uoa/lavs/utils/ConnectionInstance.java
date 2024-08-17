package uoa.lavs.utils;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.simulator.NitriteConnection;

public class ConnectionInstance {

  // Force tests to reset the connection each test so that tests are isolated
  private static Connection instance = null;

  public static Connection getConnection() {
    return instance;
  }

  public static void setConnection(Connection connection) {
    instance = connection;
  }

  /** Load in a fresh in-memory connection before each test. */
  public static void resetTestConnection() {
    instance = new NitriteConnection();
  }
}
