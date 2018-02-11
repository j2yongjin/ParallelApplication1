package stop;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-02-04.
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
    public void run(){
        try{
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()){
                queue.put(p=p.nextProbablePrime());
            }
        }catch (InterruptedExceptionApplication e){
            /* 스레드 종료 */
        }
    }
    public void cancel(){interrupt();}
}
