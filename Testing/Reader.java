package Testing;
/*
* Short summary:
* This class reads files and makes them into graphs.
*/

/**
 * Author:
 * Cavid Karca
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Reader{
    private final String path;
    public Reader(String path){
        this.path=path;
    }
    public Graph read(String filename){
        String s=path;
        s+="/";
        s+=filename;
        filename=s;
        try {
            FileReader f = new FileReader(filename);
            BufferedReader reader = new BufferedReader(f);
            int vertices=-1;
            int edges=-1;
            String line="";
            line=reader.readLine();
            if(line.contains("VERTICES = ")){
                    vertices = Integer.parseInt(line.substring(11));
            }else{
                System.out.println("File not Redable");
                return null;
            }
            line=reader.readLine();
            if(line.contains("EDGES = ")){
                    edges = Integer.parseInt(line.substring(8));
            }else{
                System.out.println("File not Redable");
                return null;
            }

            //create an DotList
            LinkedList<Dot> list = new LinkedList<>();
            for(int i=0;i<vertices;i++){
                list.add(new Dot());
            }
            //connect Dots
            for(int i=0;i<edges;i++){
                line = reader.readLine();
                String[] con = line.split(" ");
                if(con.length==2){
                    int a = Integer.parseInt(con[0])-1;
                    int b = Integer.parseInt(con[1])-1;
                    list.get(a).setConnection(list.get(b));
                    list.get(b).setConnection(list.get(a));
                }else{
                    System.out.println("File not Redable");
                    return null;
                }

            
            }
            Graph graph = new Graph(vertices, edges, list);
            return graph;
        } catch(IOException e){

        }
        return null;
    }
}
