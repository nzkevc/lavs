package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Employers {
  private String customerId;
  private List<Employer> employers;

  public Employers(String customerId) {
    this.customerId = customerId;
    this.employers = new ArrayList<>();
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addEmployer(Employer newEmployer) {
    if (newEmployer != null) {
      employers.add(newEmployer);
    }
  }

  public List<Employer> getEmployers() {
    return employers;
  }

  public Integer getEmployerCount() {
    return employers.size();
  }
}
