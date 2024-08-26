package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.ValidationException;

public class LoanCardController extends ICard<Loan> {

  private static final Logger logger = LoggerFactory.getLogger(LoanCardController.class);

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
    Frequency[] compounding =
        new Frequency[] {Frequency.Weekly, Frequency.Monthly, Frequency.Yearly};
    Frequency[] payment =
        new Frequency[] {Frequency.Weekly, Frequency.Fortnightly, Frequency.Monthly};

    loanStatus.setDisable(true);
    loanStatus.setItems(FXCollections.observableArrayList(LoanStatus.values()));
    rateType.setItems(FXCollections.observableArrayList(RateType.values()));
    compoundingFrequency.setItems(FXCollections.observableArrayList(compounding));
    paymentFrequency.setItems(FXCollections.observableArrayList(payment));
  }

  @Override
  public void render(Loan loan) {
    clear();
    loanId = loan.getLoanId();
    loanIdLbl.setText("Loan ID: " + (loanId == null ? "" : loanId));
    ControllerUtils.renderCombo(loanStatus, loan.getStatus(), LoanStatus.New);

    ControllerUtils.renderText(principal, loan.getPrincipleCents());
    ControllerUtils.renderDate(startDate, loan.getStartDate());
    ControllerUtils.renderText(period, loan.getPeriodMonths());
    ControllerUtils.renderText(term, loan.getTerm());
    ControllerUtils.renderText(interestRate, loan.getInterestRate());
    ControllerUtils.renderCombo(rateType, loan.getRateType(), RateType.Unknown);
    ControllerUtils.renderCombo(
        compoundingFrequency, loan.getCompoundingFrequency(), Frequency.Weekly);
    ControllerUtils.renderText(paymentAmount, loan.getPaymentAmountCents());
    ControllerUtils.renderCombo(paymentFrequency, loan.getPaymentFrequency(), Frequency.Weekly);
    interestOnly.setSelected(loan.isInterestOnly());
  }

  @Override
  public void clear() {
    loanIdLbl.setText("Loan ID:");
    loanId = null;
    ControllerUtils.renderCombo(loanStatus, LoanStatus.New);

    principal.clear();
    ControllerUtils.renderDate(startDate, null);
    period.clear();
    term.clear();

    interestRate.clear();
    ControllerUtils.renderCombo(rateType, RateType.Unknown);
    ControllerUtils.renderCombo(compoundingFrequency, Frequency.Weekly);
    paymentAmount.clear();
    ControllerUtils.renderCombo(paymentFrequency, Frequency.Weekly);
    interestOnly.setSelected(false);
  }

  @Override
  public void validate() throws ValidationException {
    try {
      Loan.validateCompoundingFrequency(compoundingFrequency.getSelectedItem());
      Loan.validatePaymentFrequency(paymentFrequency.getSelectedItem());
    } catch (ValidationException e) {
      throw new ValidationException("Loan " + loanId + ": " + e.getMessage(), e);
    }
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
    loan.setPaymentFrequency(paymentFrequency.getSelectedItem());
    loan.setInterestOnly(interestOnly.isSelected());
    return loan;
  }
}
