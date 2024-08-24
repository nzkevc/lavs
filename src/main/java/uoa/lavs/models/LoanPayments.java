package uoa.lavs.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanPayments {
  private String loanId;
  private String customerId;
  private String customerName;
  private Integer pages;
  private Integer payments;
  private List<LocalDate> paymentDates;
  private List<Double> paymentInterests;
  private List<Double> paymentPrincipals;
  private List<Double> paymentRemainings;
  private List<Integer> paymentNumbers;

  public LoanPayments(String loanId) {
    this.loanId = loanId;
    paymentDates = new ArrayList<>();
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

  public List<LocalDate> getPaymentDates() {
    return paymentDates;
  }

  public void setPaymentDates(List<LocalDate> paymentDates) {
    this.paymentDates = paymentDates;
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

  public void addPayment(
      LocalDate date, Integer number, double interest, double principal, double remaining) {
    paymentDates.add(date);
    paymentNumbers.add(number);
    paymentInterests.add(interest);
    paymentPrincipals.add(principal);
    paymentRemainings.add(remaining);
    payments = paymentNumbers.size();
  }
}
