package uoa.lavs.controllers.cards;

import java.time.LocalDate;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.models.LoanSummary;

public class LoanSummaryCardController extends ICard<LoanSummary> {

    @FXML private MFXTextField loanIdField;
    @FXML private MFXTextField customerIdField;
    @FXML private MFXTextField customerNameField;
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

        loanIdField.setText(data.getLoanId());
        customerIdField.setText(data.getCustomerId());
        customerNameField.setText(data.getCustomerName());
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
        loanIdField.clear();
        customerIdField.clear();
        customerNameField.clear();
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
        LoanSummary loanSummary = new LoanSummary();
        loanSummary.setLoanId(loanIdField.getText());
        loanSummary.setCustomerId(customerIdField.getText());
        loanSummary.setCustomerName(customerNameField.getText());
        loanSummary.setPriciple(Double.parseDouble(principleField.getText()));
        loanSummary.setRateValue(Double.parseDouble(rateValueField.getText()));
        loanSummary.setPayoffDate(LocalDate.parse(payoffDateField.getText()));
        loanSummary.setTerm(Integer.parseInt(termField.getText()));
        loanSummary.setTotalInterest(Double.parseDouble(totalInterestField.getText()));
        loanSummary.setTotalLoanCost(Double.parseDouble(totalLoanCostField.getText()));
        loanSummary.setPaymentAmount(Double.parseDouble(paymentAmountField.getText()));
        loanSummary.setPaymentFrequency(Frequency.valueOf(paymentFrequencyField.getText()));
        return loanSummary;
    }
   



   
}
