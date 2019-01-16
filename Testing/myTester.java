package Testing;

import Algorithms.Powell;
import Algorithms.Tournament;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class myTester {
    public static void main(String[] args){


        String graphName="g18.txt";
        System.out.println(graphName);
        Reader reader = new Reader(Parameters.path);
        Graph g = reader.read(graphName);

        Powell powell = new Powell();
        Cleaner cleaner = new Cleaner();
        cleaner.cleanUp(g);
        int up = g.giveUp();
        int low = g.giveLow();
        System.out.println("UP: "+up+" low: "+low);
        int ex = g.giveCN();
        System.out.println("CN: "+ex);
    }
}
