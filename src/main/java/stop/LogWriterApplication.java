package stop;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-02-06.
 */
public class LogWriterApplication {
}

class LogWriter{
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    boolean shutdownRequested =false;

    public LogWriter(BlockingQueue<String> queue, LoggerThread logger) {
        this.queue = queue;
        this.logger = logger;
    }
    public void start(){logger.start();}
    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    public void logCustom(String msg) throws InterruptedException {
        if(!shutdownRequested)
            queue.put(msg);
    }

}
class LoggerThread extends Thread{
    private final PrintWriter writer;
    BlockingQueue<String> queue;

    public LoggerThread(PrintWriter writer) {
        this.writer = writer;
    }
    public  void run(){
        try{
            while (true)
                writer.println(queue.take());
        }catch (InterruptedException ignore){

        }finally {
            writer.close();
        }
    }

}
