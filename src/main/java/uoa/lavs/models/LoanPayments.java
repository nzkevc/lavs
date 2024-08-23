package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class LoanPayments {
  private String loanId;
  private Integer number;
  private String customerId;
  private String customerName;
  private Integer pages;
  private Integer payments;
  private List<Double> paymentInterests;
  private List<Double> paymentPrincipals;
  private List<Double> paymentRemainings;
  private List<Integer> paymentNumbers;

  public LoanPayments(String loanId, Integer number) {
    this.loanId = loanId;
    this.number = number;
    paymentInterests = new ArrayList<>();
    paymentPrincipals = new ArrayList<>();
    paymentRemainings = new ArrayList<>();
    paymentNumbers = new ArrayList<>();
  }

  public String getLoanId() {
    return loanId;
  }

  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public Integer getPayments() {
    return payments;
  }

  public void setPayments(Integer payments) {
    this.payments = payments;
  }

  public List<Double> getPaymentInterests() {
    return paymentInterests;
  }

  public void setPaymentInterests(List<Double> paymentInterests) {
    this.paymentInterests = paymentInterests;
  }

  public List<Double> getPaymentPrincipals() {
    return paymentPrincipals;
  }

  public void setPaymentPrincipals(List<Double> paymentPrincipals) {
    this.paymentPrincipals = paymentPrincipals;
  }

  public List<Double> getPaymentRemainings() {
    return paymentRemainings;
  }

  public void setPaymentRemainings(List<Double> paymentRemainings) {
    this.paymentRemainings = paymentRemainings;
  }

  public List<Integer> getPaymentNumbers() {
    return paymentNumbers;
  }

  public void setPaymentNumbers(List<Integer> paymentNumbers) {
    this.paymentNumbers = paymentNumbers;
  }

  public void addPaymentInterest(Double paymentInterest) {
    this.paymentInterests.add(paymentInterest);
    this.payments = paymentInterests.size();
  }

  public void addPaymentPrincipal(Double paymentPrincipal) {
    this.paymentPrincipals.add(paymentPrincipal);
    this.payments = paymentPrincipals.size();
  }

  public void addPaymentRemaining(Double paymentRemaining) {
    this.paymentRemainings.add(paymentRemaining);
    this.payments = paymentRemainings.size();
  }

  public void addPaymentNumber(Integer paymentNumber) {
    this.paymentNumbers.add(paymentNumber);
    this.payments = paymentNumbers.size();
  }

  public void addPayment(Integer number, double interest, double principal, double remaining) {
    paymentNumbers.add(number);
    paymentInterests.add(interest);
    paymentPrincipals.add(principal);
    paymentRemainings.add(remaining);
    payments = paymentNumbers.size();
  }
}
