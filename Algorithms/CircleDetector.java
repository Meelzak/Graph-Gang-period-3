package Algorithms;

import java.util.ArrayList;

import Testing.*;

public class CircleDetector {
	private int chromaticNumber = 0;
	private boolean circle = false;
	
	public boolean detectCircle(Graph g) {
		//has to check every single vertex to see if it has two edges
		ArrayList<Dot> dotList = (ArrayList<Dot>) g.getList().clone();
		for (int i = 0; i < g.getList().size(); i++) {
			if (dotList.get(i).giveList().size() == 2) {
				circle = true;
			}
			else {
				circle = false;
				break;
			}
		}
		this.setChromatic(g);
		return circle;
	}
	
	public void setChromatic(Graph g) {
		if (circle && (g.getList().size() % 2 == 0)) {
			chromaticNumber = 2;
		}
		else if (circle && (g.getList().size() % 2 != 0)) {
			chromaticNumber = 3;
		}
	}
	public int getChromatic() {
		return chromaticNumber;
	}
	
}
