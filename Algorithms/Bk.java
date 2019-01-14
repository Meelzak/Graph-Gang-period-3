package Algorithms;
/*
* Short summary:
* This is the class that uses the Bron-Kerbosch algorithm to determine what the biggest clique is.
* The size of our biggest clique is the lower bound, and the biggest clique is one of our hints.	
*/

import java.util.LinkedList;
import Testing.*;
public class Bk{

    public int doBK(Graph g){
        LinkedList<Dot> list = g.getList();
        int r = doTheBK(new LinkedList<Dot>(), list, new LinkedList<Dot>());
        g.gReset();
        return r;
    }

    /* This method will perform the BK, so it will return the maximum number of connected Dots
     * How it works: 
     * There are three LinkedLists, which will be used again and again, until two of them are totally empty.
     * It checks whether vertices are connected, and what the maximum number of fully connected vertices (so they all connect to each other) is.
     */
    public int doTheBK(LinkedList<Dot> r,LinkedList<Dot> p,LinkedList<Dot> x){
        int max =0;
        if(p.isEmpty() && x.isEmpty()){//if these two are empty, then check if r is bigger than the maximum
            max =Math.max(max, r.size());

        }
        for(int i=0;i<p.size();i++){
            Dot dot = p.get(i);

            LinkedList<Dot> a = (LinkedList <Dot> )r.clone();
            a.add(dot);
            LinkedList<Dot> b = (LinkedList <Dot> )p.clone();
            b.retainAll(dot.giveList());
            LinkedList<Dot> c = (LinkedList <Dot> )x.clone();
            c.retainAll(dot.giveList());
            max = Math.max(doTheBK(a, b, c),max);//will use the doTheBK method again

            p.remove(dot);
            x.add(dot);
        }
        return max; //eventually return the maximum number of fully connected vertices
    }
}   
