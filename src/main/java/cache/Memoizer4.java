package cache;

import java.util.concurrent.*;

/**
 * Created by yjlee on 2018-01-21.
 */
public class Memoizer4<A,V> implements Computable<A,V>{
    final ConcurrentMap<A ,Future<V>> cache = new ConcurrentHashMap<>();
    final Computable<A,V> c;

    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {
        while (true){
            Future<V> f = cache.get(arg);
            if(f == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };

                FutureTask<V> ft = new FutureTask<V>(eval);
                f= cache.putIfAbsent(arg,ft);
                if(f == null){
                    f =ft; ft.run();
                }
            }
            return f.get();
        }
    }
}
