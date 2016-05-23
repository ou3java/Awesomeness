import java.util.HashMap;
import java.util.Random;

/**
 * Agent class extends the messenger class. It travels among the nodes
 * and spreads information among them about events it encounters
 */

public class Agent extends Messenger {

    private HashMap<Integer, Route> routTable = new HashMap<>();
    private Node lastNode;

    /**
     * Constructor for the agent
     * @param id The event that created the agent
     * @param n The agents current node
     * @param time the timestep the event was created in
     */
    public Agent(int id, Node n, int time) {

        routTable.put(id, new Route(n, 0, time));
        currentNode = n;
        maxsteps = 50;
        currentNode.visiting = true;
    }

    /**
     * This method adds all the events the agent is aware of to a node
     * @param n the node to add the events to
     */
    private void addEvent(Node n) {

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
    private void getEvent(Node n) {
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
            //If there is no node present in nodesToVisit...
            if (nodesToVisit == null) {
                //Choose a random node among agents current node's neighbours...
                Random rand = new Random();
                Node n = currentNode.neighbours.get(rand.nextInt(currentNode.neighbours.size()));
                //making sure nodesToVisit is not the last node.
                while (n == lastNode || n == null) {
                    n = currentNode.neighbours.get(rand.nextInt(currentNode.neighbours.size()));
                }
                //...and assign it to nodesToVisit.
                nodesToVisit = n;
            }
            //If the node in nodesToVisit is not visided already...
            if (!nodesToVisit.visiting) {
                //Change the values in agents routtable before making the move.
                for (int id : this.routTable.keySet()) {
                    this.routTable.get(id).node = currentNode;
                    this.routTable.get(id).distance++;
                }
                //Making the move by changing the agents nodevalues.
                lastNode = currentNode;
                currentNode = nodesToVisit;
                currentNode.visiting = true;
                lastNode.visiting = false;
                maxsteps--;
                nodesToVisit = null;
                //After the move is made, update the table with the new node
                addEvent(currentNode);
                getEvent(currentNode);
            }
        }
    }
}