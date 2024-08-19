package uoa.lavs.controllers.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class NoteController implements IController {

    @FXML private TextArea note;
    

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    private static final Component<NoteController> singleton =
        ResourceUtils.loadFxml("fragments/cards/customer/notes.fxml");
    
    public static Component<NoteController> getSingleton() {
        return singleton;
    }
    
    
}
