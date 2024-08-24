package uoa.lavs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  public static void saveToCache(String key, Serializable value) {
    File cacheDir = new File(CACHE_DIR);
    File cacheFile = new File(cacheDir, key);
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
      oos.writeObject(value);
    } catch (IOException e) {
      logger.error("Failed to save to {}/{}", CACHE_DIR, key, e);
    }
  }

  public static <T> T loadFromCache(String key) throws IOException {
    File cacheFile = new File(CACHE_DIR, key);
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
      T value = (T) ois.readObject();
      if (value == null) throw new IOException("Failed to load from cache: " + key);
      return value;
    } catch (IOException | ClassNotFoundException | RuntimeException e) {
      throw new IOException("Failed to load from cache", e);
    }
  }
}
