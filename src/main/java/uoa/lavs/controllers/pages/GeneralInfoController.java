package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class GeneralInfoController implements IController {

    @FXML private FieldController firstName;
    @FXML private FieldController lastName;
    @FXML private FieldController dateOfBirth;
    @FXML private FieldController citizenship;
    @FXML private FieldController visaType;
    @FXML private FieldController occupation;
    @FXML private FieldController status;


    private static final Component<GeneralInfoController> singleton =
    ResourceUtils.loadFxml("fragments/cards/customer/general-info.fxml");

    public static Component<GeneralInfoController> getSingleton() {
        return singleton;
    }

   
    
    
}
