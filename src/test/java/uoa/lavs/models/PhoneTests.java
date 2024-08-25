package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PhoneTests {
  @Test
  public void constructorTest() {
    // Arrange
    Phone phone = new Phone("123", 1);
    phone.setType("Mobile");
    phone.setPrefix("09");
    phone.setPhoneNumber("123456789");
    phone.setPrimary(true);
    phone.setCanSendTxt(true);

    // Act
    boolean isCorrect =
        phone.getCustomerId().equals("123")
            && phone.getNumber() == 1
            && phone.getType().equals("Mobile")
            && phone.getPrefix().equals("09")
            && phone.getPhoneNumber().equals("123456789")
            && phone.getPrimary()
            && phone.getCanSendTxt();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void constructor2Test() {
    // Arrange
    Phone phone = new Phone("123", 1);
    phone.setType("Mobile");
    phone.setPrefix("09");
    phone.setPhoneNumber("123456789");
    phone.setPrimary(true);
    phone.setCanSendTxt(true);

    // Act
    boolean isCorrect =
        phone.getCustomerId().equals("123")
            && phone.getNumber() == 1
            && phone.getType().equals("Mobile")
            && phone.getPrefix().equals("09")
            && phone.getPhoneNumber().equals("123456789")
            && phone.getPrimary()
            && phone.getCanSendTxt();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void emptyConstructorTest() {
    // Arrange
    Phone phone = new Phone();
    phone.setCustomerID("123");
    phone.setNumber(1);
    phone.setType("Mobile");
    phone.setPrefix("09");
    phone.setPhoneNumber("123456789");
    phone.setPrimary(true);
    phone.setCanSendTxt(false);

    // Act
    boolean isCorrect =
        phone.getCustomerId().equals("123")
            && phone.getNumber() == 1
            && phone.getType().equals("Mobile")
            && phone.getPrefix().equals("09")
            && phone.getPhoneNumber().equals("123456789")
            && phone.getPrimary()
            && !phone.getCanSendTxt();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void getFullNumberTest() {
    // Arrange
    Phone phone = new Phone("123", 1);
    phone.setType("Mobile");
    phone.setPrefix("09");
    phone.setPhoneNumber("123456789");
    phone.setPrimary(true);
    phone.setCanSendTxt(true);

    // Act
    String fullNumber = phone.getFullNumber();

    // Assert
    assertEquals("09123456789", fullNumber);
  }

  @Test
  public void getFullNumberNoPrefix() {
    // Arrange
    Phone phone = new Phone("123", 1);
    phone.setType("Mobile");
    phone.setPrefix("");
    phone.setPhoneNumber("123456789");
    phone.setPrimary(true);
    phone.setCanSendTxt(true);

    // Act
    String fullNumber = phone.getFullNumber();

    // Assert
    assertEquals("123456789", fullNumber);
  }

  @Test
  public void equalsTest() {
    // Arrange
    Phone phone1 = new Phone("123", 1);
    phone1.setType("Mobile");
    phone1.setPrefix("09");
    phone1.setPhoneNumber("123456789");
    phone1.setPrimary(true);
    phone1.setCanSendTxt(true);

    Phone phone2 = new Phone("123", 1);
    phone2.setType("Mobile");
    phone2.setPrefix("09");
    phone2.setPhoneNumber("123456789");
    phone2.setPrimary(true);
    phone2.setCanSendTxt(true);

    // Act

    // Assert
    assertTrue(phone1.equals(phone2));
    assertTrue(phone2.equals(phone1));
  }

  @Test
  public void notEqualObjectsTest() {
    // Arrange
    Phone phone1 = new Phone("123", 1);
    phone1.setType("Mobile");
    phone1.setPrefix("09");
    phone1.setPhoneNumber("123456789");
    phone1.setPrimary(true);
    phone1.setCanSendTxt(true);

    String phone2 = "123456789";

    // Act

    // Assert
    assertFalse(phone1.equals(phone2));
  }

  @Test
  public void noteEqualNullTest() {
    // Arrange
    Phone phone1 = new Phone("123", 1);
    phone1.setType("Mobile");
    phone1.setPrefix("09");
    phone1.setPhoneNumber("123456789");
    phone1.setPrimary(true);
    phone1.setCanSendTxt(true);

    Phone phone2 = null;

    // Act

    // Assert
    assertFalse(phone1.equals(phone2));
  }

  @Test
  public void notEqualTypeTest() {
    // Arrange
    Phone phone1 = new Phone("123", 1);
    phone1.setType("Mobile");
    phone1.setPrefix("09");
    phone1.setPhoneNumber("123456789");
    phone1.setPrimary(true);
    phone1.setCanSendTxt(true);

    Phone phone2 = new Phone("123", 1);
    phone2.setType("Home");
    phone2.setPrefix("09");
    phone2.setPhoneNumber("123456789");
    phone2.setPrimary(true);
    phone2.setCanSendTxt(true);

    // Act

    // Assert
    assertFalse(phone1.equals(phone2));
    assertFalse(phone2.equals(phone1));
  }

  @Test
  public void notEqualPrefixTest() {
    // Arrange
    Phone phone1 = new Phone("123", 1);
    phone1.setType("Mobile");
    phone1.setPrefix("09");
    phone1.setPhoneNumber("123456789");
    phone1.setPrimary(true);
    phone1.setCanSendTxt(true);

    Phone phone2 = new Phone("123", 1);
    phone2.setType("Mobile");
    phone2.setPrefix("02");
    phone2.setPhoneNumber("123456789");
    phone2.setPrimary(true);
    phone2.setCanSendTxt(true);

    // Act

    // Assert
    assertFalse(phone1.equals(phone2));
    assertFalse(phone2.equals(phone1));
  }

  @Test
  public void notEqualNumberTest() {
    // Arrange
    Phone phone1 = new Phone("123", 1);
    phone1.setType("Mobile");
    phone1.setPrefix("09");
    phone1.setPhoneNumber("123456789");
    phone1.setPrimary(true);
    phone1.setCanSendTxt(true);

    Phone phone2 = new Phone("123", 1);
    phone2.setType("Mobile");
    phone2.setPrefix("09");
    phone2.setPhoneNumber("987654321");
    phone2.setPrimary(true);
    phone2.setCanSendTxt(true);

    // Act

    // Assert
    assertFalse(phone1.equals(phone2));
    assertFalse(phone2.equals(phone1));
  }

  @Test
  public void validateTest() {
    // Arrange
    Phone phone = new Phone("123", 1);

    // Act

    // Assert
    // assertTrue(phone.validate());
  }
}
