package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ControllerUtils;

public class LoansDisplayCardController extends AnchorPane implements IController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoansDisplayCardController.class);

    @FXML private VBox displayVbox;
    
    public LoansDisplayCardController() {
    ControllerUtils.loadFxml(this, "cards/loans/loans-info.fxml");
  }
}
