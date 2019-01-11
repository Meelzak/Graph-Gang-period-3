package Testing;
import Algorithms.*;

import java.util.concurrent.*;

public class Test {
    public void testing(String graphName) {


        System.out.println("-------------------");
        System.out.println(graphName);
        Reader reader = new Reader(Parameters.path);

        Bk bk = new Bk();
        Powell powell = new Powell();
        MC MC = new MC();
        MC0 MC0 = new MC0();
        //CircleDetector detector = new CircleDetector();
        NewForce newForce = new NewForce();
        BiPart bi = new BiPart();
        Cleaner cleaner = new Cleaner();
        Graph g = reader.read(graphName);
        CountDownLatch latch = new CountDownLatch(1);
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(Parameters.maxTimePerGraph);
                System.out.println("-------------------");
                latch.countDown();

            }catch (InterruptedException e){

            }
        });
        t2.start();
        Thread t = new Thread(() -> {
            long start1=0;

            start1 = System.currentTimeMillis();
            cleaner.cleanUp(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println(" For Cleaning");
            start1 = System.currentTimeMillis();
            int up1 = powell.doPowell(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   upper: " + up1);

            start1 = System.currentTimeMillis();
            int low1 = bk.doBK(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   lower: " + low1);

            start1 = System.currentTimeMillis();
            int low2 = MC.search(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("lower MC: " + low2);

            start1 = System.currentTimeMillis();
            int low3 = MC0.search(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("lower MC0: " + low3);
            
            /*start1 = System.currentTimeMillis();
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            if (detector.detectCircle(g)) {
            	System.out.println("   Chromatic Number: " + detector.getChromatic());
            }
            else {
            	System.out.println("   No circle detected.");
            }*/

            start1 = System.currentTimeMillis();
            int exact1 = newForce.doNewForce(g, up1, low1);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   exact: " + exact1);
            /*
            start1 = System.currentTimeMillis();
            int groups = bi.doBi(g).size();
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   groups: " + groups);
            */

            System.out.println("-------------------");
            latch.countDown();
        });
        t.start();
        try {
            latch.await();
            t.interrupt();
            t2.interrupt();
        }catch (InterruptedException e){

        }
    }

}
