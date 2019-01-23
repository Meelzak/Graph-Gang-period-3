package Algorithms;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Stack;

public class MC0{

    ArrayList<Dot> list;
    int maxSize;
    ArrayList<ArrayList<Dot>> maxList;
    public MC0(){
        maxList = new ArrayList<>();
    }
    public int search(Graph g){
        list = g.getList();
        ArrayList<Dot> C = new ArrayList<>();
        expand(C,list);
        g.gReset();
        return maxSize;
    }
    public void expand(ArrayList<Dot> C, ArrayList<Dot> list){
        for (int i=list.size()-1;i>=0;i--){
            if (C.size()+list.size()<=maxSize) {
                return;
            }
            Dot v = list.get(i);
            C.add(v);
            ArrayList<Dot> newList = new ArrayList();
            for (int j =0;j<=i;j++){
                Dot w = list.get(j);
                if (v.giveList().contains(w)){
                    newList.add(w);
                }
            }
            if (newList.isEmpty()&&C.size()>maxSize){
                maxSize = C.size();
            }
            if(!newList.isEmpty()){
                expand(C,newList);
            }
            C.remove(C.size()-1);
            list.remove(i);
        }

    }
    public void findWheel(Graph g){
        ArrayList<Dot> list = g.getList();
        g.gReset();
        for(int i=1;i<2;i++){
            if(isWheel(list.get(i).giveList())){
                System.out.println("Found Wheel!!");
            }
            g.gReset();
        }
    }
    private boolean isWheel(ArrayList<Dot> list){
        Dot d =list.get(0);
        Dot start =list.get(0);
        Dot d2 = new Dot();
        boolean once=true;
        Stack<Dot> stack = new Stack<>();
        d.setContent(100);
        stack.push(d);
        while (!stack.isEmpty()){
            boolean contains=false;
            ArrayList<Dot> l = stack.peek().giveList();
            for(int x=0;x<l.size();x++){
                if(list.contains(l.get(x)) && l.get(x).giveContent()==0){
                    if(once){
                        d2=l.get(x);
                        once=false;
                    }
                    stack.push(l.get(x));
                    l.get(x).setContent(10);
                    contains=true;
                    break;
                }
            }
            if(!contains){
                if(!stack.peek().equals(d2)){
                    if(stack.peek().giveList().contains(start)){
                        System.out.println("YO");
                        return true;
                    }
                }
                stack.pop().setContent(5);
            }
        }
        int counter=0;
        ArrayList<Dot> l2 = start.giveList();
        for(int i=0;i<l2.size();i++){
            if(l2.get(i).giveContent()==10){
                counter++;
                if(counter>2){
                    break;
                }
            }
        }
        if(counter==2){
            return true;
        }
        return false;
    }

}

