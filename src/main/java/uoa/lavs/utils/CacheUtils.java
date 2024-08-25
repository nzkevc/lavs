package uoa.lavs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Customer;

public class CacheUtils {

  private static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);

  public static final String CACHE_DIR = "data/cache";

  public static void clearCache() {
    File cacheDir = new File(CACHE_DIR);
    if (cacheDir.exists()) {
      for (File file : cacheDir.listFiles()) {
        file.delete();
      }
    }
  }

  public static void saveToCache(String key, Customer customer) {
    try {
      File cacheFile = new File(CACHE_DIR, key + ".ser");
      logger.debug("Saving to {}", cacheFile.getAbsolutePath());
      String parent = cacheFile.getParent();
      if (parent == null) Files.createDirectories(Paths.get(parent));
      if (cacheFile.exists()) cacheFile.delete();
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
        if (customer != null) {
          oos.writeObject(customer);
        } // Else just delete the file
      }
    } catch (IOException e) {
      logger.error("Failed to save to {}/{}", CACHE_DIR, key);
      throw new RuntimeException(e);
    }
  }

  public static <T> T loadFromCache(String key) throws IOException {
    File cacheFile = new File(CACHE_DIR, key + ".ser");
    logger.debug("Reading from {}", cacheFile.getAbsolutePath());
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
      T value = (T) ois.readObject();
      if (value == null) throw new IOException("Failed to load from cache: " + key);
      return value;
    } catch (IOException | ClassNotFoundException | RuntimeException e) {
      throw new IOException("Failed to load from cache", e);
    }
  }
}
