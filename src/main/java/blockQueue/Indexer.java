package blockQueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-01-21.
 */
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;
    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try{
            while (true){
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File take) {

    }
}
