package cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yjlee on 2018-01-21.
 *
 *  f(1)존재 하지 않음   -> f(1) 계산  -> cache 추가
 *                          f(1) 존재하지 않음  -> f(1) 계산
 */
public class Memoizer2<A,V> implements Computable<A,V>{

    //final Map<A,V> cache = new HashMap<>();
    final Map<A,V> cache = new ConcurrentHashMap<>();
    final Computable<A,V> c;
    public Memoizer2(Computable<A,V> c){
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}


