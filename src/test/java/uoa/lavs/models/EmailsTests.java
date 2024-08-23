package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmailsTests {
  @Test
  public void constructorTest() {
    // Arrange
    Emails emails = new Emails("123");
    emails.setCustomerId("124");
    Email primaryEmail = new Email(emails.getCustomerId(), 1, "123@123.com", true);

    // Act
    emails.setPrimaryEmail(primaryEmail);

    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void constructor2Test() {
    // Arrange
    Email primaryEmail = new Email("123", 1, "123@123.com", true);
    Emails emails = new Emails("123", primaryEmail);

    // Act

    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void changePrimaryEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1, "123@123.com", true);
    Emails emails = new Emails("123", primaryEmail);

    // Act
    Email newPrimaryEmail = new Email("123", 2, "124@124.com", true);
    emails.setPrimaryEmail(newPrimaryEmail);

    // Assert
    assertEquals(newPrimaryEmail, emails.getPrimaryEmail());
    assertFalse(primaryEmail.getIsPrimary());
  }

  @Test
  public void addEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1, "123@123.com", true);
    Emails emails = new Emails("123", primaryEmail);

    // Act
    int firstEmailCount = emails.getEmailCount();
    Email newEmail = new Email("123", 2, "124@124.com", false);
    emails.addEmail(newEmail);

    // Assert
    assertEquals(firstEmailCount + 1, emails.getEmailCount());
  }
}
