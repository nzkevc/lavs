package uoa.lavs.utils;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class AsyncUtils {

  private static final ListeningExecutorService futureFactory =
      MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

  // Create JS-style Promises (ListenableFuture)
  public static <T> ListenableFuture<T> promise(Callable<T> callable) {
    return futureFactory.submit(callable);
  }

  public static void close() {
    futureFactory.shutdown();
  }
}
