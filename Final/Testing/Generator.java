package Algorithms;
/*
 * Short summary:
 * The generator class generates random graphs and saves it as a file, for when a user wants to create their own graph.
 */

/**
 * Author:
 * Cavid Karca
 */
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import Testing.*;
class ColEdge extends Object{
    public int u;
    public int v;
    public ColEdge(int pU,int pV){
        u=pU;
        v=pV;
    }
    public boolean isEqual(int u ,int v){
        if(this.u==u && this.v==v){
            return true;
        }
        if(this.u==v && this.v==u){
            return true;
        }
        return false;
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
        ArrayList list = Generate(vertices,edges);
        System.out.println(filename +" created, vertices: "+vertices+" edges: "+list.size());
        fileomat(filename, vertices, list);
    }
    public Graph GenerateGraphDensity(int vertices,double density){
        double edges = ((density/100)*(vertices*(vertices-1)))/2;
        return this.GenerateGraph(vertices,(int)edges);
    }
    public Graph GenerateGraph(int vertices,int edges){
        ArrayList list1 = Generate(vertices, edges);
        ArrayList<Dot> list = new ArrayList<Dot>();
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
    private ArrayList Generate(int vertices,int edges){
        //Returns a List with the connections of a random-graph in it
        //The graph has transfered number of vertices and edges,if edges<0 a random number of edges
        //between 0 and the maximal possible number of edges will be used, if edges>= maximal number of edges
        // max number of edges will be used
        ArrayList list = doList2(vertices);
        if(edges>=list.size()){
            return list;
        }
        ArrayList newList = new ArrayList();
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
    private ArrayList doList2(int vertices){
        //Returns a List with all connections between the transfered number of vertices
        ArrayList list = new ArrayList();
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
    private void fileomat(String fileName,int vertices,ArrayList list){
        //needs a String as the name of the Graphfile, the number of vertices and
        //and writes a graph, transfered as a ArrayList into a .txt
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

    public void generateGephiFile(Graph graph, String name) {
        ArrayList<Dot> list = graph.getList();
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        builder.append("<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">\n");
        builder.append("    <meta lastmodifieddate=\""+dtf.format(localDate)+"\">\n");
        builder.append("        <description>"+name+" file</description>\n");
        builder.append("    </meta>\n");
        builder.append("    <graph mode=\"static\" defaultedgetype=\"directed\">\n");
        builder.append("        <nodes>\n");
        for (int i=0;i<list.size();i++){
            list.get(i).id=i;
            builder.append("            <node id=\""+i+"\" label=\""+i+"\" />\n");
        }
        builder.append("        </nodes>\n");
        builder.append("        <edges>\n");
        int id=0;
        for (int i=0;i<list.size();i++){
            Dot dot = list.get(i);
            ArrayList<Dot> l = dot.giveList();
            for(int x=0;x<l.size();x++){
                builder.append("            <edge id=\""+id+"\" source=\""+dot.id+"\" target=\""+l.get(x).id+"\" />\n");
                id++;
            }
            dot.killMe();
        }
        builder.append("        </edges>\n");
        builder.append("    </graph>\n");
        builder.append("</gexf>\n");
        try {
            File file = new File("C:\\Users\\cavid\\Dropbox\\Private\\Phase_3\\src\\Graphs2\\Gephi\\"+name+".gexf");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(builder.toString().getBytes(Charset.forName("UTF-8")));
            fileOutputStream.flush();
        }catch (Exception e){

        }
    }
}