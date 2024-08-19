package uoa.lavs.utils;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncUtils {

  private static final Logger logger = LoggerFactory.getLogger(AsyncUtils.class.getName());

  private static final ListeningExecutorService futureFactory =
      MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

  // Create JS-style Promises (ListenableFuture)
  public static <T> void promise(Callable<T> callable) {
    promise(callable, result -> {}, throwable -> {});
  }

  // With onSuccess callback
  public static <T> void promise(Callable<T> callable, Consumer<T> onSuccess) {
    promise(callable, onSuccess, throwable -> {});
  }

  // With onSuccess and onFailure callbacks
  public static <T> void promise(
      Callable<T> callable, Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
    ListenableFuture<T> future = futureFactory.submit(callable);

    FutureCallback<T> callback =
        new FutureCallback<T>() {
          @Override
          public void onSuccess(T result) {
            try {
              onSuccess.accept(result);
            } catch (Exception e) {
              onFailure(e);
            }
          }

          @Override
          public void onFailure(Throwable throwable) {
            try {
              onFailure.accept(throwable);
            } catch (Exception e) {
              logger.error("Error in onFailure callback", e);
            }
          }
        };

    Futures.addCallback(future, callback, futureFactory);
  }

  public static void close() {
    futureFactory.shutdown();
  }
}
