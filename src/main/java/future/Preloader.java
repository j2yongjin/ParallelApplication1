package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by yjlee on 2018-01-21.
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                @Override
                public ProductInfo call() throws Exception {
                    Thread.sleep(2000); // 실제로 오래 걸리는 작업이 있다면 DB / FILE 등등
                    return new ProductInfo("딸기");
                }
            });

    private final Thread thread = new Thread(future);
    public void start(){
        thread.start();
    }
    public ProductInfo get() throws ExecutionException, InterruptedException {
        try {
            return future.get();
        }catch (ExecutionException e){
            Throwable cause = e.getCause();
            throw e;
        }
    }



}
