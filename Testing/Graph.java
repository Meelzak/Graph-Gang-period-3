package Testing;
/*
* Short summary:
* This is the class where all the information about the graph is stored.
*/

/**
 * Author:
 * Cavid Karca
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Graph{
    //saves all the parameters of the graph
    private int edges;
    private int vertices;
    private int upperBound=-1;
    private int lowerBound=-1;
    private int cNumber=-1;
    private LinkedList<Dot> list;
    public Graph(int vertices,int edges,LinkedList<Dot> list){
        this.edges=edges;
        this.vertices=vertices;
        this.list=list;
    }
    //Setters and Getters
    //If lowerBound/upperBound/cNumber == -1 it follows Graph was not calculated
    public int getEdges() {
        return edges;
    }
    public LinkedList<Dot> getList() {
        return new LinkedList((LinkedList<Dot>)list.clone());
    }
    public int getVertices() {
        return vertices;
    }
    public void setEdgesMinus(){if(edges>0){edges--;} }
    public void setVerticesMinus(){if(vertices>0){vertices--;}}
    public void setEdgesPlus(){edges++;}
    public void setVerticesPlus(){vertices++;}

    public void setUpperBound(int upperBound){
        this.upperBound=upperBound;
    }
    public void setLowerBound(int lowerBound){
        this.lowerBound=lowerBound;
    }
    public void setCNumer(int cNumber){
        this.cNumber=cNumber;
    }
    public int getUpperBound(){
        return upperBound;
    }
    public int getLowerBound(){
        return lowerBound;
    }
    public int getCNumber(){
        return cNumber;
    }

    public void sort(){
        Collections.sort(list, (o1, o2) -> -Integer.compare(o1.giveList().size(),o2.giveList().size()));
    }
    public void gReset(){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }

}
