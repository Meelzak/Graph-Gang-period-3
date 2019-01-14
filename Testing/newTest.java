package Testing;


public class newTest {
    public static void main(String[] args){
        String graphName="g18.txt";
        System.out.println("-------------------");
        System.out.println(graphName);
        Reader reader = new Reader(Parameters.path);
        Graph g = reader.read(graphName);

        g.sort();

        System.out.println("dad");
        //System.out.println("Groups: "+bi.doBi(g).size());
        //g.print();
        //cleaner.cleanUp(g);
        //g.print();
       // System.out.println("Vertices: "+g.getVertices());
        //System.out.println("Up: "+powell.doPowell(g));
        //System.out.println("Low: "+bk.doBK(g));
    }
}
