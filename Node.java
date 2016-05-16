import java.util.ArrayList;
import java.util.Random;

public class Node {

    public Position p;
    protected ArrayList<Node> neighbours = new ArrayList();
   // private ArrayList<Event> events = new ArrayList();
    private ArrayList<Node> direction=new ArrayList();
    private ArrayList<Integer> distance=new ArrayList();
    private boolean visiting=false;

    public Node(Position p){
        this.p=p;
    }

    public Agent sendAgent(int id, Node n){
        Agent e = new Agent(id, n);
        return e;
    }

    public void sendRequest(){

    }

    public boolean setEvent(int id){
        Random rand = new Random();
        if(rand.nextInt(10000)==5000){
            distance.add(id, 0);
            return true;
        }
        return false;
    }
}
