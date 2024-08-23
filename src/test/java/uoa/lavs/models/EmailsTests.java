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

  @Test
  public void addingNullEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1, "123@123.com", true);
    Emails emails = new Emails("123", primaryEmail);

    // Act
    emails.addEmail(null);

    // Assert
    assertEquals(1, emails.getEmailCount());
  }

  @Test
  public void addingNullPrimaryEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1, "123@123.com", true);
    Emails emails = new Emails("123", primaryEmail);

    // Act
    emails.setPrimaryEmail(null);

    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void gettingMultipleEmails() {
    // Arrange
    Email primaryEmail = new Email("123", 1, "123@123.com", true);
    Email secondaryEmail = new Email("123", 2, "124@14.com", false);
    Email tertiaryEmail = new Email("123", 3, "125@15.com", false);
    Emails emails = new Emails("123", primaryEmail);

    // Act
    int firstEmailCount = emails.getEmailCount();
    emails.addEmail(secondaryEmail);
    emails.addEmail(tertiaryEmail);

    // Assert
    assertEquals(firstEmailCount + 2, emails.getEmailCount());
    assertEquals(tertiaryEmail, emails.getEmails().get(2));
  }
}
