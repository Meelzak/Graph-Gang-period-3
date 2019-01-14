package Algorithms;


import Testing.Dot;
import Testing.Graph;

import java.util.LinkedList;
public class MC{

    LinkedList<Dot> list;
    int n;
    int maxSize = 0;

    public int search(Graph g){
        list = g.getList();
        n = list.size();
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
            LinkedList<Dot> newList = new LinkedList<>();
            for (Dot w : list){
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
            C.remove(v);
            list.remove(v);
        }

    }

}

