package stop;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-02-08.
 */
public class LogServiceApp {
    public static void main(String[] args) {

    }
}

class LogService{
    private final BlockingQueue<String> queue=null;
    private final LoggerThread loggerThread=null;
    private final PrintWriter printWriter=null;

    private boolean isShutdown;
    int reservations;

    public void start(){
        loggerThread.start();
    }
    public void stop(){
        synchronized (this){
            isShutdown =true;
            loggerThread.interrupt();
        }
    }
    public void log(String msg) throws InterruptedException {
        synchronized (this){
            if(isShutdown){
                throw new IllegalStateException();
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        public void run(){
            try{
                while (true){
                    try{
                        synchronized (LogService.this){
                            if(isShutdown && reservations == 0){
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this){
                            --reservations;
                        }
                        printWriter.println(msg);

                    }catch (InterruptedException e){

                    }
                }
            }finally {
                printWriter.close();
            }
        }
    }
}

