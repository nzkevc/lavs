package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.layout.VBox;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan;
import uoa.lavs.models.LoanPayments;
import uoa.lavs.models.LoanSummary;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.ValidationUtils;

public class LoanBoxController extends VBox implements ICard<Loan> {

  @FXML private Accordion accordion;

  // loan details
  @FXML private FieldController status;
  @FXML private FieldController principleCents;
  @FXML private FieldController startDate;
  @FXML private FieldController periodMonths;
  @FXML private FieldController term;
  @FXML private FieldController interestRate;
  @FXML private FieldController rateType;
  @FXML private FieldController compoundingFrequency;
  @FXML private FieldController paymentAmountCents;
  @FXML private FieldController paymentFrequency;
  @FXML private FieldController interestOnly;

  // loan summary details
  @FXML private FieldController principle;
  @FXML private FieldController rateValue;
  @FXML private FieldController payoffDate;
  @FXML private FieldController totalInterest;
  @FXML private FieldController totalLoanCost;
  @FXML private FieldController paymentAmount;

  // loan payments details
  @FXML private FieldController loanId;
  @FXML private FieldController number;
  @FXML private FieldController pages;
  @FXML private FieldController payments;
  @FXML private FieldController paymentInterests;
  @FXML private FieldController paymentPrincipals;
  @FXML private FieldController paymentRemainings;
  @FXML private FieldController paymentNumbers;

  public LoanBoxController() {
    ControllerUtils.loadFxml(this, "fragments/loan-box.fxml");
  }

  @Override
  public void render(Loan loan) {
    status.setValue(loan.getStatus().toString());
    principleCents.setValue(String.valueOf(loan.getPrincipleCents()));
    startDate.setValue(loan.getStartDate().toString());
    periodMonths.setValue(String.valueOf(loan.getPeriodMonths()));
    term.setValue(String.valueOf(loan.getTerm()));
    interestRate.setValue(String.valueOf(loan.getInterestRate()));
    rateType.setValue(loan.getRateType().toString());
    compoundingFrequency.setValue(loan.getCompoundingFrequency().toString());
    paymentAmountCents.setValue(String.valueOf(loan.getPaymentAmountCents()));
    paymentFrequency.setValue(loan.getPaymentFrequency().toString());
    interestOnly.setValue(String.valueOf(loan.isInterestOnly()));

    LoanSummary loanSummary = loan.getLoanSummary();
    principle.setValue(String.valueOf(loanSummary.getPriciple()));
    rateValue.setValue(String.valueOf(loanSummary.getRateValue()));
    payoffDate.setValue(loanSummary.getPayoffDate().toString());
    totalInterest.setValue(String.valueOf(loanSummary.getTotalInterest()));
    totalLoanCost.setValue(String.valueOf(loanSummary.getTotalLoanCost()));
    paymentAmount.setValue(String.valueOf(loanSummary.getPaymentAmount()));

    LoanPayments loanPayments = loan.getLoanPayments();
    loanId.setValue(String.valueOf(loanPayments.getLoanId()));
    number.setValue(String.valueOf(loanPayments.getNumber()));
    pages.setValue(String.valueOf(loanPayments.getPages()));
    payments.setValue(String.valueOf(loanPayments.getPayments()));
    paymentInterests.setValue(String.valueOf(loanPayments.getPaymentInterests()));
    paymentPrincipals.setValue(String.valueOf(loanPayments.getPaymentPrincipals()));
    paymentRemainings.setValue(String.valueOf(loanPayments.getPaymentRemainings()));
    paymentNumbers.setValue(String.valueOf(loanPayments.getPaymentNumbers()));
  }

  @Override
  public void clear() {
    status.clearValue();
    principleCents.clearValue();
    startDate.clearValue();
    periodMonths.clearValue();
    term.clearValue();
    interestRate.clearValue();
    rateType.clearValue();
    compoundingFrequency.clearValue();
    paymentAmountCents.clearValue();
    paymentFrequency.clearValue();
    interestOnly.clearValue();

    principle.clearValue();
    rateValue.clearValue();
    payoffDate.clearValue();
    totalInterest.clearValue();
    totalLoanCost.clearValue();
    paymentAmount.clearValue();

    loanId.clearValue();
    number.clearValue();
    pages.clearValue();
    payments.clearValue();
    paymentInterests.clearValue();
    paymentPrincipals.clearValue();
    paymentRemainings.clearValue();
    paymentNumbers.clearValue();
  }

  @Override
  public Loan assemble() {
    Loan loan = new Loan();
    loan.setStatus(LoanStatus.valueOf(status.getValue()));
    loan.setPrincipleCents(Integer.parseInt(principleCents.getValue()));
    loan.setStartDate(ValidationUtils.getDateFromField(startDate));
    loan.setPeriodMonths(Integer.parseInt(periodMonths.getValue()));
    loan.setTerm(Integer.parseInt(term.getValue()));
    loan.setInterestRate(Double.parseDouble(interestRate.getValue()));
    loan.setRateType(RateType.valueOf(rateType.getValue()));
    loan.setCompoundingFrequency(Frequency.valueOf(compoundingFrequency.getValue()));
    loan.setPaymentAmountCents(Integer.parseInt(paymentAmountCents.getValue()));
    loan.setPaymentFrequency(Frequency.valueOf(paymentFrequency.getValue()));
    loan.setInterestOnly(Boolean.parseBoolean(interestOnly.getValue()));

    LoanSummary loanSummary = new LoanSummary();
    loanSummary.setPriciple(Double.valueOf(principle.getValue()));
    loanSummary.setRateValue(Double.valueOf(rateValue.getValue()));
    loanSummary.setPayoffDate(ValidationUtils.getDateFromField(payoffDate));
    loanSummary.setTotalInterest(Double.valueOf(totalInterest.getValue()));
    loanSummary.setTotalLoanCost(Double.valueOf(totalLoanCost.getValue()));
    loanSummary.setPaymentAmount(Double.valueOf(paymentAmount.getValue()));
    loan.setLoanSummary(loanSummary);

    return loan;
  }
}
