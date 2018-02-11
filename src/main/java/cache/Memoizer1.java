package cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by yjlee on 2018-01-21.
 *
 *   병렬성이 좋지 않은 경우
 *
 *   A  --> getLock --> F(1)  --> lock free
 *   B                                      --> get lock -> f(2) -> lock free
 *
 */
public class Memoizer1<A,V> implements Computable<A,V>{

    final Map<A,V> cache = new HashMap<>();
    final Computable<A,V> c;
    public Memoizer1(Computable<A,V> c){
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException, ExecutionException {
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}

interface Computable<A,V>{
    V compute(A arg) throws InterruptedException, ExecutionException;
}
class ExpensiaveFunction implements Computable<String , BigInteger>{

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}




