package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan;
import uoa.lavs.models.LoanSummary;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.ValidationUtils;

public class LoanCardController extends ICard<Loan> {

  @FXML private Label loanIdLbl;
  private String loanId;

  @FXML private MFXComboBox<LoanStatus> loanStatus;
  @FXML private MFXTextField principal;
  @FXML private MFXDatePicker startDate;
  @FXML private MFXTextField period;
  @FXML private MFXTextField term;
  @FXML private MFXTextField interestRate;
  @FXML private MFXComboBox<RateType> rateType;
  @FXML private MFXComboBox<Frequency> compoundingFrequency;
  @FXML private MFXTextField paymentAmount;
  @FXML private MFXComboBox<Frequency> paymentFrequency;
  @FXML private MFXCheckbox interestOnly;

  // loan summary details
  // @FXML private FieldController principle;
  // @FXML private FieldController rateValue;
  // @FXML private FieldController payoffDate;
  // @FXML private FieldController totalInterest;
  // @FXML private FieldController totalLoanCost;
  // @FXML private FieldController paymentAmount;

  // // loan payments details
  // @FXML private FieldController number;
  // @FXML private FieldController pages;
  // @FXML private FieldController payments;
  // @FXML private FieldController paymentInterests;

  // @FXML private FieldController paymentPrincipals;
  // @FXML private FieldController paymentRemainings;
  // @FXML private FieldController paymentNumbers;

  public LoanCardController() {
    ControllerUtils.loadFxml(this, "cards/loan-card.fxml");
  }

  @FXML
  private void initialize() {
    loanStatus.setEditable(false);
  }

  @Override
  public void render(Loan loan) {
    clear();
    loanId = loan.getLoanId() == null ? "" : loan.getLoanId();
    loanIdLbl.setText("Loan ID: " + loanId);
    if (loan.getStatus() == null) {
      loan.setStatus(LoanStatus.New);
    }
    loanStatus.setValue(loan.getStatus());

    principal.setText(String.valueOf(loan.getPrincipleCents()));
    startDate.setValue(loan.getStartDate());
    period.setText(String.valueOf(loan.getPeriodMonths()));
    term.setText(String.valueOf(loan.getTerm()));
    interestRate.setText(String.valueOf(loan.getInterestRate()));
    rateType.setValue(loan.getRateType());
    compoundingFrequency.setValue(loan.getCompoundingFrequency());
    paymentAmount.setText(String.valueOf(loan.getPaymentAmountCents()));
    paymentFrequency.setValue(loan.getPaymentFrequency());
    interestOnly.setSelected(loan.isInterestOnly());


    // LoanSummary loanSummary = loan.getLoanSummary();
    // if (loanSummary != null) {
    //   principle.setValue(String.valueOf(loanSummary.getPriciple()));
    //   rateValue.setValue(String.valueOf(loanSummary.getRateValue()));
    //   payoffDate.setValue(loanSummary.getPayoffDate().toString());
    //   totalInterest.setValue(String.valueOf(loanSummary.getTotalInterest()));
    //   totalLoanCost.setValue(String.valueOf(loanSummary.getTotalLoanCost()));
    //   paymentAmount.setValue(String.valueOf(loanSummary.getPaymentAmount()));
    // }

    // LoanPayments loanPayments = loan.getLoanPayments();
    // if (loanPayments != null) {
    //   number.setValue(String.valueOf(loanPayments.getNumber()));
    //   pages.setValue(String.valueOf(loanPayments.getPages()));
    //   payments.setValue(String.valueOf(loanPayments.getPayments()));
    //   paymentInterests.setValue(String.valueOf(loanPayments.getPaymentInterests()));
    //   // paymentPrincipals.setValue(String.valueOf(loanPayments.getPaymentPrincipals()));
    //   // paymentRemainings.setValue(String.valueOf(loanPayments.getPaymentRemainings()));
    //   // paymentNumbers.setValue(String.valueOf(loanPayments.getPaymentNumbers()));
    // }

    // // Disable fields if loan is already persisted
    // if (loan.getLoanId() != null) {
    //   principleCents.setEditable(false);
    //   startDate.setEditable(false);
    //   periodMonths.setEditable(false);
    //   term.setEditable(false);
    //   interestRate.setEditable(false);
    //   rateType.setEditable(false);
    //   compoundingFrequency.setEditable(false);
    //   paymentAmountCents.setEditable(false);
    //   paymentFrequency.setEditable(false);
    //   interestOnly.setEditable(false);

    //   principle.setEditable(false);
    //   rateValue.setEditable(false);
    //   payoffDate.setEditable(false);
    //   totalInterest.setEditable(false);
    //   totalLoanCost.setEditable(false);
    //   paymentAmount.setEditable(false);

    //   number.setEditable(false);
    //   pages.setEditable(false);
    //   payments.setEditable(false);
    //   paymentInterests.setEditable(false);
      // paymentPrincipals.setEditable(false);
      // paymentRemainings.setEditable(false);
      // paymentNumbers.setEditable(false);
    // }
  }

  @Override
  public void clear() {
    loanIdLbl.setText("");
    loanId = null;
    loanStatus.setValue(LoanStatus.New);

    principal.clear();
    startDate.clear();
    period.clear();
    term.clear();
    interestRate.clear();
    rateType.clear();
    compoundingFrequency.clear();
    paymentAmount.clear();
    paymentFrequency.clear();
    interestOnly.setSelected(false);

    // principle.clearValue();
    // rateValue.clearValue();
    // payoffDate.clearValue();
    // totalInterest.clearValue();
    // totalLoanCost.clearValue();
    // paymentAmount.clearValue();

    // number.clearValue();
    // pages.clearValue();
    // payments.clearValue();
    // paymentInterests.clearValue();
    // paymentPrincipals.clearValue();
    // paymentRemainings.clearValue();
    // paymentNumbers.clearValue();
  }

  @Override
  public Loan assemble() {
    Loan loan = new Loan();
    loan.setLoanId(loanId);
    loan.setStatus(loanStatus.getValue());
    loan.setPrincipleCents(Double.parseDouble(principal.getText()));
    loan.setStartDate(startDate.getValue());
    loan.setPeriodMonths(Integer.parseInt(period.getText()));
    loan.setTerm(Integer.parseInt(term.getText()));
    loan.setInterestRate(Double.parseDouble(interestRate.getText()));
    loan.setRateType(rateType.getValue());
    loan.setCompoundingFrequency(compoundingFrequency.getValue());
    loan.setPaymentAmountCents(Double.parseDouble(paymentAmount.getText()));
    loan.setPaymentFrequency(Frequency.valueOf(paymentFrequency.getText()));
    loan.setInterestOnly(interestOnly.isSelected());
    return loan;
  }
}
