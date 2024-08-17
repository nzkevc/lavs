package uoa.lavs.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.objects.Component;

public class ResourceUtils {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  /**
   * Load a resource (e.g. image, css) and retrive its contents as a string. Urls are relative to
   * the resources directory (e.g. "css/globals.css").
   *
   * @param url the url of the resource relative to the resources directory
   * @return the resource as a string
   */
  private static String loadAsString(String url) {
    return App.class.getResource(url).toExternalForm();
  }

  /**
   * Load a CSS file and return it. Urls are relative to the css directory (e.g. globals.css).
   *
   * @param url the url of the css file relative to the css directory
   * @return the css file as a string
   */
  public static String loadCss(String url) {
    return loadAsString("css/" + url);
  }

  /**
   * Load an image and return it. Urls are relative to the images directory (e.g. logo.png).
   *
   * @param url the url of the image file relative to the images directory
   * @return the image as an Image object
   */
  public static Image loadImage(String url) {
    return new Image(loadAsString("images/" + url));
  }

  /**
   * Load an FXML file and return a pair containing the root node and associated controller. The
   * view must link to a controller. Urls are relative to the views directory (e.g.
   * pages/landing-page.fxml)
   *
   * @param <T> the type of the controller
   * @param url the url of the FXML file relative to the views directory
   * @return a Component object containing the root node and controller
   */
  public static <T extends IController> Component<T> loadFxml(String url) {
    logger.debug("Loading FXML: " + url);
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + url));
    Parent root; // Access with Pair.getKey()
    T controller = fxmlLoader.getController(); // Access with Pair.getValue()
    try {
      root = fxmlLoader.load();
    } catch (IOException e) {
      logger.error("Could not load fxml: " + url);
      root = new AnchorPane(); // Avoid crashing application with null
    }
    return new Component<>(url, root, controller);
  }

  /**
   * Load a font and register it with JavaFX. Urls are relative to the fonts directory (e.g.
   * Montserrat-Medium.ttf).
   *
   * @param url the url of the font file relative to the fonts directory
   */
  public static void loadFont(String url) {
    Font.loadFont(loadAsString("fonts/" + url), 10);
  }
}
