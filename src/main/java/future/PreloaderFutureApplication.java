package future;

import java.util.concurrent.ExecutionException;

/**
 * Created by yjlee on 2018-01-21.
 */
public class PreloaderFutureApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Preloader preloader = new Preloader();
        preloader.start();
        ProductInfo productInfo = preloader.get();
        System.out.println(" prductInfo name " + productInfo.getName());
    }
}
