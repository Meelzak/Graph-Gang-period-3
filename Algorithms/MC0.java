package Algorithms;


import Testing.Dot;
import Testing.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class MC0{

    LinkedList<Dot> list;
    int maxSize;
    ArrayList<ArrayList<Dot>> maxList;
    public MC0(){
        maxList = new ArrayList<>();
    }
    public int search(Graph g){
        list = g.getList();
        LinkedList<Dot> C = new LinkedList<>();
        expand(C,list);
        g.gReset();
        return maxSize;
    }
    public void expand(LinkedList<Dot> C, LinkedList<Dot> list){
        for (int i=list.size()-1;i>=0;i--){
            if (C.size()+list.size()<=maxSize) {
                return;
            }
            Dot v = list.get(i);
            C.add(v);
            LinkedList<Dot> newList = new LinkedList();
            for (int j =0;j<=i;j++){
                Dot w = list.get(j);
                if (v.giveList().contains(w)){
                    newList.add(w);
                }
            }
            if (newList.isEmpty()&&C.size()>maxSize){
                maxSize = C.size();
            }
            if(!newList.isEmpty()){
                expand(C,newList);
            }
            C.remove(C.size()-1);
            list.remove(i);
        }

    }

}

