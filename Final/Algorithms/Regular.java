package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
class DoubleDot extends Object{
    public Dot d1;
    public Dot d2;
    public DoubleDot(Dot d1,Dot d2){
        this.d1=d1;
        this.d2=d2;
    }
    public boolean equals(DoubleDot doubleDot){
        if(doubleDot.d1.equals(d1) && doubleDot.d2.equals(d2)){
            return true;
        }
        if(doubleDot.d2.equals(d1) && doubleDot.d1.equals(d2)){
            return true;
        }
        return false;
    }
}
public class Regular {
    public boolean isRegular(Graph g){
        ArrayList<Dot> list = g.getList();
        int k = list.get(0).giveList().size();
        for(int i=1;i<list.size();i++){
            if(k!=list.get(i).giveList().size()){
                return false;
            }
        }
        return true;
    }
    public ArrayList<Dot> findBreakPoints(Graph graph){
        graph.gReset();
        ArrayList<Dot> breakPoints = new ArrayList<>();
        ArrayList<Dot> list = graph.getList();
        if(!isConnected(graph)){
            return breakPoints;
        }
        for(int i=0;i<list.size();i++){
            graph.gReset();
            Dot d = list.get(i);
            d.setIgnoreMe(true);
            if(!isConnected(graph)){
                breakPoints.add(d);
            }
            d.setIgnoreMe(false);
        }
        return breakPoints;
    }
    public void breakUp(Graph graph){
        ArrayList<Dot> list = findBreakPoints(graph);
        ArrayList<DoubleDot> doubleDots = new ArrayList<>();

        if(!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                Dot d = list.get(i);
                ArrayList<Dot> list2 = d.giveList();
                for(int x=0;x<list2.size();x++){
                    Dot connectedDot = list2.get(x);
                    d.giveList().remove(x);
                    int indexD = connectedDot.giveList().indexOf(d);
                    connectedDot.giveList().remove(indexD);
                    if(!isConnected(graph)) {

                        DoubleDot doubleDot = new DoubleDot(d,connectedDot);
                        if(doubleDots.isEmpty()){
                            doubleDots.add(doubleDot);
                        }else{
                            boolean add=true;
                            for(int z=0;z<doubleDots.size();z++){
                                if(doubleDots.get(z).equals(doubleDot)){
                                    add=false;
                                }
                            }
                            if(add){
                                doubleDots.add(doubleDot);
                            }
                        }


                    }
                    d.giveList().add(x,connectedDot);
                    connectedDot.giveList().add(indexD,d);
                }

            }
            System.out.println("Real number to Remove "+doubleDots.size());
            for(int i=0;i<doubleDots.size();i++){

                Dot d1 = doubleDots.get(i).d1;
                Dot d2 = doubleDots.get(i).d2;
                d1.giveList().remove(d2);
                d2.giveList().remove(d1);
                graph.setEdgesMinus();


            }
        }

    }

    public boolean isConnected(Graph graph){
        graph.gReset();
        ArrayList<Dot> list = graph.getList();
        Dot first = list.get(0);
        if(first.getIgnoreMe()){
            test(list.get(1));
        }else {
            test(list.get(0));
        }
        for (int i=0;i<list.size();i++){
            if(!list.get(i).getIsConnected()){
                return false;
            }
        }
        return true;
    }
    public void test(Dot dot){
        if(dot.getIsConnected()){
            return;
        }
        if(dot.getIgnoreMe()){
            dot.setIsConnected(true);
            return;
        }
        dot.setIsConnected(true);
        for (int i=0;i<dot.giveList().size();i++){
            test(dot.giveList().get(i));
        }
    }

}
