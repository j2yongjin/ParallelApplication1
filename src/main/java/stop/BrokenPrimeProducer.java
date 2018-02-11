package stop;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-02-01.
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    public void run(){
        try{
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());

        }catch (InterruptedExceptionApplication consumed){
        }
    }
    public void cancel(){
        cancelled = true;
    }
}

class BrokenPrimeConsumer{
    private final BlockingQueue<BigInteger> queue;

    public BrokenPrimeConsumer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run(){
        try {
            while (needMorePrimes()) {
                consume(queue.take());
            }
        } catch (InterruptedExceptionApplication e) {
            e.printStackTrace();
        } finally {

            cancel();
        }
    }

    private void consume(BigInteger b){

    }

    private void cancel() {

    }

    private boolean needMorePrimes() {
        return false;
    }
}
