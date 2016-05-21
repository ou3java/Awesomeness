import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Node {

    public Position p;
    protected ArrayList<Node> neighbours = new ArrayList();
    // private ArrayList<Event> events = new ArrayList();
    //private ArrayList<Node> direction=new ArrayList();
    //protected ArrayList<Integer> distance=new ArrayList();
    protected HashMap<Integer, Route> routTable = new HashMap<>();
    protected boolean visiting=false;
    private int chanceToSend = 50;

    public Node(Position p){
        this.p=p;
    }

    public Agent sendAgent(int id, Node n){
        Agent e = new Agent(id, n);
        return e;
    }

    public Request sendRequest(int id, Node n){
        Request r = new Request(id,n);
        return r;

    }

    public void setEvent(int id, Environment e){

        routTable.put(id, new Route(this, 0));
        Random rand = new Random();
        if(rand.nextInt(100) < chanceToSend){
            e.messengers.add(sendAgent(id, this));
        }
    }

    public Node randomNeighbour(){
        Random rand = new Random();
        return neighbours.get(rand.nextInt(neighbours.size()));
    }
}