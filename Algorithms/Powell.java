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
import java.util.Collections;

import Testing.*;
public class Powell{
    
    //this lets the powell algorithm be executed, calculates how many colours there are and returns that number
    public int doPowell(Graph g){
        ArrayList<Dot> list = (ArrayList)g.getList().clone();
        if (g.getEdges()==0){
            return 1;
        }
        qSort(list);
        int answer=0;
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(cNumber(list.get(i).giveList()));
            answer = Math.max(list.get(i).giveContent(), answer);
        }
        gReset(list);
        return answer;
    }
    public int doPowellTest(Graph g,ArrayList<Dot> myList){
        ArrayList<Dot> list = (ArrayList)g.getList().clone();
        if (g.getEdges()==0){
            return 1;
        }
        //qSort(list);
        if(!myList.isEmpty()){
            int pos = list.indexOf(myList.get(0));
            Collections.swap(list,0,pos);
        }
        int answer=0;
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(cNumber(list.get(i).giveList()));
            answer = Math.max(list.get(i).giveContent(), answer);
        }
        gReset(list);
        return answer;
    }
    //colours every vertix that is not connected to one with the same colour
    public int cNumber(ArrayList<Dot> dList){
        //Needs the list of connected dots
        ArrayList<Integer> list =new  ArrayList<Integer>();
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
    //quicksorts the arraylist, uses the method sort:
    public static void qSort(ArrayList<Dot> array){
        int left=0;
        int right=array.size()-1;
        sort(array,left,right);
    }
    //sorts the vertices from the one with the most connections to the one with the least connections:
    public static ArrayList<Dot> sort(ArrayList<Dot> array,int pLeft,int pRight){
        int left=pLeft;
        int right=pRight;
        int pivot= array.get((left+right)/2).giveList().size();
        do{
            while(array.get(left).giveList().size()>pivot){
                left++;
            }
            while(array.get(right).giveList().size()<pivot){
                right=right-1;
            }
            if(left<=right){
                Collections.swap(array,left,right);
                left++;
                right=right-1;
            }
        }while(left<=right);
            if(pLeft<right){
                array = sort(array,pLeft,right);
            }
            if(pRight>left){
                array = sort(array,left,pRight);
            }
            return array;
    }
    //resets the content (which are the colours) from the list
    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }
}
