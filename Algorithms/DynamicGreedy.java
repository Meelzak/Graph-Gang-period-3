package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import Testing.*;

/*
 * Okay, what I wanted it to do:
 * -first colour one you choose yourself (so there's a possibility to start from every point in the graph)
 * -after that, sort the ones from the one with the most coloured conn to the one with the least
 * -then colour the one with the most connections again
 * -repeat this until everything is coloured
 * -then check what the highest colour used is
 * -ideally, do this for every vertex (so it starts from every point once, and then check what the lowest possible outcome is
 */

/*BUT:
 * -for some reason it always returns a 2 as an answer when you run it from every single vertex
 * -it gives numbers under the lower bound (like the two)
 *- so there should be a logic error somewhere but I can't find it
 */


public class DynamicGreedy {
	private int upperbound = 0;
	public static ArrayList<Dot> list; 
	
	public DynamicGreedy(Graph g) {
		list = (ArrayList<Dot>) g.getList().clone();
	}
	
	public void doGreedy(int startVertix, Graph g) {//this should be alright?
		int colour = 1; //for the first one
		list.get(startVertix).setContent(colour); //start with setting the colour of the first vertix
		addColoured(startVertix);//add points to the one connected to this one
		for (int i = 0; i < list.size()-1; i++) {//has to be as many times as needed, as the first is already coloured it doesn't need that one
			qSort(); //first quicksort everything, from the one with the most coloured(!) connections to the least coloured
			for (int j = 0; j < list.size(); j++) {//you have to check as many times as possible, this loop will break when done
				if (list.get(j).giveContent() ==0) {//check for the first vertex that is not coloured already
					//colour this vertex with the right colour
					colourVertex(j); //colour the vertex in a colour that is not connected to this one
					addColoured(j);//then add points to the ones connected to this
					break;//so it'll sort again before going to the next one
				}
			}
		}
		this.setGreedy(list);//set the upper bound for this one
	}
	
	public void setGreedy(ArrayList<Dot> list) {//this should be alright? it's the one for powell, but modified
		ArrayList<Integer> myList =new  ArrayList<Integer>(); //make a new list
        for(int i=0;i<list.size();i++){
                myList.add(list.get(i).giveContent());//add to your list- the content set in the list of the connected dots (so the colours)
        }
        int i=1; //start with the colour nr 1
        while(true){
            if(myList.contains(i)){//if the colour is already in the list, add 1
                i++;
            }else{
                break; //else you have found the colour
            }
        }
        upperbound = i; //the upperbound is the max colour you used
    }
	
	public void addColoured(int vertex) {//add points to every vertex connected to your coloured ones
		for (int i = 0; i < list.size(); i++) {//check for every vertex
			if (list.get(vertex).giveList().contains((Dot) list.get(i))) {
				list.get(i).addColouredConnections();
			}
		}
	}
	
	public int getGreedy() {//get the upperbound
		return upperbound;
	}
	
	public void colourVertex(int vertex) {//check if a colour is available, and if so: set it as the colour for your vertex
		boolean possible;
		for (int i = 1; i < list.size() ; i++) {//the colours
			possible = true;//set it to true
			for (int j = 0; j < list.get(vertex).giveList().size(); j++) {
				if (i == ( (Dot) list.get(vertex).giveList().get(j)).giveContent()) {//it'll be false if any of the vertices in this one have the colour
					possible = false;
				}
			}
			if (possible) {
				list.get(vertex).setContent(i); 
				break;
			}
		}
	}
	
	//for now, causes a stackoverflow, haven't figured out (yet) how to fix this
	//this is the stuff that will arrange a list so that it's from the one with the most coloured vertices connected to it to the least coloured vertices connected to it
	public static void qSort(){
        int left=0;
        int right=list.size()-1;
        sortBasedOnMostColoured(left,right);
    }
	
	public static ArrayList<Dot> sortBasedOnMostColoured(int pLeft,int pRight) {
		int left=pLeft;
        int right=pRight;
        int pivot= list.get((left+right)/2).getColouredConnections();
        do{
            while(list.get(left).getColouredConnections() >pivot){
                left++;
            }
            while(list.get(right).getColouredConnections()<pivot){
                right=right-1;
            }
            if(left<=right){
                Collections.swap(list,left,right);
                left++;
                right=right-1;
            }
        }while(left<=right);
            if(pLeft<right){
                list = sortBasedOnMostColoured(pLeft,right);//this is the line that causes a stackoverflow
            }
            if(pRight>left){
                list = sortBasedOnMostColoured(left,pRight);
            }
            return list;
	}
	
}
