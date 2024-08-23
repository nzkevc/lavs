package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PhonesTests {
  @Test
  public void constructorTest() {
    // Arrange
    Phones phones = new Phones("123");
    phones.setCustomerId("124");
    Phone primaryPhone =
        new Phone(phones.getCustomerId(), 1, "Mobile", "09", "123456789", true, true);

    // Act
    phones.setPrimaryPhone(primaryPhone);
    phones.setTextPhone(primaryPhone);

    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(primaryPhone, phones.getTextPhone());
  }

  @Test
  public void constructor2Test() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phones phones = new Phones("123", primaryPhone);

    // Act

    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
  }

  @Test
  public void constructor3Test() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, false);
    Phone textPhone = new Phone("123", 2, "Mobile", "09", "123456789", false, true);
    Phones phones = new Phones("123", primaryPhone, textPhone);

    // Act

    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(textPhone, phones.getTextPhone());
  }

  @Test
  public void changePrimaryPhoneTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phones phones = new Phones("123", primaryPhone);

    // Act
    Phone newPrimaryPhone = new Phone("123", 2, "Mobile", "09", "987654321", true, true);
    phones.setPrimaryPhone(newPrimaryPhone);

    // Assert
    assertEquals(newPrimaryPhone, phones.getPrimaryPhone());
    assertFalse(primaryPhone.getPrimary());
  }

  @Test
  public void addPhoneTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phones phones = new Phones("123", primaryPhone);

    // Act
    int firstPhoneCount = phones.getPhoneCount();
    Phone newPhone = new Phone("123", 2, "Mobile", "09", "987654321", false, true);
    phones.setPrimaryPhone(newPhone);

    // Assert
    assertEquals(firstPhoneCount + 1, phones.getPhoneCount());
  }

  @Test
  public void addAllPhonesTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phone textPhone = new Phone("123", 2, "Mobile", "09", "987654321", false, true);
    Phones phones = new Phones("123", primaryPhone, textPhone);

    // Act
    int firstPhoneCount = phones.getPhoneCount();
    Phone newPhone = new Phone("123", 3, "Mobile", "09", "987654321", false, false);
    phones.addPhone(newPhone);
    phones.addPhone(null);

    // Assert
    assertEquals(firstPhoneCount + 1, phones.getPhoneCount());
  }

  @Test
  public void changeTextPhone() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phone textPhone = new Phone("123", 2, "Mobile", "09", "987654321", false, true);
    Phones phones = new Phones("123", primaryPhone);

    // Act
    phones.setTextPhone(textPhone);

    // Assert
    assertEquals(textPhone, phones.getTextPhone());
    assertFalse(primaryPhone.getCanSendTxt());
  }

  @Test
  public void setTextPhoneWhenNull() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phones phones = new Phones("123", primaryPhone);

    // Act
    phones.setTextPhone(null);

    // Assert
    assertNotNull(phones.getTextPhone());
  }

  @Test
  public void setPrimaryPhoneWhenNull() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phones phones = new Phones("123", primaryPhone);

    // Act
    phones.setPrimaryPhone(null);

    // Assert
    assertNotNull(phones.getPrimaryPhone());
  }

  @Test
  public void getPhoneNumbers() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1, "Mobile", "09", "123456789", true, true);
    Phone textPhone = new Phone("123", 2, "Mobile", "09", "987654321", false, true);
    Phones phones = new Phones("123", primaryPhone, textPhone);

    // Act

    // Assert
    assertEquals(2, phones.getPhoneNumbers().size());
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(textPhone, phones.getTextPhone());
  }
}
