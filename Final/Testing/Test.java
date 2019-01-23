package Testing;

import Algorithms.*;
import Algorithms.Generator;

public class Test {
    public static void main(String[] args){
        Reader reader = new Reader(Parameters.path);
        Cleaner cleanerAndDevider = new Cleaner();
        Regular regular = new Regular();
        Generator generator = new Generator();
        for(int i=1;i<21;i++){

            if(i!=5){
                String graphName="g"+i+".txt";
                System.out.println(graphName);
                Graph graph = reader.read(graphName);
                cleanerAndDevider.cleanAndDivide(graph);
                regular.breakUp(graph);
                generator.generateGephiFile(graph,graphName+"Broke");
            }

        }
    }
}
