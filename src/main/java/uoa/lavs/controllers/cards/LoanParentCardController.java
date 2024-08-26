package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.ValidationUtils;

public class LoanParentCardController extends ICard<Loan> {

  private static final Logger logger = LoggerFactory.getLogger(LoanParentCardController.class);

  private String loanId;
  private final LoanCardController loanCreationCardController = new LoanCardController();
  private final LoanSummaryCardController loanSummaryCardController =
      new LoanSummaryCardController();
  private final LoanPaymentsCardController loanPaymentsCardController =
      new LoanPaymentsCardController();

  @FXML private Label loanIdLbl;
  @FXML private Button swapBtn;
  @FXML private Pane switchPane;

  public LoanParentCardController() {
    ControllerUtils.loadFxml(this, "cards/loan-parent-card.fxml");
  }

  @FXML
  private void initialize() {
    render(new Loan());
  }

  @Override
  public void render(Loan loan) {
    loanId = loan.getLoanId();

    if (ValidationUtils.isNullOrBlank(loanId)) {
      loanIdLbl.setText("New Loan");
      loanCreationCardController.render(loan);
      ControllerUtils.swapComponent(switchPane, loanCreationCardController);
      swapBtn.setVisible(false);
    } else {
      loanIdLbl.setText("Loan ID: " + loan.getLoanId());
      loanSummaryCardController.render(loan.getLoanSummary());
      loanPaymentsCardController.render(loan.getLoanPayments());
      ControllerUtils.swapComponent(switchPane, loanSummaryCardController);
      swapBtn.setVisible(true);
    }
  }

  @Override
  public void clear() {
    logger.debug(String.valueOf(loanIdLbl));
    loanId = null;
    loanIdLbl.setText("New Loan");
    loanCreationCardController.clear();
    loanSummaryCardController.clear();
    loanPaymentsCardController.clear();
  }

  @Override
  public Loan assemble() {
    return loanCreationCardController.assemble();
  }

  @FXML
  private void onSwapBtnClick() {
    logger.debug("Summary button clicked");
    if (loanId == null) {
      return;
    }

    if (switchPane.getChildren().contains(loanSummaryCardController)) {
      ControllerUtils.swapComponent(switchPane, loanPaymentsCardController);
      swapBtn.setText("Summary");
    } else {
      ControllerUtils.swapComponent(switchPane, loanSummaryCardController);
      swapBtn.setText("Payments");
    }
  }
}
