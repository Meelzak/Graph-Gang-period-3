package Algorithms;
/*
* Short summary:
* Uses the Welsh-Powell algorithm to calculate the upperbound of the graph.
* Which is: it creates a list and then sorts it so the most connected vertix is the first entry, and the least connected vertix is last.
* Firstly, it colours the first entry a particular colour, the goes down the list and colours every entry that is not connected to an entry with this colour, that colour.
* When every vertix is connected to one with that colour, it starts at the top again with a new colour, and repeats this until every dot is coloured.
* Then it returns how many colours it used.
*/


import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;

import Testing.*;
public class Powell{
    
    //this lets the powell algorithm be executed, calculates how many colours there are and returns that number
    public int doPowell(Graph g){
        g.sort();
        ArrayList<Dot> list = g.getList();
        if (g.getEdges()==0){
            return 1;
        }
        int answer=0;
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(cNumber(list.get(i).giveList()));
            answer = Math.max(list.get(i).giveContent(), answer);
        }
        g.gReset();
        return answer;
    }
    //colours every vertix that is not connected to one with the same colour
    public int cNumber(ArrayList<Dot> dList){
        //Needs the list of connected dots
        ArrayList<Integer> list =new ArrayList<>();
        for(int i=0;i<dList.size();i++){
                list.add(dList.get(i).giveContent());
        }
        int i=1;
        while(true){
            if(list.contains(i)){
                i++;
            }else{
                break;
            }
        }
        return i;
    }

}
