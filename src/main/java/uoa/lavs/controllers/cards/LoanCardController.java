package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.ControllerUtils;

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
    //ObservableList<LoanStatus> loanStatusList = FXCollections.observableArrayList(LoanStatus.values());
    loanStatus.setItems(FXCollections.observableArrayList(LoanStatus.values()));
    principal.setText(String.valueOf(loan.getPrincipleCents()));
    startDate.setValue(loan.getStartDate());
    period.setText(String.valueOf(loan.getPeriodMonths()));
    term.setText(String.valueOf(loan.getTerm()));
    interestRate.setText(String.valueOf(loan.getInterestRate()));
    rateType.setItems(FXCollections.observableArrayList(RateType.values()));
    compoundingFrequency.setItems(FXCollections.observableArrayList(Frequency.values()));
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
    //   pages.setValue(String.valueOf(loanPayments.getPages()));
    //   payments.setValue(String.valueOf(loanPayments.getPayments()));
    //   paymentInterests.setValue(String.valueOf(loanPayments.getPaymentInterests()));
      // paymentPrincipals.setValue(String.valueOf(loanPayments.getPaymentPrincipals()));
      // paymentRemainings.setValue(String.valueOf(loanPayments.getPaymentRemainings()));
      // paymentNumbers.setValue(String.valueOf(loanPayments.getPaymentNumbers()));
    // }

    // // Disable fields if loan is already persisted
    // if (loan.getLoanId() != null) {
    //  
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
  }

  @Override
  public Loan assemble() {
    Loan loan = new Loan();
    loan.setLoanId(loanId);
    loan.setStatus(loanStatus.getSelectedItem());
    loan.setPrincipleCents(Double.parseDouble(principal.getText()));
    loan.setStartDate(startDate.getValue());
    loan.setPeriodMonths(Integer.parseInt(period.getText()));
    loan.setTerm(Integer.parseInt(term.getText()));
    loan.setInterestRate(Double.parseDouble(interestRate.getText()));
    loan.setRateType(rateType.getSelectedItem());
    loan.setCompoundingFrequency(compoundingFrequency.getSelectedItem());
    loan.setPaymentAmountCents(Double.parseDouble(paymentAmount.getText()));
    loan.setPaymentFrequency(Frequency.valueOf(paymentFrequency.getText()));
    loan.setInterestOnly(interestOnly.isSelected());
    return loan;
  }
}
