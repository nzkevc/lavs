package uoa.lavs.data_models;

public class Phone {
  private String type;
  private String prefix;
  private String number;

  public Phone(String type, String prefix, String number) {
    this.type = type;
    this.prefix = prefix;
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getNumber() {
    return number;
  }

  public String getFullNumber() {
    return prefix + number;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Phone) {
      Phone phone = (Phone) o;
      return type.equals(phone.getType())
          && prefix.equals(phone.getPrefix())
          && number.equals(phone.getNumber());
    }
    return false;
  }
}
