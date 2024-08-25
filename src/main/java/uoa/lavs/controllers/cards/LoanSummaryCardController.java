package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uoa.lavs.models.LoanSummary;
import uoa.lavs.utils.ControllerUtils;

public class LoanSummaryCardController extends ICard<LoanSummary> {

  @FXML private Label loanIdLabel;
  @FXML private MFXButton paymentsBtn;
  @FXML private MFXTextField principleField;
  @FXML private MFXTextField rateValueField;
  @FXML private MFXTextField payoffDateField;
  @FXML private MFXTextField termField;
  @FXML private MFXTextField totalInterestField;
  @FXML private MFXTextField totalLoanCostField;
  @FXML private MFXTextField paymentAmountField;
  @FXML private MFXTextField paymentFrequencyField;

  public LoanSummaryCardController() {
    ControllerUtils.loadFxml(this, "cards/loan-summary-card.fxml");
  }

  @Override
  public void render(LoanSummary loanSummary) {

    loanIdLabel.setText("Loan ID: " + loanSummary.getLoanId());
    principleField.setText(loanSummary.getPriciple().toString());
    rateValueField.setText(loanSummary.getRateValue().toString());
    payoffDateField.setText(loanSummary.getPayoffDate().toString());
    termField.setText(loanSummary.getTerm().toString());
    totalInterestField.setText(loanSummary.getTotalInterest().toString());
    totalLoanCostField.setText(loanSummary.getTotalLoanCost().toString());
    paymentAmountField.setText(loanSummary.getPaymentAmount().toString());
    paymentFrequencyField.setText(loanSummary.getPaymentFrequency().toString());
  }

  @Override
  public void clear() {
    loanIdLabel.setText("Loan ID: ");
    principleField.clear();
    rateValueField.clear();
    payoffDateField.clear();
    termField.clear();
    totalInterestField.clear();
    totalLoanCostField.clear();
    paymentAmountField.clear();
    paymentFrequencyField.clear();
  }

  @Override
  public LoanSummary assemble() {
    throw new UnsupportedOperationException("Do not assemble LoanSummary");
  }
}
