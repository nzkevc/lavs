package uoa.lavs.controllers.fragments;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.controllers.cards.ICard;
import uoa.lavs.controllers.cards.LoanCardController;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Loans;
import uoa.lavs.utils.ControllerUtils;

public class ScrollerController extends AnchorPane implements ICard<Loans> {

  private static final Logger logger = LoggerFactory.getLogger(ScrollerController.class);

  @FXML private VBox displayVbox;
  @FXML private Button createNewLoanBtn;

  public ScrollerController() {
    ControllerUtils.loadFxml(this, "fragments/scroller.fxml");
  }

  @Override
  public void render(Loans data) {
    clear();
    List<Loan> loans = data.getLoans();
    for (Loan loan : loans) {
      LoanCardController loanBoxController = new LoanCardController();
      loanBoxController.render(loan);
      displayVbox.getChildren().add(loanBoxController);
    }
    displayVbox.getChildren().add(createNewLoanBtn);
  }

  @Override
  public void clear() {
    displayVbox.getChildren().clear();
  }

  @Override
  public Loans assemble() {
    Loans loans = new Loans();
    for (Node node : displayVbox.getChildren().filtered(child -> child instanceof LoanCardController)) {
      Loan loan = ((LoanCardController) node).assemble();
      loans.addLoan(loan);
    }
    return loans;
  }

  @FXML
  private void onCreateNewLoanClick() {
    LoanCardController loanBoxController = new LoanCardController();
    loanBoxController.clear();
    displayVbox.getChildren().remove(createNewLoanBtn);
    displayVbox.getChildren().add(loanBoxController);
    displayVbox.getChildren().add(createNewLoanBtn);
  }
}
