package Testing;

import Algorithms.BiPart;
import Algorithms.Bk;
import Algorithms.NewForce;
import Algorithms.Powell;

public class newTest {
    public static void main(String[] args){
        String graphName="gTest.txt";
        System.out.println("-------------------");
        System.out.println(graphName);
        Reader reader = new Reader(Parameters.path);

        Bk bk = new Bk();
        Powell powell = new Powell();
        NewForce newForce = new NewForce();

        BiPart bi = new BiPart();
        Graph g = reader.read(graphName);

        System.out.println("Groups: "+bi.doBi(g).size());
        //g.print();
        //cleaner.cleanUp(g);
        //g.print();
       // System.out.println("Vertices: "+g.getVertices());
        //System.out.println("Up: "+powell.doPowell(g));
        //System.out.println("Low: "+bk.doBK(g));
    }
}
