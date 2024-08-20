package uoa.lavs.controllers.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.ControllerUtils;

public class LoanBoxController extends VBox implements ICard<Loan> {


    @FXML private Accordion accordion;

    // @FXML private FieldController status;
    // @FXML private FieldController principleCents;
    // @FXML private FieldController startDate;
    // @FXML private FieldController periodMonths;
    // //@FXML private FieldController term;
    // @FXML private FieldController interestRate;
    // @FXML private FieldController rateType;
    // @FXML private FieldController compoundingFrequency;
    // @FXML private FieldController paymentAmountCents;
    // //@FXML private FieldController paymentFrequency;
    // @FXML private FieldController interestOnly;
    // @FXML private FieldController loanPayments;
    // @FXML private FieldController loanSummary;


    public LoanBoxController() {
        ControllerUtils.loadFxml(this, "fragments/loan-box.fxml");
    }

    @FXML
    private void initialize() {

        
    }

    @Override
    public void render(Loan data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Loan assemble() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assemble'");
    }

}
