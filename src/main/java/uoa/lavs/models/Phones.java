package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Phones {
  private List<Phone> phoneNumbers;
  private Phone primaryPhone;
  private Phone textPhone;

  public Phones(Phone primaryPhone) {
    this.phoneNumbers = new ArrayList<>();
    this.primaryPhone = primaryPhone;
    this.textPhone = primaryPhone;
    this.phoneNumbers.add(primaryPhone);
    this.phoneNumbers.add(textPhone);
  }

  public Phones(Phone primaryPhone, Phone textPhone) {
    this.phoneNumbers = new ArrayList<>();
    this.primaryPhone = primaryPhone;
    this.textPhone = textPhone;
    this.phoneNumbers.add(primaryPhone);
    this.phoneNumbers.add(textPhone);
  }

  public List<Phone> getPhoneNumbers() {
    return phoneNumbers;
  }

  public Phone getPrimaryPhone() {
    return primaryPhone;
  }

  public Phone getTextPhone() {
    return textPhone;
  }

  public void addPhone(Phone phone) {
    phoneNumbers.add(phone);
  }

  public void setPrimaryPhone(Phone phone) {
    primaryPhone = phone;
  }

  public void setTextPhone(Phone phone) {
    textPhone = phone;
  }
}
