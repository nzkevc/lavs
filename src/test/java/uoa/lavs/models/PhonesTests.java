package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import uoa.lavs.utils.objects.ValidationException;

public class PhonesTests {
  @Test
  public void constructorTest() {
    // Arrange
    Phones phones = new Phones("123");
    phones.setCustomerId("124");
    Phone primaryPhone = new Phone(phones.getCustomerId(), 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    // Act
    phones.addPhone(primaryPhone);
    phones.addPhone(primaryPhone);

    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(primaryPhone, phones.getTextPhone());
  }

  @Test
  public void constructor2Test() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act

    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
  }

  @Test
  public void constructor3Test() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(false);

    Phone textPhone = new Phone("123", 2);
    textPhone.setType("Mobile");
    textPhone.setPrefix("09");
    textPhone.setPhoneNumber("987654321");
    textPhone.setPrimary(false);
    textPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);
    phones.addPhone(textPhone);

    // Act

    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(textPhone, phones.getTextPhone());
  }

  @Test
  public void setConstructorTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(false);

    Phone textPhone = new Phone("123", 2);
    textPhone.setType("Mobile");
    textPhone.setPrefix("09");
    textPhone.setPhoneNumber("987654321");
    textPhone.setPrimary(false);
    textPhone.setCanSendTxt(true);

    Set<Phone> phoneSet = new HashSet<>();
    phoneSet.add(primaryPhone);
    phoneSet.add(textPhone);

    Phones phones = new Phones(phoneSet);

    // Act
    // Assert
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(textPhone, phones.getTextPhone());
  }

  @Test
  public void changePrimaryPhoneTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    Phone newPrimaryPhone = new Phone("123", 2);
    newPrimaryPhone.setType("Mobile");
    newPrimaryPhone.setPrefix("09");
    newPrimaryPhone.setPhoneNumber("987654321");
    newPrimaryPhone.setPrimary(true);
    newPrimaryPhone.setCanSendTxt(true);

    phones.addPhone(newPrimaryPhone);

    // Assert
    assertEquals(newPrimaryPhone, phones.getPrimaryPhone());
    assertFalse(primaryPhone.getPrimary());
  }

  @Test
  public void addPhoneTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    int firstPhoneCount = phones.getPhoneCount();
    Phone newPhone = new Phone("123", 2);
    newPhone.setType("Mobile");
    newPhone.setPrefix("09");
    newPhone.setPhoneNumber("987654321");
    newPhone.setPrimary(false);
    newPhone.setCanSendTxt(true);

    phones.addPhone(newPhone);

    // Assert
    assertEquals(firstPhoneCount + 1, phones.getPhoneCount());
  }

  @Test
  public void addAllPhonesTest() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phone textPhone = new Phone("123", 2);
    textPhone.setType("Mobile");
    textPhone.setPrefix("09");
    textPhone.setPhoneNumber("987654321");
    textPhone.setPrimary(false);
    textPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);
    phones.addPhone(textPhone);

    // Act
    int firstPhoneCount = phones.getPhoneCount();
    Phone newPhone = new Phone("123", 3);
    newPhone.setType("Mobile");
    newPhone.setPrefix("09");
    newPhone.setPhoneNumber("123987654");
    newPhone.setPrimary(false);
    newPhone.setCanSendTxt(false);

    phones.addPhone(newPhone);
    phones.addPhone(null);

    // Assert
    assertEquals(firstPhoneCount + 1, phones.getPhoneCount());
  }

  @Test
  public void changeTextPhone() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phone textPhone = new Phone("123", 2);
    textPhone.setType("Mobile");
    textPhone.setPrefix("09");
    textPhone.setPhoneNumber("987654321");
    textPhone.setPrimary(false);
    textPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    phones.addPhone(textPhone);

    // Assert
    assertEquals(textPhone, phones.getTextPhone());
    assertFalse(primaryPhone.getCanSendTxt());
  }

  @Test
  public void setTextPhoneWhenNull() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    phones.addPhone(null);

    // Assert
    assertNotNull(phones.getTextPhone());
  }

  @Test
  public void setPrimaryPhoneWhenNull() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    phones.addPhone(null);

    // Assert
    assertNotNull(phones.getPrimaryPhone());
  }

  @Test
  public void getPhoneNumbers() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phone textPhone = new Phone("123", 2);
    textPhone.setType("Mobile");
    textPhone.setPrefix("09");
    textPhone.setPhoneNumber("987654321");
    textPhone.setPrimary(false);
    textPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);
    phones.addPhone(textPhone);

    // Act

    // Assert
    assertEquals(2, phones.getPhoneNumbers().size());
    assertEquals(primaryPhone, phones.getPrimaryPhone());
    assertEquals(textPhone, phones.getTextPhone());
  }

  @Test
  public void validatePrimaryPhone() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    // Assert
    assertDoesNotThrow(() -> Phones.validate(phones));
  }

  @Test
  public void validateTwoPrimaryPhones() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phone primaryPhone2 = new Phone("123", 2);
    primaryPhone2.setType("Mobile");
    primaryPhone2.setPrefix("09");
    primaryPhone2.setPhoneNumber("987654321");
    primaryPhone2.setPrimary(true);
    primaryPhone2.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);
    phones.addPhone(primaryPhone2);

    // Act
    // Assert
    assertDoesNotThrow(() -> Phones.validate(phones));
  }

  @Test
  public void validateTwoPrimaryPhonesWithSet() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(true);
    primaryPhone.setCanSendTxt(true);

    Phone primaryPhone2 = new Phone("123", 2);
    primaryPhone2.setType("Mobile");
    primaryPhone2.setPrefix("09");
    primaryPhone2.setPhoneNumber("987654321");
    primaryPhone2.setPrimary(true);
    primaryPhone2.setCanSendTxt(true);

    Set<Phone> phoneSet = new HashSet<>();
    phoneSet.add(primaryPhone);
    phoneSet.add(primaryPhone2);

    Phones phones = new Phones(phoneSet);

    // Act
    // Assert
    assertDoesNotThrow(() -> Phones.validate(phones));
  }

  @Test
  public void validateNoPrimaryPhones() {
    // Arrange
    Phone primaryPhone = new Phone("123", 1);
    primaryPhone.setType("Mobile");
    primaryPhone.setPrefix("09");
    primaryPhone.setPhoneNumber("123456789");
    primaryPhone.setPrimary(false);
    primaryPhone.setCanSendTxt(true);

    Phones phones = new Phones("123");
    phones.addPhone(primaryPhone);

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Phones.validate(phones));
  }

  @Test
  public void validateEmptyPhones() {
    // Arrange
    Phones phones = new Phones("123");

    // Act
    // Assert
    assertDoesNotThrow(() -> Phones.validate(phones));
  }
}
