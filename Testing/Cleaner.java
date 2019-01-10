package Testing;

import java.util.ArrayList;

public class Cleaner {
    public void cleanUp(Graph graph) {
        int min = 0;
        int startVertices = graph.getVertices();
        if (graph.getVertices() >= 2 && graph.getEdges() >= 1) {
            min = 2;
        }
        if (graph.getEdges() == 0) {
            min = 1;
        }
        boolean cleaned = false;
        int counter = 0;
        ArrayList<Dot> list = graph.getList();
        while (!cleaned) {
            cleaned = true;
            for (int i = 0; i < list.size(); i++) {
                Dot d = list.get(i);
                if (d.giveList().size() == 1) {
                    d.killMe();
                    list.remove(i);
                    graph.setEdgesMinus();
                    graph.setVerticesMinus();
                    cleaned = false;
                    counter++;
                } else if (list.get(i).giveList().size() == 0) {
                    list.remove(i);
                    graph.setVerticesMinus();
                    cleaned = false;
                    counter++;
                }
            }
        }
            double percent = ((double)counter/(double)startVertices)*100;
            System.out.println("Removed Vertices: " + counter + " || "+percent+ " percent");
            if (graph.getVertices() <= 0) {
                if (min == 1) {
                    graph.getList().add(new Dot());
                    graph.setVerticesPlus();
                }
                if (min == 2) {
                    Dot d1 = new Dot();
                    Dot d2 = new Dot();
                    d1.setConnection(d2);
                    d2.setConnection(d1);
                    graph.getList().add(d1);
                    graph.getList().add(d2);
                    graph.setVerticesPlus();
                    graph.setVerticesPlus();
                    graph.setEdgesPlus();
                }
            }
        }
    }
