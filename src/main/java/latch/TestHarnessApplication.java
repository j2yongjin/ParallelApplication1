package latch;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.midi.Soundbank;

/**
 * Created by yjlee on 2018-01-21.
 */
public class TestHarnessApplication {

    public static void main(String[] args) throws InterruptedException {

        TestHarness testHarness = new TestHarness();

        Integer nThread = 5;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(" runnble ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        System.out.println(" result : " +
            testHarness.timeTasks(nThread,runnable)
        );

    }


}
