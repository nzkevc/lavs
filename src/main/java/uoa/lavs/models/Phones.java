package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Phones {
  private String customerId;
  private List<Phone> phoneNumbers;
  private Phone primaryPhone;
  private Phone textPhone;

  public Phones(String customerId) {
    this.customerId = customerId;
    this.phoneNumbers = new ArrayList<>();
  }

  public Phones(String customerId, Phone primaryPhone) {
    this.customerId = customerId;
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

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public List<Phone> getPhoneNumbers() {
    return phoneNumbers;
  }

  public Integer getPhoneCount() {
    return phoneNumbers.size();
  }

  public Phone getPrimaryPhone() {
    return primaryPhone;
  }

  public void setPrimaryPhone(Phone phone) {
    if (primaryPhone != null) {
      primaryPhone.setPrimary(false);
    }
    primaryPhone = phone;
    phoneNumbers.add(phone);
  }

  public Phone getTextPhone() {
    return textPhone;
  }

  public void setTextPhone(Phone phone) {
    if (textPhone != null) {
      textPhone.setCanSendTxt(false);
    }
    textPhone = phone;
    phoneNumbers.add(phone);
  }

  public void addPhone(Phone phone) {
    phoneNumbers.add(phone);
  }
}
