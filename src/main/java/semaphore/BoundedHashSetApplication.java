package semaphore;

/**
 * Created by yjlee on 2018-01-21.
 */
public class BoundedHashSetApplication {

    public static void main(String args[]) throws InterruptedException {

        BoundedHashSet<String> boundedHashSet = new BoundedHashSet<>(5);
        boundedHashSet.add("1");
        boundedHashSet.add("2");
        boundedHashSet.add("3");
        boundedHashSet.add("4");
        boundedHashSet.add("5");

//        boundedHashSet.remove("5");

        boundedHashSet.add("6");

        System.out.println(" result success ");

    }
}
