package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;
import uoa.lavs.utils.objects.ValidationException;

public class Phones implements IModel {
  private String customerId;
  private Set<Phone> phoneNumbers;
  private Phone primaryPhone;
  private Phone textPhone;

  public Phones(String customerId) {
    this.customerId = customerId;
    this.phoneNumbers = new HashSet<>();
  }

  public Phones(Set<Phone> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Set<Phone> getPhoneNumbers() {
    return phoneNumbers;
  }

  public Integer getPhoneCount() {
    return phoneNumbers.size();
  }

  public Phone getPrimaryPhone() {
    return primaryPhone;
  }

  void setPrimaryPhone(Phone phone) {
    if (phone != null) {
      if (primaryPhone != null) {
        primaryPhone.setPrimary(false);
      }
      primaryPhone = phone;
      phoneNumbers.add(phone);
    }
  }

  public Phone getTextPhone() {
    return textPhone;
  }

  void setTextPhone(Phone phone) {
    if (phone != null) {
      if (textPhone != null) {
        textPhone.setCanSendTxt(false);
      }
      textPhone = phone;
      phoneNumbers.add(phone);
    }
  }

  public void addPhone(Phone phone) {
    if (phone != null) {
      if (phone.getPrimary()) {
        setPrimaryPhone(phone);
      }

      if (phone.getCanSendTxt()) {
        setTextPhone(phone);
      }

      if (!phone.getPrimary() && !phone.getCanSendTxt()) {
        phoneNumbers.add(phone);
      }
    }
  }

  public static void validate(Phones phones) throws ValidationException {
    if (phones.getPhoneNumbers().isEmpty()) {
      return;
    }
    if (phones.getPhoneNumbers().stream().noneMatch(Phone::getPrimary)) {
      throw new ValidationException("Primary phone must be set.");
    }
    if (phones.getPhoneNumbers().stream().filter(Phone::getPrimary).count() > 1) {
      throw new ValidationException("Only one primary address is allowed.");
    }
  }
}
