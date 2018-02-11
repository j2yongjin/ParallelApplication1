package stop;

/**
 * Created by yjlee on 2018-02-01.
 */
public class PrimeGeneratorApplication {
    public static void main(String[] args){

        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try{

            Thread.sleep(1);
        } catch (InterruptedExceptionApplication e) {
            e.printStackTrace();
        } finally {
            primeGenerator.cancel();
        }
        primeGenerator.get();
    }
}
