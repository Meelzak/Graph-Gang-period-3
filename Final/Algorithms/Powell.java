package Algorithms;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;

public class Powell{

    //this lets the powell algorithm be executed, calculates how many colours there are and returns that number
    public int doPowell(Graph g){
        //g.sort();
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
