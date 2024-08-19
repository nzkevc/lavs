package uoa.lavs.controllers.pages;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.services.ExampleService;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class ContactInfoController implements IController {

  private static final Logger logger = LoggerFactory.getLogger(ContactInfoController.class);

  private static final Component<ContactInfoController> singleton =
      ResourceUtils.loadFxml("fragments/cards/customer/contact-info2.fxml");

  @FXML private FieldController address;
  @FXML private FieldController suburb;
  @FXML private FieldController city;
  @FXML private FieldController postcode;
  @FXML private FieldController country;
  

  public static Component<ContactInfoController> getSingleton() {
    return singleton;
  }

  


  
}
