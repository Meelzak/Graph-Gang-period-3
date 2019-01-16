package Algorithms;

import Testing.Dot;
import Testing.Graph;

import java.util.ArrayList;

public class BiPart {
    public ArrayList<ArrayList<Dot>> doBi(Graph graph){
        ArrayList<Dot> list = graph.getList();
        ArrayList<ArrayList<Dot>> groups = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Dot d = list.get(i);
            ArrayList<Dot> l = d.giveList();
            boolean b=true;
            int c =0;
            if(l.size()!=0){
                while (true){
                    if(isPossible(d,c)){
                        break;
                    }
                    c++;
                }
            }
            d.setGroup(c);
            if(c<groups.size()){
                groups.get(c).add(d);
            }else{
                ArrayList<Dot> e = new ArrayList();
                e.add(d);
                groups.add(e);
            }
        }
        return groups;
    }
    private boolean isPossible(Dot d,int group){
        ArrayList<Dot> list = d.giveList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getGroup()==group){
                return false;
            }
        }
        return true;
    }
}
