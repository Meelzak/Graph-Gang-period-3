package Algorithms;
import java.util.ArrayList;

public class CircleDetector {
    private int chromaticNumber = 0;
    private boolean circle = false;

    public boolean detectCircle(Graph g) {
        //has to check every single vertex to see if it has two edges
        ArrayList<Dot> dotList = g.getList();
        for (int i = 0; i < g.getList().size(); i++) {
            if (dotList.get(i).giveList().size() == 2) {
                circle = true;
            }
            else {
                circle = false;
                break;
            }
        }
        return circle;
    }
}