package Algorithms;

import Testing.Dot;
import Testing.Graph;

import java.util.ArrayList;

public class IsConnected {
    public boolean isConnected(Graph g){
        ArrayList<Dot> list  = g.getList();
        int v = g.getVertices()-1;
        for(int i=0;i<list.size();i++){
            Dot dot = list.get(i);
            if(dot.giveList().size()==v){
                System.out.println("WOW");
            }

        }
        if(!list.isEmpty()){
            test((Dot) g.getList().get(0));
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
        ArrayList<Dot> l = d.giveList();
        for(int i=0;i<l.size();i++){
            test(l.get(i));
        }
    }
}
