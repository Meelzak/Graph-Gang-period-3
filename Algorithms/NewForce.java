package Algorithms;

import java.util.ArrayList;
import Testing.*;
public class NewForce{

    public int chromaticNumber;

    //Empty constructor
    public NewForce() {
    }

    //Returns the chromatic number
    //Gets called in chromatic manager
    //Returns lowerbound if lowerbound == upperbound
    public int doNewForce(Graph graph, int upperBound, int lowerBound){
        if(lowerBound==upperBound){
            return lowerBound;
        }
        upperBound--;
        //dF is the boolean function which checks whether two adjacent vertices are same color or no
        while(dF(graph.getList(), upperBound, 0)){
            upperBound--;
            gReset(graph.getList());
        }
        doIt(graph,0,upperBound+1);

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
    //clear the values for next dot
    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }

    //Manually set the chromatic number
    public void doIt(Graph graph,int startDot,int smallesChrom){
        this.chromaticNumber = smallesChrom;
    }
    //chromatic number getter
    public int getChromaticNumber() {
        return chromaticNumber;
    }
}
