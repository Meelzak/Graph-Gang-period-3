package Testing;

import Algorithms.CleanerAndDevider;
import Algorithms.Generator;
import Algorithms.Powell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class myTester {
    public static void main(String[] args) {
        test2();

    }
    public static void test1(){
        Reader reader = new Reader(Parameters.path);
        Generator generator = new Generator();
        CleanerAndDevider cleanerAndDevider = new CleanerAndDevider();
        for(int i=1;i<21;i++){
            String graphName="g"+i+".txt";
            Graph graph = reader.read(graphName);
            cleanerAndDevider.cleanAndDivide(graph);
            generator.generateGephiFile(graph,graphName+"_CLEANED");
        }
    }
    public static void test2(){
        String graphName = "g3.txt";
        Reader reader = new Reader(Parameters.path);
        Generator generator = new Generator();
        Graph graph = reader.read(graphName);
        CleanerAndDevider cleanerAndDevider = new CleanerAndDevider();
        cleanerAndDevider.cleanAndDivide(graph);
        ArrayList<Graph> gList = graph.giveLeafList();
        if(gList.size()>1){
            System.out.println(gList.size());
            Graph g = gList.remove(0);
            g.mergeGraphs(gList);
            generator.generateGephiFile(g,graphName+"_CLEANED");
        }

    }
}
