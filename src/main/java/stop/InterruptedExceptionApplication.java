package stop;

import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-02-05.
 *   InterruptedException을 상위 메소드로 전달
 */
public class InterruptedExceptionApplication {
    BlockingQueue<Task> queue;
    public Task getNextTask() throws InterruptedException{
        return queue.take();
    }
}



