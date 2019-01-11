package Algorithms;


import Testing.Dot;
import Testing.Graph;

import java.util.ArrayList;
public class MC{

    ArrayList<Dot> list;
    int n;
    int maxSize = 0;

    public int search(Graph g){
        list =(ArrayList)g.getList().clone();
        n = list.size();
        ArrayList<Dot> C = new ArrayList<>();
        expand(C,list);
        gReset(list);
        return maxSize;

    }

    public void expand(ArrayList<Dot> C, ArrayList<Dot> list){
        for (int i=list.size()-1;i>=0;i--){
            if (C.size()+list.size()<=maxSize) {
                return;
            }
            Dot v = list.get(i);
            C.add(v);
            ArrayList<Dot> newList = new ArrayList<>();
            for (Dot w : list){
                if (v.giveList().contains(w)){
                    newList.add(w);
                }
            }
            if (newList.isEmpty()&&C.size()>maxSize){
                saveSolution(C);
            }
            if(!newList.isEmpty()){
                expand(C,newList);
            }
            C.remove((Dot)v);
            list.remove((Dot)v);
        }

    }
    public void saveSolution(ArrayList<Dot> C){
        maxSize = C.size();
    }

    //set the content of every entry (a Dot) of the ArrayList to zero
    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }
}

