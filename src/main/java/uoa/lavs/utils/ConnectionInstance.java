package uoa.lavs.utils;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.simulator.NitriteConnection;

public class ConnectionInstance {
  private ConnectionInstance() {}
  ;

  private static String dataPath = "nitrite.db";
  private static Connection INSTANCE = new NitriteConnection(dataPath);

  public static Connection getConnection() {
    return INSTANCE;
  }

  public static void setConnection(String path) {
    INSTANCE = new NitriteConnection(path);
  }

  public static void setConnection() {
    INSTANCE = new NitriteConnection();
  }
}
