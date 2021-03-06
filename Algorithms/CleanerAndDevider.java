package Algorithms;

import Testing.Cleaner;
import Testing.Graph;

import java.util.ArrayList;

public class CleanerAndDevider {
    public void cleanAndDivide(Graph g){
        Cleaner cleaner = new Cleaner();
        Divider divider = new Divider();
        cleaner.cleanUp(g);
        divider.divide(g);
        if(g.hasSub()){
            ArrayList<Graph> gList = g.getSub();
            for(int i=0;i< gList.size();i++){
                cleanAndDivide(gList.get(i));
            }
        }
    }
}
