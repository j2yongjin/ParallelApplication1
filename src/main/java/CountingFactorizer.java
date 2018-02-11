import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yjlee on 2018-01-14.
 */
public class CountingFactorizer
{
    private AtomicLong count = null;
//    public long getCount{return count;}

    public CountingFactorizer(AtomicLong count) {
        this.count = count;
    }

    public void service(Integer req , Integer resp){
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
//        encodeIntoResponse(resp,factors);
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(Integer req) {
        return new BigInteger(String.valueOf(1));

    }
}
