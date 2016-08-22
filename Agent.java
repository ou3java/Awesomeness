/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */
import java.util.*;

/**
 * Agent class extends the messenger class. It travels among the nodes
 * and spreads information among them about events it encounters
 */

public class Agent extends Messenger {

    protected HashMap<Integer, Route> routTable = new HashMap<>();
    private Random rand = new Random();
    private boolean waiting = false;

    /**
     * Constructor for the agent
     * @param id The event that created the agent
     * @param n The agents current node
     * @param time the timestep the event was created in
     */
    public Agent(int id, Node n, int time, int maxStep) {


        routTable.put(id, new Route(n, 0, time));
        currentNode = n;
        currentNode.visiting = true;
        this.maxsteps = maxStep;
    }

    /**
     * This method adds all the events the agent is aware of to a node
     * @param n the node to add the events to
     */
    protected void addEvent(Node n) {

        //For each key in agents routtable...
        for(int id : this.routTable.keySet()){
            //...that is not present in the nodes routtable...
            if(!n.routTable.containsKey(id)){
                //...add to the nodes routtable.
                n.routTable.put(id, new Route(this.routTable.get(id)));
            }
            //...that is present in the nodes routtable but has lower distance
            else if(this.routTable.get(id).distance < n.routTable.get(id).distance){
                //...replace the nodes route with agents route.
                n.routTable.put(id, new Route(this.routTable.get(id)));
            }
        }
    }

    /**
     * This method gets all the events a node is aware of
     * @param n the node to get events from
     */
    protected void getEvent(Node n) {
        //For each key in the nodes routtable...
        for(int id : n.routTable.keySet()){
            //...that is not present in agents routtable...
            if(!this.routTable.containsKey(id)){
                //...add to agents routtable.
                this.routTable.put(id, new Route(n.routTable.get(id)));
            }
            //...that is present in agents routtable but has lower distance
            else if(n.routTable.get(id).distance < this.routTable.get(id).distance){
                //...replace agents route with the nodes route.
                this.routTable.put(id, new Route(n.routTable.get(id)));
            }
        }

    }

    /**
     * This method simulates a move agent makes from one node to another
     */
    @Override
    public void move() {

        if (maxsteps > 0) {
            Node n;
            //If there is no node present in nodesToVisit...
            if (nodesToVisit == null) {
                //create a temporary set of the neighbourlist
                LinkedList<Node> temp = new LinkedList<Node>(currentNode.neighbours);
                //exclude visited nodes from the temporary neighbourlist
                temp.removeAll(visitedNodes);
                //Choose a random neighbour
                Collections.shuffle(temp, rand);
                //if all nodes are visited choose a rendom neighbour
                if(temp.isEmpty()){
                    n = currentNode.randomNeighbour();
                }
                else{
                    n = temp.getFirst();
                }
                //assign the chosen node to nodesToVisit.
                nodesToVisit = n;
            }
            //If the node in nodesToVisit is not visited by other messages...
            if (!nodesToVisit.visiting) {

                //Change the values in agents routtable before making the move.
                for (int id : this.routTable.keySet()) {
                    this.routTable.get(id).node = currentNode;
                    this.routTable.get(id).distance++;
                }
                //Making the move by changing the agents nodevalues.
                visitedNodes.add(currentNode);
                currentNode.visiting = false;
                currentNode = nodesToVisit;
                //System.out.printf("Agent % visiting node %d\n", currentNode.p.getX());
                currentNode.visiting = true;
                maxsteps--;
                nodesToVisit = null;
                //After the move is made, update the table with the new node
                addEvent(currentNode);
                getEvent(currentNode);
                waiting = false;
            }
            else{
                //if the node to visit is busy twice reset it.
                //This prevents locks if 2 messengers wants to visit eachothers
                //node.
                if(waiting){
                    waiting = false;
                    nodesToVisit = null;
                }
                else{
                    waiting = true;
                }
            }
        }
    }
}
