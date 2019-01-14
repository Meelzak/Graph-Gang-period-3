package Algorithms;

import Testing.Dot;
import Testing.Graph;

import java.util.LinkedList;

public class IsConnected {
    public boolean isConnected(Graph g){
        LinkedList<Dot> list  = g.getList();
       /* int v = g.getVertices()-1;
        for(int i=0;i<list.size();i++){
            Dot dot = list.get(i);
            if(dot.giveList().size()==v){
                System.out.println("WOW");
            }

        }
       */

        if(!list.isEmpty()){
            test(g.getList().get(0));
            for (int i=0;i<list.size();i++){
                if(list.get(i).isConnected==false){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private void test(Dot d){
        if(d.isConnected){
            return;
        }
        d.isConnected=true;
        LinkedList<Dot> l = d.giveList();
        for(int i=0;i<l.size();i++){
            test(l.get(i));
        }
    }
}
