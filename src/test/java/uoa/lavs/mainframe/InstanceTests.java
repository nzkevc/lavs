package uoa.lavs.mainframe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InstanceTests {
  @Test
  public void getInstanceReturnsConnection() {
    // Act
    Connection conn = Instance.getConnection();

    // Assert
    assertNotNull(conn);
  }
}
