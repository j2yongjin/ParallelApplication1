package interrupt;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-01-21.
 *
 * 인터럽트가 발생했음을 저장해 인터럽트 상황을 잊지 않도록 한다.
 *
 */
public class TaskRunnable implements Runnable{

    BlockingQueue<Task> queue;
    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            // 인터럽트가 발생한 사실을 저장한다.
            Thread.currentThread().interrupt();
        }
    }
}
