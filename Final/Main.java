import Algorithms.Tournament;

public class Main {
    public static void main(String[] args){
        String name = args[0];
        Tournament tournament = new Tournament();
        tournament.doTournament(name);
    }
}
