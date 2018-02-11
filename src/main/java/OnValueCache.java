import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yjlee on 2018-01-14.
 */
public class OnValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastfactors;

    public OnValueCache(BigInteger lastNumber, BigInteger[] lastfactors) {
        this.lastNumber = lastNumber;
        this.lastfactors = Arrays.copyOf(lastfactors,lastfactors.length);
    }
    public BigInteger[] getFactors(BigInteger i){
        if(lastNumber == null || !lastfactors.equals(i)){
            return null;
        }else return Arrays.copyOf(lastfactors,lastfactors.length);
    }
}

//class VolatileCachedFactorizer implements Servlet{
//    private volatile OnValueCache cache = new OnValueCache(null,null);
//    public void service(ServletRequest request,ServletResponse response){
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = cache.getFactors(i);
//        if(factors == null){
//            factors = factor(i);
//            cache = new OnValueCache(i,factors);
//        }
//        encodeIntoResponse(resp,factors);
//    }
//}


@ThreadSafe
class PersonSet{

    @GuardedBy("this")
    private final Set<String> mySet = new HashSet<>();

    public synchronized void addPerson(String p){
        mySet.add(p);
    }

    public synchronized boolean containsPerson(String p){
        return mySet.contains(p);
    }
}


class DelegatingVehicleTracker{

    final ConcurrentMap<String ,Long> locations;
    public DelegatingVehicleTracker(ConcurrentMap<String, Long> locations) {
        this.locations = locations;
    }

    public Long getLocations(String id){
        return locations.get(id);
    }
}


class NumberRange {

    final AtomicInteger lower = new AtomicInteger(0);
    final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i){
        if(i > upper.get())  throw  new IllegalArgumentException();
        lower.set(1);
    }

}

//class ImprovedList<T> implements List<T>{
//    public final List<T> list;
//    public ImprovedList(List<T> list){this.list = list;}
//    public synchronized boolean putIfAbsent(T x){
//        boolean contains = list.contains(x);
//        if(!contains)
//            list.add(x);
//        return contains;
//    }
//}
