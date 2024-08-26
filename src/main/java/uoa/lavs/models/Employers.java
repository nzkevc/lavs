package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;

public class Employers implements IModel {
  private String customerId;
  private Set<Employer> employers;

  public Employers(String customerId) {
    this.customerId = customerId;
    this.employers = new HashSet<>();
  }

  public Employers(Set<Employer> employers) {
    this.employers = new HashSet<>();
    employers.forEach(this::addEmployer);
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

  public Set<Employer> getEmployers() {
    return employers;
  }

  public Integer getEmployerCount() {
    return employers.size();
  }
}
