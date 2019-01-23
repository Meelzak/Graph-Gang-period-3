package Algorithms;

public class TournamentPrinter {
    private int upperBound=1000000000;
    private int lowerBound=-1;
    private int exact=-1;
    public void printUpperBound(int upperBound){
        if(this.upperBound>upperBound && exact==-1){
            this.upperBound=upperBound;
            System.out.println("NEW BEST UPPER BOUND = "+upperBound);
        }
    }
    public void printLowerBound(int lowerBound){
        if(this.lowerBound<lowerBound && exact==-1){
            this.lowerBound=lowerBound;
            System.out.println("NEW BEST LOWER BOUND = "+lowerBound);
        }
    }
    public void printExact(int exact){
        if(this.exact==-1){
            this.exact=exact;
            System.out.println("CHROMATIC NUMBER = "+exact);
        }
    }
}
