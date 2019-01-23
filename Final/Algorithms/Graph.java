package Algorithms;
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
import java.util.ArrayList;

public class Graph{
    //saves all the parameters of the graph
    private int edges;
    private int vertices;
    private int upperBound=-1;
    private int lowerBound=-1;
    private int cNumber=-1;
    private ArrayList<Dot> list;
    private ArrayList<Graph> subGraph;
    private int addToChrom=0;
    public Graph(int vertices,int edges,ArrayList<Dot> list){
        this.edges=edges;
        this.vertices=vertices;
        this.list=list;
        subGraph = new ArrayList<>();
    }
    //Setters and Getters
    //If lowerBound/upperBound/cNumber == -1 it follows Graph was not calculated
    public int getEdges() {
        return edges;
    }
    public ArrayList<Dot> getList() {
        return new ArrayList((ArrayList<Dot>)list.clone());
    }
    public void setList(ArrayList<Dot> newList){list=(ArrayList<Dot>) newList.clone();}
    public int getVertices() {
        return vertices;
    }
    public void setEdgesMinus(){if(edges>0){edges--;} }
    public void setEdgesMinus(int minus){ edges = edges-minus; }

    public void setVerticesMinus(){if(vertices>0){vertices--;}}
    public void setEdgesPlus(){edges++;}
    public void setVerticesPlus(){vertices++;}
    public void addDot(Dot dot){
        list.add(dot);
    }
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
            Dot d = list.get(i);
            d.setContent(0);
            d.setIsConnected(false);
            //d.setIgnoreMe(false);

        }
    }
    public boolean hasSub(){
        if(subGraph.isEmpty()){
            return false;
        }
        return true;
    }
    public ArrayList<Graph> getSub(){
        return (ArrayList<Graph>) subGraph.clone();
    }
    public void addSub(Graph graph){
        subGraph.add(graph);
    }
    public void addAddToChrom(){
        addToChrom++;
    }
    public int getAddToChrom(){
        return addToChrom;
    }
    public int giveCN(){
        this.gReset();
        if(this.hasSub()){
            int max=0;
            for(int i=0;i<subGraph.size();i++){
                int x=subGraph.get(i).giveCN();
                if(x>max){
                    max=x;
                }
            }
            return max+addToChrom;
        }
        else{
            CircleDetector circleDetector = new CircleDetector();
            if(circleDetector.detectCircle(this)){
                if(this.getVertices()%2==0){
                    return 2+addToChrom;
                }
                else {
                    return 3+addToChrom;
                }
            }
            NewForce newForce = new NewForce();
            if(upperBound!=-1){
                if(lowerBound!=-1){
                    return addToChrom + newForce.doNewForce(this,this.upperBound,this.lowerBound,false);
                }else{
                    MC0 mc0 = new MC0();
                    return addToChrom + newForce.doNewForce(this,upperBound,mc0.search(this),false);
                }
            }else {
                if(lowerBound!=-1){
                    Powell powell = new Powell();
                    return addToChrom + newForce.doNewForce(this,powell.doPowell(this),lowerBound,false);
                }
                else {
                    Powell powell = new Powell();
                    MC0 mc0 = new MC0();
                    return addToChrom + newForce.doNewForce(this,powell.doPowell(this),mc0.search(this),false);
                }
            }
        }
    }
    public int giveUp(){
        this.gReset();
        if(this.hasSub()){
            int max=0;
            for(int i=0;i<subGraph.size();i++){
                int x=subGraph.get(i).giveUp();
                if(x>max){
                    max=x;
                }
            }
            this.upperBound = max+addToChrom;
            return max+addToChrom;
        }
        else{
            Powell powell = new Powell();
            BiPart biPart = new BiPart();
            int xx = powell.doPowell(this);
            //this.gReset();
            //int xx2 = biPart.doBi(this).size();
            //int xx = Math.min(xx1,xx2);
            this.upperBound = xx + addToChrom;
            return addToChrom + xx;
        }
    }
    public int giveLow(){
        this.gReset();
        if(this.hasSub()){
            int max=0;
            for(int i=0;i<subGraph.size();i++){
                int x=subGraph.get(i).giveLow();
                if(x>max){
                    max=x;
                }
            }
            this.lowerBound = max + addToChrom;
            return max+addToChrom;
        }
        else{
            MC0 mc0 = new MC0();
            int yy = mc0.search(this);
            this.lowerBound = addToChrom + yy;
            return addToChrom + yy;
        }
    }
    public ArrayList<Graph> giveLeafList(){
        ArrayList<Graph> newList = new ArrayList<>();
        doLeafList(newList);
        return newList;
    }
    private void doLeafList(ArrayList<Graph> graphList){
        if(this.hasSub()){
            for(int i=0;i<subGraph.size();i++){
                subGraph.get(i).doLeafList(graphList);
            }
            return;
        }
        else {
            graphList.add(this);
        }
    }
    public void mergeGraphs(ArrayList<Graph> list){
        for (int i=0;i<list.size();i++){
            this.mergeGraphs(list.get(i));

        }
    }
    public void mergeGraphs(Graph graph){
        this.edges=this.edges + graph.edges;
        this.vertices=this.vertices + graph.vertices;
        this.list.addAll(graph.getList());
    }

}
