package Algorithms;
/*
* Short summary:
* The generator class generates random graphs and saves it as a file, for when a user wants to create their own graph.
*/

/**
 * Author:
 * Cavid Karca
 */
import java.util.Random;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Testing.*;
class ColEdge extends Object{
public int u;
public int v;
    public ColEdge(int pU,int pV){
        u=pU;
        v=pV;
    }
}
public class Generator{

    private static Generator generator = new Generator();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many vertices?");
        int vertices = input.nextInt();
        System.out.println("How many edges");
        int edges = input.nextInt();
        System.out.println("which file?");
        String file = input.next();
        generator.GenerateGraph(vertices,edges,file);

    }
    public Generator(){
        //Generates beautiful Graphs
    }
    public void GenerateGraph(int vertices,int edges,String filename){
        //Generates Graph and saves it as file
        LinkedList list = Generate(vertices,edges);
        System.out.println(filename +" created, vertices: "+vertices+" edges: "+list.size());
        fileomat(filename, vertices, list);
    } 
    public Graph GenerateGraph(int vertices,int edges){
        LinkedList list1 = Generate(vertices, edges);
        LinkedList<Dot> list = new LinkedList<Dot>();
        for(int i=0;i<vertices;i++){
            list.add(new Dot());
        }
        for(int i=0;i<list1.size();i++){
            ColEdge c = (ColEdge)list1.get(i);
            int u = c.u-1;
            int v = c.v-1;
            list.get(u).setConnection(list.get(v));
            list.get(v).setConnection(list.get(u));
        }
        Graph graph = new Graph(vertices, edges, list);
       return graph;
    } 
    private LinkedList Generate(int vertices,int edges){
        //Returns a List with the connections of a random-graph in it
        //The graph has transfered number of vertices and edges,if edges<0 a random number of edges 
        //between 0 and the maximal possible number of edges will be used, if edges>= maximal number of edges
        // max number of edges will be used
        System.out.println(vertices+" b "+edges);
        LinkedList list = doList2(vertices);
        if(edges>=list.size()){
            return list;
        }
        LinkedList newList = new LinkedList();
        Random rand = new Random();
        int i=0;
        int r=edges;
        if(vertices==1){
            return newList;
        }
        if(r<0){
            r = rand.nextInt(list.size())+1;
        }
        while(i<r){
            int r1 = rand.nextInt(list.size());
            newList.add(list.remove(r1));
            i++;
        }
        return newList;
    }
    private LinkedList doList2(int vertices){
        //Returns a List with all connections between the transfered number of vertices 
        LinkedList list = new LinkedList();
        for(int i=1;i<vertices;i++){
            int x=i+1;
            while (x<=vertices) {
                ColEdge col = new ColEdge(i,x);
                list.add(col);
                x++;
            }
        }
        return list;
    }
    private void fileomat(String fileName,int vertices,LinkedList list){
        //needs a String as the name of the Graphfile, the number of vertices and 
        //and writes a graph, transfered as a LinkedList into a .txt
        try{
            FileWriter f = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(f);
            String strg="";
            strg+= "VERTICES = "+vertices+"\r\n"+"EDGES = "+list.size()+"\r\n";
            while(!list.isEmpty()){
                ColEdge col = (ColEdge)list.remove(0);
                strg+=Integer.toString(col.u)+" "+Integer.toString(col.v)+"\r\n";
            }
            writer.write(strg);
            writer.close();
            System.out.println(fileName + " Safed");
            
        }catch(IOException ex){
            System.out.println("can't write file");
            ex.fillInStackTrace();
            return;
        }
    }
}
