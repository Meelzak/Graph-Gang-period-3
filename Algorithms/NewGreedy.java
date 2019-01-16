package Algorithms;

import java.util.ArrayList;
import java.util.ArrayList;

import Testing.*;

public class NewGreedy {
	// a dynamic greedy, using a DSATUR algorithm - basically choosing one based on how many adjacent vertices are colored
	
	private int upperbound = 0;
	
	public void doGreedy(int startVertix, Graph g) {
		ArrayList<Dot> list = (ArrayList<Dot>) g.getList().clone();
		setUpper(0);
		//to have a starting point:
		list.get(startVertix).setContent(1);
		setColoredConnections(startVertix, list);
		
		//to determine what the one with the most colored connections is, you can just use the Math.max method, quicksorting is slower
		int maxColoured = -1;
		int maximum = -1;
		
		//it has to run n - 1 times, to make sure every vertex is coloured
		for (int timesToRun = 0; timesToRun < list.size() - 1; timesToRun++) {
			//run through all the vertices to determine which (uncolored) one has the most colored connections:
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).giveContent() == 0) {
					if (Math.max(maxColoured, list.get(i).getColouredConnections()) >= maxColoured) {
						//set the one with the most colored connections as the maximum
						maxColoured = list.get(i).getColouredConnections();
						maximum = i;
					}
				}
			}
			//color the one with the most colored connections
			setColor(maximum, list);
		}
		//count the number of colours used and after that, set the upperbound:
		countColours(list);
		gReset(list);
	}
	
	public void setColor(int vertix, ArrayList<Dot> list) {
		//check for every colour
		for (int i = 1; i < list.size()+ 1; i++) {
			//assume the color is not there, if it is so, disprove this statement
			boolean colorPossible = true;
			for (int j = 0; j < list.get(vertix).giveList().size(); j++) {
				//if the color is used in any of the vertices connected to this vertex, it can not be:
				if (i == ((Dot) list.get(vertix).giveList().get(j)).giveContent()) {
					colorPossible = false;
					break;
				}
			}
			//if the color is possible, then color the dot with the chosen color and add a colored connection to every dot connected to this vertex:
			if (colorPossible) {
				list.get(vertix).setContent(i);
				setColoredConnections(vertix, list);
				break;
			}
		}
	}
	
	//count the colors used in this coloring of the graph:
	public void countColours(ArrayList<Dot> list) {
		//make a new ArrayList for the colors you used
		ArrayList<Integer> colorList =new  ArrayList<Integer>();
		
        for(int i=0;i<list.size();i++){
                colorList.add(list.get(i).giveContent());
        }
        
        //check, beginning from the lowest, whether the colors are inside, if not, then that's the amount
        int currentAmount =1; 
        while(true){
            if(colorList.contains(currentAmount)){
                currentAmount++;
            }else{
                break;
            }
        }
		setUpper(currentAmount);
	}
	
	//a method that adds one for every colored connection a vertex has, so it is easy to rank them all from highest to lowest
	public void setColoredConnections(int vertix, ArrayList<Dot> list) {
		for (int i = 0; i < list.get(vertix).giveList().size(); i++) {
			((Dot) list.get(vertix).giveList().get(i)).addColouredConnections();
		}
	}
	
	//setter and getter:
	public void setUpper(int newUpper) {
		upperbound = newUpper;
	}
	
	public int getUpper() {
		return upperbound;
	}
	
	public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
            list.get(i).resetColouredConnections();
        }
    }
}
