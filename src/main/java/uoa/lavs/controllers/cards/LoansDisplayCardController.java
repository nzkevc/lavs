package uoa.lavs.controllers.cards;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    List<Loan> loans = data.getLoans();
    for (Loan loan : loans) {
      logger.info("Loan: {}", loan);
    }

    logger.warn("Unimplemented method 'render'");
  }

  @Override
  public void clear() {
    logger.warn("Unimplemented method 'clear'");
  }

  @Override
  public Loans assemble() {
    logger.warn("Unimplemented method 'assemble'");
    return new Loans();
  }
}
