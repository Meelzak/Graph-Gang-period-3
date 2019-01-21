package Algorithms;

import java.util.ArrayList;
import Testing.*;

public class Connected {
	private ArrayList<Dot> listOfVertices = new ArrayList<Dot>();
	private boolean connected = true;
	
	public boolean isConnected(Graph g) {
		ArrayList<Dot> graphList = g.getList();
		
		goDeeper(graphList.get(0), graphList);
		
		for (int i = 0; i < graphList.size(); i++) {
			if (!listOfVertices.contains(graphList.get(i))){
				connected = false;
				break;
			}
		}
		boolean answer = getConnected();
		resetAll();
		return answer;
	}
	
	public void goDeeper(Dot dot, ArrayList<Dot> graphList) {
		if (!seenEverything(dot)){
			for (int i = 0; i < dot.giveList().size(); i++) {
				if (!dot.giveList().get(i).getSeen()) {
					dot.giveList().get(i).haveSeen();
					addToList(dot.giveList().get(i));
					goDeeper(dot.giveList().get(i), graphList);
				}
			}
		}
	}
	
	public boolean seenEverything (Dot dot) {
		for (int index = 0; index < dot.giveList().size(); index++) {
			if (!dot.giveList().get(index).getSeen()) {
				return false;
			}
		}
		return true;
	}		
	
	public void addToList(Dot dot) {
		listOfVertices.add(dot);
	}
	
	public void resetAll() {
		listOfVertices = new ArrayList<Dot>();
		connected = true;
	}
	
	public boolean getConnected() {
		return connected;
	}
	
}
