package stop;

import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-02-05.
 */
public class InterruptedExceptionExample2 {

    public Task getNextTask(BlockingQueue<Task> queue){
        boolean interrupted = false;
        try{
            while (true){
                try{
                    return queue.take();
                }catch (InterruptedException e){
                    interrupted =true;
                }
            }
        }finally {
            if(interrupted)
                Thread.currentThread().interrupt();

        }
    }
}
