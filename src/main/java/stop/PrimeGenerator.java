package stop;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjlee on 2018-02-01.
 */
public class PrimeGenerator implements Runnable{
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean canceled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!canceled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        canceled = true;
    }
    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }
}
