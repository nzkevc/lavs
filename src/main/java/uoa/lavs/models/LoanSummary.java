package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.mainframe.Frequency;

public class LoanSummary {
  private String loanId;
  private String customerId;
  private String customerName;
  private Double priciple;
  private Double rateValue;
  private LocalDate payoffDate;
  private Integer term;
  private Double totalInterest;
  private Double totalLoanCost;
  private Double paymentAmount;
  private Frequency paymentFrequency;

  public LoanSummary(String loanId) {
    this.loanId = loanId;
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

  public Double getPriciple() {
    return priciple;
  }

  public void setPriciple(Double priciple) {
    this.priciple = priciple;
  }

  public Double getRateValue() {
    return rateValue;
  }

  public void setRateValue(Double rateValue) {
    this.rateValue = rateValue;
  }

  public LocalDate getPayoffDate() {
    return payoffDate;
  }

  public void setPayoffDate(LocalDate payoffDate) {
    this.payoffDate = payoffDate;
  }

  public Integer getTerm() {
    return term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }

  public Double getTotalInterest() {
    return totalInterest;
  }

  public void setTotalInterest(Double totalInterest) {
    this.totalInterest = totalInterest;
  }

  public Double getTotalLoanCost() {
    return totalLoanCost;
  }

  public void setTotalLoanCost(Double totalLoanCost) {
    this.totalLoanCost = totalLoanCost;
  }

  public Double getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(Double paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public Frequency getPaymentFrequency() {
    return paymentFrequency;
  }

  public void setPaymentFrequency(Frequency paymentFrequency) {
    this.paymentFrequency = paymentFrequency;
  }
}
