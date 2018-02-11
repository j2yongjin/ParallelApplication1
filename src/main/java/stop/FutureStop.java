package stop;

import java.util.concurrent.*;

/**
 * Created by yjlee on 2018-02-05.
 */
public class FutureStop {

    public static void timedRun(final Runnable r , long timeout , TimeUnit timeUnit) throws InterruptedException{


        class RethrowableTask implements Runnable{
            private volatile Throwable t;
            @Override
            public void run() {
                try{r.run();
                }catch (Throwable t){
                    this.t = t;
                }
            }
            void rethrow(){
                if(t != null){
                    throw launderThrowable(t);
                }
            }
            private RuntimeException launderThrowable(Throwable t) {
                return null;
            }
        }

        RethrowableTask rethrowableTask = new RethrowableTask();
        final Thread thread = new Thread(rethrowableTask);
        thread.start();

//        cacelExec.s

    }

//    public static void timedRun(Runnable r , long timeout , TimeUnit timeUnit) throws InterruptedException{
//        Future<?> task = new taskExec.submit(r);
//        try{
//            task.get(timeout,timeUnit);
//        }catch (TimeoutException t){
//
//        }catch (ExecutionException e){
//
//        }finally {
//            task.cancel(true);
//        }
//    }
}
