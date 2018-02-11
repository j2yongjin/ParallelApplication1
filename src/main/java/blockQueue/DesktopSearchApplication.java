package blockQueue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yjlee on 2018-01-21.
 */
public class DesktopSearchApplication {

    public static void main(String[] args){
        File[] roots = null;
        startIndexing(roots);
    }

    public static void startIndexing(File[] roots){

        BlockingQueue<File> queue = new LinkedBlockingQueue<>(100);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        for(File root:roots)
            new Thread(new FileCrawler(queue,fileFilter,root)).start();

        for(int i=0;i<10;i++){
            new Thread(new Indexer(queue)).start();
        }

    }

}
