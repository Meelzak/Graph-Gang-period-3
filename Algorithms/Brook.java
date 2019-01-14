package Algorithms;

import Testing.Dot;
import Testing.Graph;

import java.util.ArrayList;

public class Brook {
    public int doBrook(Graph g){
        ArrayList<Dot> list = g.getList();
        int max=0;
        for(int i=0;i<list.size();i++){
            int x=list.get(i).giveList().size();
            if(x>max){
                max=x;
            }
        }
        return max;
    }
}
