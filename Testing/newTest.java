package Testing;

import Algorithms.*;

public class newTest {
    public static void main(String[] args){
        String graphName="g18.txt";
        System.out.println("-------------------");
        System.out.println(graphName);
        Reader reader = new Reader(Parameters.path);
        Graph g = reader.read(graphName);

        IsConnected isConnected = new IsConnected();
        Bk bk = new Bk();
        Powell powell = new Powell();
        NewForce newForce = new NewForce();
        BiPart bi = new BiPart();
        Brook brook = new Brook();

        boolean b=isConnected.isConnected(g);
        System.out.println("Connected: "+b);
        if(b==true){
            System.out.println("Brook: "+brook.doBrook(g));
        }

        //System.out.println("Groups: "+bi.doBi(g).size());
        //g.print();
        //cleaner.cleanUp(g);
        //g.print();
       // System.out.println("Vertices: "+g.getVertices());
        //System.out.println("Up: "+powell.doPowell(g));
        //System.out.println("Low: "+bk.doBK(g));
    }
}
