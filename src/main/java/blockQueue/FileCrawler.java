package blockQueue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yjlee on 2018-01-21.
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileBlockingQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileBlockingQueue, FileFilter fileFilter, File root) {
        this.fileBlockingQueue = fileBlockingQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if(entries != null){
            for(File entry : entries){
                if(entry.isDirectory())
                    crawl(root);
                else if(!alreadyIndexed(entry)){
                    fileBlockingQueue.put(entry);
                }
            }

        }

    }

    private boolean alreadyIndexed(File entry) {
        return false;
    }

    @Override
    public void run() {
        try{
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}



