import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The nodeclass simulates a node in the environment grid. It can contain
 * Create events and contain information about events. Each node is created with
 * a position
 */
public class Node {

    public Position p;
    protected ArrayList<Node> neighbours = new ArrayList();
    protected HashMap<Integer, Route> routTable = new HashMap<>();
    public boolean visiting=false;
    private int chanceToSend = 50;

    /**
     * Constructor for Node.
     * @param p the position for the node
     */
    public Node(Position p){
        this.p=p;
    }

    /**
     * This method sends an agent. There is a chance each time an event is
     * created.
     * @param id The events id
     * @param n The node that sent the event
     * @param time The events timestep
     * @return the agent to be sent
     */
    private Agent sendAgent(int id, Node n, int time){
        Agent e = new Agent(id, n, time);
        return e;
    }

    /**
     * This method sends a request for a specific event.
     * @param id The events id
     * @param n The node to send the request
     * @return The request to be sent
     */
    public Request sendRequest(int id, Node n){
        Request r = new Request(id,n);
        return r;

    }

    /**
     * Sets an event at the node.
     * @param id the events id
     * @param e The environmentobject the event is in
     * @param time the timestep the event is created in
     */
    public void setEvent(int id, Environment e, int time){

        routTable.put(id, new Route(this, 0, time));
        Random rand = new Random();
        if(rand.nextInt(100) < chanceToSend){
            e.messengers.add(sendAgent(id, this, time));
        }
    }

    /**
     * Method to choose a random neighbour for the node
     * @return The chosen node
     */
    public Node randomNeighbour(){
        Random rand = new Random();
        return neighbours.get(rand.nextInt(neighbours.size()));
    }
}