package Testing;

/*
 * Short summary:
 * A dot represents a vertix, and contains an ArrayList with Dots in it.
 * The Dots in this ArrayList are the vertices this particular vertix is connected to.
 * The vertices can also be filled (in this case with other pictures), when a hint is given.
 */
import java.util.ArrayList;

public class Dot{
    
    //there need to be two lists, one for the connections, on for the unavailable colours
    private ArrayList<Dot> list;
    //create instance variables you need
    private int content=0;
    private int group=-1;
    public int id=-1;
    public boolean isConnected=false;
    private int numberOfColouredConnections=0;
    private boolean seen = false;
    //firstly, the constructor:
    public Dot(){
        //A class representing a single vertice of a graph
        list = new ArrayList<>();
    }
    public void setConnection(Dot dot){//you can add another dot to the ArrayList inside your Dot, so you know these are connected
        list.add(dot);
    }
    public void setContent(int c){//the vertix can get content, if the user gives it to it
        content = c;
    }
    public int giveContent(){ return content; }//returns the content of the vertix
    
    public ArrayList<Dot> giveList(){//returns the list of the connections
        return list;
    }

    public void killMe(){
        for(int i=0;i<list.size();i++){
            list.get(i).giveList().remove(this);
        }
    }
    public void setGroup(int group){
        this.group=group;
    }
    public int getGroup(){
        return group;
    }
    
    public void addColouredConnections() {
    	numberOfColouredConnections++;
    }
    public int getColouredConnections() {
    	return numberOfColouredConnections;
    }
    public void resetColouredConnections() {
    	numberOfColouredConnections = 0;
    }
    public void haveSeen() {
    	seen = true;
    }
    public void resetSeen() {
    	seen = false;
    }
    public boolean getSeen() {
    	return seen;
    }
}
