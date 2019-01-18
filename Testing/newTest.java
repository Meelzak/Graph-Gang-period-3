package Testing;

import Algorithms.Tournament;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class newTest {
    public static void main(String[] args){
        String graphName="g20.txt";
        System.out.println(graphName);
        long time = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(1);
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(Parameters.maxTimePerGraph);
                latch.countDown();

            }catch (InterruptedException e){

            }
        });
        t2.start();
        Thread t = new Thread(() -> {
            Tournament tournament = new Tournament();
            tournament.run(graphName);
            latch.countDown();
        });
        t.start();
        try {
            latch.await();
            System.out.println("Time in ms: "+(System.currentTimeMillis()-time));
            System.out.println("-------------------");
            System.exit(0);
        }catch (InterruptedException e){
            System.exit(0);
        }
    }
}
