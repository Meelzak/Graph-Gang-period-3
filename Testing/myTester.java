package Testing;

import Algorithms.CleanerAndDevider;
import Algorithms.Powell;
import Algorithms.Tournament;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class myTester {
    public static void main(String[] args){

        Generator generator = new Generator();
        Graph g = generator.GenerateGraphDensity(100,99);

        double density = ((double)g.getEdges()*2)/(g.getVertices()-1)/g.getVertices()*100;
        System.out.println("Density: "+density+" percent");

        Powell powell = new Powell();
        Cleaner cleaner = new Cleaner();
        CleanerAndDevider cleanerAndDevider = new CleanerAndDevider();
        cleanerAndDevider.cleanAndDivide(g);
        System.out.println("Finished");
        int up = g.giveUp();
        int low = g.giveLow();
        System.out.println("UP: "+up+" low: "+low);
        int ex = g.giveCN();
        System.out.println("CN: "+ex);
    }
}
