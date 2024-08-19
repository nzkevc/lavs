package uoa.lavs.controllers.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class EmployerInfoController implements IController {

    @FXML private FieldController employerName;
    @FXML private FieldController address;
    @FXML private FieldController suburb;
    @FXML private FieldController city;
    @FXML private FieldController postcode;
    @FXML private FieldController country;

    @FXML private FieldController phone;
    @FXML private FieldController emailAddress;
    @FXML private FieldController website;

    

    private static final Logger logger = LoggerFactory.getLogger(EmployerInfoController.class);

    private static final Component<EmployerInfoController> singleton =
        ResourceUtils.loadFxml("fragments/cards/customer/employer-info.fxml");
    
    public static Component<EmployerInfoController> getSingleton() {
        return singleton;
    }



}
