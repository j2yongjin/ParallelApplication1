package future;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by yjlee on 2018-01-24.
 */
public class TaskExecutorWebServer {

    private static final  int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(80);
        while(true){
            final Socket connection = serverSocket.accept();

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }

                private void handleRequest(Socket connection) {
                    System.out.println(" test connection ");
                }
            };

            exec.execute(task);
        }
    }
}

interface ExamTask extends Executor{

    public void task1();

}

class Exam1 implements ExamTask{

    @Override
    public void execute(Runnable command) {

        DelayQueue<Delayed> delayQueue = new DelayQueue<Delayed>();

    }

    @Override
    public void task1() {

    }
}
