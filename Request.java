/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */

import java.util.Stack;

/**
 * Requestclass extends messenger class. It moves around and searches nodes
 * for information about a specific event
 */

public class Request extends Messenger {

    protected Stack<Node> wayBack = new Stack();
    protected Node startNode;
    private int event;
    protected boolean found = false;
    private Node targetNode;
    private boolean retry = true;
    private int stepsToGoal;
    protected int timeoutCounter;

    /**
     * Constructor for Request.
     * @param id The event-id to search for
     * @param n The starting node
     */
    public Request(int id, Node n, int maxStep){
        event = id;
        currentNode = n;
        startNode=n;
        currentNode.visiting = true;
        this.maxsteps = maxStep;
        timeoutCounter = 8 * maxsteps;


    }

    /**
     * A method to to check each node if the event is reached
     */
    private void checkEvent(){
        if(currentNode.routTable.get(event) != null){
            if(currentNode.routTable.get(event).distance == 0){
                found = true;
                targetNode = currentNode;
                stepsToGoal = wayBack.size();
            }
        }
    }

    /**
     * A method to use when Request has returned. If it returns for the first
     * time it tries again to find its event.
     * If it has found its event it writes the information to the console
     */
    private void dumpInfo(){

        String reqStart= "x:"+(startNode.p.getX()+1)+" y:"+(startNode.p.getY()+1);
        String reqEnd = "x:"+(targetNode.p.getX()+1)+" y:"+(targetNode.p.getY()+1);
        System.out.println("Event "+event+" found!");
        System.out.println("Request made by node: "+reqStart+"");
        System.out.println("Event was found in node: "+reqEnd+"");
        System.out.println("Event time: "+targetNode.routTable.get(event).time+"");
        System.out.println("Steps it took: "+stepsToGoal+"\n");

        maxsteps = 0;

    }

    /**
     * This method simulates a move the Request makes from one node to another.
     */
    public void move(){
        timeoutCounter--;
        //if the request timeout counter is done try again one more time
        if(timeoutCounter <= 0 && retry){
            retry =false;
            maxsteps=45;
            wayBack.clear();
            wayBack.push(startNode);
            currentNode = startNode;
        }

        //if the request has found the event...
        if(found) {
            //...and has reached the start
            if (wayBack.isEmpty()) {
                //Do the dumpinfomethod and ignore the rest of move
                dumpInfo();
                return;
            }
            //...or is on its way back
            else{
                //backtrack
                nodesToVisit = wayBack.peek();
            }
        //Or if it has run out of moves...
        }else if(maxsteps <= 0) {

            //clear visiting information and return
            currentNode.visiting = false;
            return;

        //Or if it has found no information about its event in its node...
        }else if (currentNode.routTable.get(event)==null){

            //Continue with random neighbour
            Node n = currentNode.randomNeighbour();
            nodesToVisit = n;
        //Or if information about its event is found...
        }else{

            //...choose the correct node from the table and add to nodesToVisit.
            nodesToVisit = currentNode.routTable.get(event).node;
        }
        //If the node in nodesToVisit is not visided already...
        if(!nodesToVisit.visiting){
            //make the move by changing the nodevalues.
            currentNode.visiting = false;
            //different behavior if returning.
            if(found) {
                wayBack.pop();
            }
            else {
                wayBack.push(currentNode);
            }
            currentNode = nodesToVisit;
            currentNode.visiting = true;
            maxsteps--;
            nodesToVisit = null;
            //check for event at the end of each move
            checkEvent();
        }
    }
}
