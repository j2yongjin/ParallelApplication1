/**
 * Created by yjlee on 2018-01-14.
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        public void run(){
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] arsg){
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}


