package Algorithms;

import Testing.Dot;
import Testing.Graph;

import java.util.LinkedList;

public class BiPart {
    public LinkedList<LinkedList<Dot>> doBi(Graph graph){
        LinkedList<Dot> list = graph.getList();
        LinkedList<LinkedList<Dot>> groups = new LinkedList<>();
        for (int i=0;i<list.size();i++){
            Dot d = list.get(i);
            LinkedList<Dot> l = d.giveList();
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
                LinkedList<Dot> e = new LinkedList();
                e.add(d);
                groups.add(e);
            }
        }
        return groups;
    }
    private boolean isPossible(Dot d,int group){
        LinkedList<Dot> list = d.giveList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getGroup()==group){
                return false;
            }
        }
        return true;
    }
}
