package Algorithms;

import Testing.Cleaner;
import Testing.Graph;
import Testing.Parameters;
import Testing.Reader;

import java.util.concurrent.CountDownLatch;


public class Tournament {
    public void run(String graphName){
       /* CountDownLatch latch = new CountDownLatch(2);
        Cleaner cleaner = new Cleaner();
        CleanerAndDevider cleanerAndDevider = new CleanerAndDevider();
        Powell powell = new Powell();
        MC0 mc0 = new MC0();
        NewForce newForce = new NewForce();
        */
        CleanerAndDevider cleanerAndDevider = new CleanerAndDevider();
        Reader reader = new Reader(Parameters.path);
        TournamentPrinter printer = new TournamentPrinter();
        //Graph graph = reader.read(graphName);
        Graph graph2 = reader.read(graphName);

        //graph.sort();
        graph2.sort();
        cleanerAndDevider.cleanAndDivide(graph2);
        int up = graph2.giveUp();
        printer.printUpperBound(up);
        int low = graph2.giveLow();
        if(graph2.getAddToChrom()==0){
            graph2.setUpperBound(up);
            graph2.setLowerBound(low);
        }
        printer.printLowerBound(low);
        int exact = graph2.giveCN();
        printer.printExact(exact);
    /*
        Thread t = new Thread(() -> {
            cleanerAndDevider.cleanAndDivide(graph2);
            int up = graph2.giveUp();
            graph2.setUpperBound(up);
            System.out.println("graph.give()  "+"NEW BEST UPPER BOUND = "+up);
            int low = graph2.giveLow();
            graph2.setLowerBound(low);
            System.out.println("graph.give()  "+"NEW BEST LOWER BOUND = "+low);
            System.out.println("graph.give()  "+"CHROMATIC NUMBER = "+graph2.giveCN());
            latch.countDown();
        });
        t.start();
        Thread t2 = new Thread(() -> {
            int up = powell.doPowell(graph);
            System.out.println("normal  "+"NEW BEST UPPER BOUND = "+up);
            int low = mc0.search(graph);
            System.out.println("normal  "+"NEW BEST LOWER BOUND = "+low);
            System.out.println("normal  "+"CHROMATIC NUMBER = "+newForce.doNewForce(graph,up,low,true));
            latch.countDown();
        });
        t2.start();
        try {
            latch.await();
        }catch (Exception e){

        }
        */
    }

}
