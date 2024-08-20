package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.models.Loans;
import uoa.lavs.utils.ControllerUtils;

public class LoansDisplayCardController extends AnchorPane implements ICard<Loans> {
    
    private static final Logger logger = LoggerFactory.getLogger(LoansDisplayCardController.class);

    @FXML private VBox displayVbox;
    
    public LoansDisplayCardController() {
    ControllerUtils.loadFxml(this, "cards/loans/loans-info.fxml");
  }

    @Override
    public void render(Loans data) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void clear() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Loans assemble() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'assemble'");
    }
}
