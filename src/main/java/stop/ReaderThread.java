package stop;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by yjlee on 2018-02-05.
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;
    public ReaderThread(Socket socket) throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
    }
    public void interrupt(){
        try {
            socket.close();
        } catch (IOException ignored) {
        }finally {
            super.interrupt();
        }
    }
    public void run(){
        try{
            byte[] buf = new byte[100];
            in.read(buf);
        }catch (IOException e){
            // 스레드 종료
        }
    }

}
