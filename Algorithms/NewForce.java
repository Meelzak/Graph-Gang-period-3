package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

import Testing.*;
public class NewForce{

    public int chromaticNumber;
    //Returns the chromatic number
    //Gets called in chromatic manager
    //Returns lowerbound if lowerbound == upperbound
    public int doNewForce(Graph graph, int upperBound, int lowerBound,boolean println){
        if(lowerBound==upperBound){
            return lowerBound;
        }
        upperBound--;
        ArrayList<Dot> list = graph.getList();
        //dF is the boolean function which checks whether two adjacent vertices are same color or no
        while(dF(list, upperBound, 0)){
            if(println){
                System.out.println("NEW BEST UPPER BOUND = "+upperBound);
            }
            if(upperBound==lowerBound){
                upperBound--;
                break;
            }
            upperBound--;
            graph.gReset();
        }
        return upperBound+1;
        }

    public boolean dF(ArrayList<Dot> list,int color,int dotPosition){
        boolean allColored=true;
        for(int i=0;i<list.size();i++){ //Add a size and getSize to graph too.
            if(list.get(i).giveContent()==0){
                allColored=false;
                break;
            }
        }
        //If none of the adjacent vertices has the same value move to next one and increment the vallue by 1
        if(allColored){
            return true;
        }
        for(int i=1;i<=color;i++){
            if(cNumberTrue(list.get(dotPosition).giveList(), i)){
                list.get(dotPosition).setContent(i);
                int next=dotPosition + 1;
                if(next==list.size()||dF(list, color, next)){
                    return true;
                }
                list.get(dotPosition).setContent(0);
            }
        }
        return false;
    }
    //Check is the chromatic number is true or not
    public boolean cNumberTrue(ArrayList<Dot> list,int color){
        for(int i=0;i<list.size();i++){
            if(list.get(i).giveContent()==color){
                return false;
            }
        }
        return true;
    }
}
