package cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by yjlee on 2018-01-21.
 */
public class Memoizer3<A,V> implements Computable<A,V> {

    final Computable<A,V> c;

    final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {

        Future<V> f = cache.get(arg);
        if(f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f =ft;
            cache.put(arg,ft);
            ft.run();
        }
        return f.get();

    }

}
