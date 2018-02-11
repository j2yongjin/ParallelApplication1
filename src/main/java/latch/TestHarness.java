package latch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yjlee on 2018-01-21.
 */
public class TestHarness {

    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i=0;i<nThreads;i++){
            Thread t = new Thread(){
                public void run(){
                    try {
                        startGate.await();   // startGate가 0이 대길 기다린다.  현재 1 이다.
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }

                    } catch (InterruptedException ingnored) {}


                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();  // startGate 1->0 으로 감소시킨다. 기다리던 모든 스레드가 동작한다.
        endGate.await();
        long end = System.nanoTime();
        return end -start;
    }
}


