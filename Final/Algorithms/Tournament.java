package Algorithms;

import Testing.Parameters;
import Testing.Reader;

import java.util.concurrent.TimeUnit;

public class Tournament {
    public void doTournament(String graphName){
        TournamentPrinter tournamentPrinter = new TournamentPrinter();
        Reader reader = new Reader(Parameters.path);
        Regular regular = new Regular();
        Cleaner cleaner = new Cleaner();
        Thread t = new Thread(() -> {
            Graph g = reader.read(graphName);
            tournamentPrinter.printUpperBound(g.giveUp());
            g.sort();
            tournamentPrinter.printUpperBound(g.giveUp());
        });
        t.start();
        Graph graph = reader.read(graphName);
        //GO
        graph.sort();
        if(graph.getEdges()<100000){
            tournamentPrinter.printUpperBound(graph.giveUp());
            cleaner.cleanAndDivide(graph);
            graph.gReset();
            regular.breakUp(graph);
            graph.gReset();
            //cleaner.cleanAndDivide(graph);
            //graph.gReset();
        }
        tournamentPrinter.printLowerBound(graph.giveLow());
        tournamentPrinter.printExact(graph.giveCN());


    }
}
