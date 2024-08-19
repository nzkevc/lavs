package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ControllerUtils;

public class SummaryPageController extends AnchorPane implements IController {

    @FXML private Button backBtn;

    @FXML private Label errorLbl;
    @FXML private Button submitBtn;

    @FXML private AnchorPane infoPane;
    @FXML private Label customerName;
    @FXML private Label customerID;

    @FXML private Button generalBtn;
    @FXML private Button contactBtn;
    @FXML private Button employerBtn;
    @FXML private Button notesBtn;
    @FXML private Button loansBtn;

    @FXML private AnchorPane infoCard;
    
  public SummaryPageController() {
     ControllerUtils.loadFxml(this, "pages/summary-page.fxml");
  }
    
}
