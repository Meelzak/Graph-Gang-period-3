package Algorithms;

import Testing.Dot;
import Testing.Graph;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;

public class Divider {
    public boolean divide(Graph g){
        ArrayList<Dot> oldList = g.getList();
        if(oldList.isEmpty()){
            return false;
        }
        while (!oldList.isEmpty()){
            ArrayList<Dot> newSubList = new ArrayList<>();
            test(oldList.get(0),oldList,newSubList);

            if(newSubList.size()==g.getVertices()){
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
        if(d.isConnected){
            return;
        }
        d.isConnected=true;
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
            list.get(i).isConnected=false;
        }

    }
}
