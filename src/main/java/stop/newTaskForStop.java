package stop;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by yjlee on 2018-02-05.
 */
public class newTaskForStop {

    public static void main(String[] args) {

        BlockingQueue<Runnable> str= null;

//        CancelingExecutor cancelingExecutor = new CancelingExecutor(1,1,10L,TimeUnit.SECONDS,str);
//        cancelingExecutor.newTaskFor(new SocketUsingTask<String>() {
//        })

    }


}


interface CancellableTask<T> extends Callable<T>{
    void cancel();
    RunnableFuture<T> newTask();
}

class CancelingExecutor extends ThreadPoolExecutor{

    public CancelingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable){
        if(callable instanceof CancellableTask){
            return ((CancellableTask<T>) callable).newTask();
        }else
            return super.newTaskFor(callable);
    }

}

abstract class SocketUsingTask<T> implements CancellableTask<T>{
    private Socket socket;
    protected synchronized void setSocket(Socket s){
        socket=s;
    }
    public synchronized void cancel(){
        try{
            if(socket!= null)
                socket.close();
        }catch (IOException ignored){}
    }
    public RunnableFuture<T> newTask(){
        return new FutureTask<T>(this){
            public boolean cancel(boolean mayInterruptIfRunning){
                try{
                    SocketUsingTask.this.cancel();
                }finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }

}
