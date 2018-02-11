package barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yjlee on 2018-01-21.
 */
public class CellularAutomata {
    final Board mainBorard;
    final CyclicBarrier barrier;
    final Worker[] workers;

    public CellularAutomata(Board board){
        this.mainBorard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                mainBorard.commitNewValues();
            }
        });
        this.workers = new Worker[count];

        for(int i=0;i<count;i++){
            workers[i] = new Worker(new Board(""));

        }
    }

    public  void start(){
        for(int i=0;i<workers.length;i++){
            new Thread(workers[i]).start();
        }
    }


    class Worker implements Runnable{
        private final Board board;

        public Worker(Board board){
            this.board = board;
        }

        public void run(){
            while (!board.hasConverged()){
                for(int x=0;x<board.getMaxX();x++){
                    for(int y=0;y<board.getMaxY();y++)
                        board.setNewValue(x,y,computeValue(x,y));
                }

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        }

        private int computeValue(int x, int y) {
            return -1;

        }


    }

}

