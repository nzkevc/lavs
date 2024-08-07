package uoa.lavs.data_models;

import java.util.Date;

public class Customer {
  private String id;
  // status
  private String title;
  private String name;
  private Date dateOfBirth;
  private String occupation;
  private String citizenship;
  private String visa;
  private Addresses addresses;
  private Phones phones;
  private Emails emails;
  private Employer employer;
  private String notes;
  private Loans loans;

  public Customer() {}
}
