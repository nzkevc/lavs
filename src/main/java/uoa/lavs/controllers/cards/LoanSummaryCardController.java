package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uoa.lavs.models.LoanSummary;
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

    @Override
    public void render(LoanSummary data) {

        loanIdLabel.setText("Loan ID: " + data.getLoanId());
        principleField.setText(data.getPriciple().toString());
        rateValueField.setText(data.getRateValue().toString());
        payoffDateField.setText(data.getPayoffDate().toString());
        termField.setText(data.getTerm().toString());
        totalInterestField.setText(data.getTotalInterest().toString());
        totalLoanCostField.setText(data.getTotalLoanCost().toString());
        paymentAmountField.setText(data.getPaymentAmount().toString());
        paymentFrequencyField.setText(data.getPaymentFrequency().toString());
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
       return null;
    }
   
}
