package Algorithms;


import Testing.Dot;
import Testing.Graph;

import java.util.ArrayList;
import java.util.ArrayList;

public class MC0{

    ArrayList<Dot> list;
    int maxSize;
    ArrayList<ArrayList<Dot>> maxList;
    public MC0(){
        maxList = new ArrayList<>();
    }
    public int search(Graph g){
        list = g.getList();
        ArrayList<Dot> C = new ArrayList<>();
        expand(C,list);
        g.gReset();
        return maxSize;
    }
    public void expand(ArrayList<Dot> C, ArrayList<Dot> list){
        for (int i=list.size()-1;i>=0;i--){
            if (C.size()+list.size()<=maxSize) {
                return;
            }
            Dot v = list.get(i);
            C.add(v);
            ArrayList<Dot> newList = new ArrayList();
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

