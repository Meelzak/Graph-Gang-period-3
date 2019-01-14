package Algorithms;

import java.util.LinkedList;

import Testing.*;

public class WheelDetector {
	private int chromaticNumber = 0;
	private boolean wheel = false;
	private int connect = 0;
	
	public boolean detectWheel(Graph g) {
		//has to check every single vertex to see if it has two edges or is the central point
		LinkedList<Dot> dotList = (LinkedList<Dot>) g.getList().clone();
		for (int i = 0; i < g.getList().size(); i++) {
			if (dotList.get(i).giveList().size() == 2 || (dotList.get(i).giveList().size() == (g.getList().size() -1) && connect == 0 )) {
				wheel = true;
				if (dotList.get(i).giveList().size() == g.getList().size() -1){
					connect++;
				}
			}
			else {
				wheel = false;
				break;
			}
		}
		if (connect != 1) {
			wheel = false;
		}
		this.setChromatic(g);
		return wheel;
	}
	
	public void setChromatic(Graph g) {
		if (wheel && (g.getList().size() % 2 == 0) && connect == 1) {
			chromaticNumber = 4;
		}
		else if (wheel && (g.getList().size() % 2 != 0) && connect ==1) {
			chromaticNumber = 3;
		}
	}
	public int getChromatic() {
		return chromaticNumber;
	}
	
}

