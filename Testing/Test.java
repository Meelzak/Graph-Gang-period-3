package Testing;
import Algorithms.*;

import java.util.ArrayList;
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
        IsConnected connected = new IsConnected();
        Cleaner cleaner = new Cleaner();
        Graph g = reader.read(graphName);
        double density = ((double)g.getEdges()*2)/(g.getVertices()-1)/g.getVertices()*100;
        System.out.println("Density: "+density+" percent");

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
        /*
            start1 = System.currentTimeMillis();
            boolean connect = connected.isConnected(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println(" Is Connected: "+connect);

        */

            start1 = System.currentTimeMillis();
            cleaner.cleanUp(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println(" For Cleaning");

            start1 = System.currentTimeMillis();
            boolean connect2 = connected.isConnected(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println(" Is Connected: "+connect2);
            /*
            start1 = System.currentTimeMillis();
            int up1 = powell.doPowell(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   upper: " + up1);
            /*
            start1 = System.currentTimeMillis();
            int low1 = bk.doBK(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   lower: " + low1);

            start1 = System.currentTimeMillis();
            int low2 = MC.search(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   lower MC: " + low2);
            */
            /*
            start1 = System.currentTimeMillis();
            int low3 = MC0.search(g);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   lower MC0: " + low3);
            /*
                        ArrayList<ArrayList<Dot>> biggest = MC0.searchList(g);

            start1 = System.currentTimeMillis();
            int up2 = powell.doPowellTest(g,biggest);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   upper2: " + up2);
               */
            /*start1 = System.currentTimeMillis();
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            if (detector.detectCircle(g)) {
            	System.out.println("   Chromatic Number: " + detector.getChromatic());
            }
            else {
            	System.out.println("   No circle detected.");
            }
            */
        /*
            start1 = System.currentTimeMillis();
            int exact1 = newForce.doNewForce(g, up1, low3);
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   exact: " + exact1);
            /*
            start1 = System.currentTimeMillis();
            int groups = bi.doBi(g).size();
            System.out.print(graphName+"  "+"time: " + (System.currentTimeMillis() - start1));
            System.out.println("   groups: " + groups);
            */
            /*
            ArrayList<ArrayList<Dot>> biggest = MC0.searchList(g);
            int count=0;
            for(int i=0;i<biggest.size();i++){
                count=count+biggest.get(i).size();
            }
            System.out.println("TEST: "+count);


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
