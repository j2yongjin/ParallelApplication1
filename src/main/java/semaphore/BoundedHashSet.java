package semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by yjlee on 2018-01-21.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;
    public BoundedHashSet(int bound){   // 초기 세마포어 카운트 및 set 갯수 제한
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }
    public boolean add(T o) throws InterruptedException {
        sem.acquire();     // 미리 획득득
        boolean wasAddred = false;
        try {
            wasAddred = set.add(o);
            return wasAddred;
        }finally {
            if(!wasAddred)   // 저장되지 않은 경우 release
                sem.release();
        }
    }
    public boolean remove(Object o){  // 삭제 성공시 relaase
        boolean wasRemoved = set.remove(o);
        if(wasRemoved)
            sem.release();
        return wasRemoved;
    }



}
