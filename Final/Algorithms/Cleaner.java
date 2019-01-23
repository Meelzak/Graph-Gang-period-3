package Algorithms;
import java.util.ArrayList;

public class Cleaner {
    public void cleanAndDivide(Graph g){
        cleanUp(g);
        divide(g);
        if(g.hasSub()){
            ArrayList<Graph> gList = g.getSub();
            for(int i=0;i< gList.size();i++){
                cleanAndDivide(gList.get(i));
            }
        }
    }
    public boolean cleanUp(Graph graph) {
        graph.gReset();
        int min = 0;
        int startVertices = graph.getVertices();
        if (graph.getEdges() == 0) {
            min = 1;
        }
        boolean cleaned = false;
        int counter = 0;
        ArrayList<Dot> list = graph.getList();
        while (!cleaned) {
            cleaned = true;
            for (int i = 0; i < list.size(); i++) {
                Dot d = list.get(i);
                if (d.giveList().size() == 1) {
                    d.killMe();
                    list.remove(i);
                    graph.setEdgesMinus();
                    graph.setVerticesMinus();
                    cleaned = false;
                    counter++;
                } else if (list.get(i).giveList().size() == 0) {
                    list.remove(i);
                    graph.setVerticesMinus();
                    cleaned = false;
                    counter++;
                }
                else if(d.giveList().size()==graph.getVertices()-1){
                    int minus = d.giveList().size();
                    d.killMe();
                    list.remove(i);
                    graph.setVerticesMinus();
                    graph.setEdgesMinus(minus);
                    cleaned = false;
                    graph.addAddToChrom();
                    graph.setLowerBound(-1);
                    graph.setUpperBound(-1);
                    counter++;
                    //dem Graph das Sagen
                }
            }
        }
        if (graph.getVertices() <= 0) {
            if (min == 1) {
                graph.getList().add(new Dot());
                graph.setVerticesPlus();
            }
            if (min == 0) {
                Dot d1 = new Dot();
                Dot d2 = new Dot();
                d1.setConnection(d2);
                d2.setConnection(d1);
                list.add(d1);
                list.add(d2);
                graph.setVerticesPlus();
                graph.setVerticesPlus();
                graph.setEdgesPlus();
            }
        }
        graph.setList(list);
        return counter>0;

    }
    public boolean divide(Graph g){
        ArrayList<Dot> oldList = g.getList();
        if(oldList.isEmpty()){
            reset(g);
            return false;
        }
        while (!oldList.isEmpty()){
            ArrayList<Dot> newSubList = new ArrayList<>();
            test(oldList.get(0),oldList,newSubList);

            if(newSubList.size()==g.getVertices()){
                reset(g);
                return false;
            }
            int edges=0;
            for (int i=0;i<newSubList.size();i++){
                edges=edges+newSubList.get(i).giveList().size();
            }
            Graph newSubGraph = new Graph(newSubList.size(),edges,newSubList);
            g.addSub(newSubGraph);
            reset(g);
        }
        return true;
    }

    private void test(Dot d,ArrayList<Dot> mother,ArrayList<Dot> newList){
        if(d.getIsConnected()){
            return;
        }
        d.setIsConnected(true);
        mother.remove(d);
        newList.add(d);
        ArrayList<Dot> l = d.giveList();
        for(int i=0;i<l.size();i++){
            test(l.get(i),mother,newList);
        }
    }
    private void reset(Graph g){
        ArrayList<Dot> list = g.getList();
        for(int i=0;i<list.size();i++){
            list.get(i).setIsConnected(false);
        }

    }
}
