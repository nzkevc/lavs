package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import uoa.lavs.utils.objects.ValidationException;

public class EmailsTests {
  @Test
  public void constructorTest() {
    // Arrange
    Emails emails = new Emails("123");
    emails.setCustomerId("124");
    Email primaryEmail = new Email(emails.getCustomerId(), 1);
    primaryEmail.setAddress("123@123.com");
    primaryEmail.setIsPrimary(true);

    // Act
    emails.addEmail(primaryEmail);

    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void constructor2Test() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setAddress("123@123.com");
    primaryEmail.setIsPrimary(true);
    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act

    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void constructorSetTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setAddress("123@123.com");
    primaryEmail.setIsPrimary(true);

    Set<Email> emailsSet = new HashSet<>();
    emailsSet.add(primaryEmail);

    Emails emails = new Emails(emailsSet);

    // Act
    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void changePrimaryEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");
    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act
    Email newPrimaryEmail = new Email("123", 2);
    newPrimaryEmail.setIsPrimary(true);
    newPrimaryEmail.setAddress("124@124.com");
    emails.addEmail(newPrimaryEmail);

    // Assert
    assertEquals(newPrimaryEmail, emails.getPrimaryEmail());
    assertFalse(primaryEmail.getIsPrimary());
  }

  @Test
  public void addEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");
    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act
    int firstEmailCount = emails.getEmailCount();
    Email newEmail = new Email("123", 2);
    newEmail.setAddress("124@124.com");
    emails.addEmail(newEmail);

    // Assert
    assertEquals(firstEmailCount + 1, emails.getEmailCount());
  }

  @Test
  public void addingNullEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");
    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act
    emails.addEmail(null);

    // Assert
    assertEquals(1, emails.getEmailCount());
  }

  @Test
  public void addingNullPrimaryEmailTest() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");
    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act
    emails.addEmail(null);

    // Assert
    assertEquals(primaryEmail, emails.getPrimaryEmail());
  }

  @Test
  public void gettingMultipleEmails() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");
    Email secondaryEmail = new Email("123", 2);
    secondaryEmail.setIsPrimary(false);
    secondaryEmail.setAddress("124@124.com");
    Email tertiaryEmail = new Email("123", 3);
    tertiaryEmail.setIsPrimary(false);
    tertiaryEmail.setAddress("123456@23wergi");
    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act
    int firstEmailCount = emails.getEmailCount();
    emails.addEmail(secondaryEmail);
    emails.addEmail(tertiaryEmail);

    // Assert
    assertEquals(firstEmailCount + 2, emails.getEmailCount());
  }

  @Test
  public void validatePrimaryEmail() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("something@example.com");

    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);
  }

  @Test
  public void validateTwoPrimaryEmails() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");

    Email primaryEmail2 = new Email("123", 2);
    primaryEmail2.setIsPrimary(true);
    primaryEmail2.setAddress("1232@123.com");

    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);
    emails.addEmail(primaryEmail2);

    // Act
    // Assert
    assertDoesNotThrow(() -> Emails.validate(emails));
  }

  @Test
  public void validateTwoPrimaryEmailsWithSetConstructor() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(true);
    primaryEmail.setAddress("123@123.com");

    Email primaryEmail2 = new Email("123", 2);
    primaryEmail2.setIsPrimary(true);
    primaryEmail2.setAddress("1232@123.com");

    Set<Email> emailSet = new HashSet<>();

    emailSet.add(primaryEmail);
    emailSet.add(primaryEmail2);

    Emails emails = new Emails(emailSet);

    // Act
    // Assert
    assertDoesNotThrow(() -> Emails.validate(emails));
  }

  @Test
  public void validateNoPrimaryEmail() {
    // Arrange
    Email primaryEmail = new Email("123", 1);
    primaryEmail.setIsPrimary(false);
    primaryEmail.setAddress("123@123.com");

    Emails emails = new Emails("123");
    emails.addEmail(primaryEmail);

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Emails.validate(emails));
  }

  @Test
  public void validateEmptyEmails() {
    // Arrange
    Emails emails = new Emails("123");

    // Act
    // Assert
    assertDoesNotThrow(() -> Emails.validate(emails));
  }
}
