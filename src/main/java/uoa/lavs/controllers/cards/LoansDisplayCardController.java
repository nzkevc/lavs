package uoa.lavs.controllers.cards;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.controllers.fragments.LoanBoxController;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Loans;
import uoa.lavs.utils.ControllerUtils;

public class LoansDisplayCardController extends AnchorPane implements ICard<Loans> {

  private static final Logger logger = LoggerFactory.getLogger(LoansDisplayCardController.class);

  @FXML private VBox displayVbox;

  public LoansDisplayCardController() {
    ControllerUtils.loadFxml(this, "cards/loans-display-card.fxml");
  }

  @Override
  public void render(Loans data) {
    clear();
    List<Loan> loans = data.getLoans();
    for (Loan loan : loans) {
      LoanBoxController loanBoxController = new LoanBoxController();
      loanBoxController.render(loan);
      displayVbox.getChildren().add(loanBoxController);
    }
  }

  @Override
  public void clear() {
    displayVbox.getChildren().clear();
  }

  @Override
  public Loans assemble() {
    Loans loans = new Loans();
    for (Node node : displayVbox.getChildren()) {
      Loan loan = ((LoanBoxController) node).assemble();
      loans.addLoan(loan);
    }
    return loans;
  }
}
