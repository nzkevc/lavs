package uoa.lavs.utils;

import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtils {

  private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

  public static <T> T instantiate(Class<T> clazz) {
    try {
      return clazz.getDeclaredConstructor().newInstance();
    } catch (IllegalAccessException
        | IllegalArgumentException
        | InstantiationException
        | NoSuchMethodException
        | SecurityException
        | InvocationTargetException e) {
      logger.error("Failed to instantiate class: {}", clazz.getSimpleName(), e);
      throw new RuntimeException("Failed to instantiate class: " + clazz.getSimpleName(), e);
    }
  }
}
