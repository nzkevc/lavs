package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import uoa.lavs.models.Loan;

public class LoanParentCardController extends ICard<Loan> {
    
    @FXML Label loanIdLbl;
    @FXML MFXButton SummaryBtn;
    @FXML Pane SwitchPane;

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
