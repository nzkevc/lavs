package uoa.lavs.mainframe.simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.FindCustomer;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;

class SimpleReplayConnectionTests {
  @Test
  public void handlesMissingDataFile() throws IOException {
    // Arrange
    String dataFile = "data/testing/simple-data.1.txt";
    Path path = Path.of(dataFile);
    if (Files.exists(path)) Files.delete(path);
    SimpleReplayConnection connection = new SimpleReplayConnection(dataFile);
    FindCustomer findCustomer = new FindCustomer();

    // Act
    Status status = findCustomer.send(connection);
    connection.close();

    // Assert
    assertEquals(1000, status.getErrorCode());
  }

  @Test
  public void handlesEndOfFile() throws IOException {
    // Arrange
    String dataFile = "data/testing/simple-data.2.txt";
    Files.writeString(Path.of(dataFile), "");
    SimpleReplayConnection connection = new SimpleReplayConnection(dataFile);
    FindCustomer findCustomer = new FindCustomer();

    // Act
    Status status = findCustomer.send(connection);
    connection.close();

    // Assert
    assertEquals(1020, status.getErrorCode());
  }

  @Test
  public void sendLoadsData() throws IOException {
    // Arrange
    String dataFile = "data/testing/simple-data.3.txt";
    String data =
        "code=\"1101\",error=\"0\",msg=,in-id=,out-title=\"\",out-name=\"John"
            + " Doe\",out-dob=\"\",out-occupation=\"\",out-citizenship=\"\",out-visa=\"\"";
        Files.writeString(Path.of(dataFile), data);
        SimpleReplayConnection connection = new SimpleReplayConnection(dataFile);
        LoadCustomer message = new LoadCustomer();

        // Act
        Status status = message.send(connection);
        connection.close();

        // Assert
        assertTrue(status.getWasSuccessful());
        assertEquals("John Doe", message.getNameFromServer());
    }
}