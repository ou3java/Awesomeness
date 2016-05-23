import java.util.Stack;

/**
 * Requestclass extends messenger class. It moves around and searches nodes
 * for information about a specific event
 */

public class Request extends Messenger {

    private Stack<Node> wayBack = new Stack();
    private Node startNode;
    private int event;
    private boolean found= false;
    private Node targetNode;
    private boolean hasReturned=false;
    private int stepsToGoal;

    /**
     * Constructor for Request.
     * @param id The event-id to search for
     * @param n The starting node
     */
    public Request(int id, Node n){
        event = id;
        maxsteps=45;
        currentNode = n;
        startNode=n;
        currentNode.visiting = true;


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
        if(targetNode==null && hasReturned==false){
            hasReturned=true;
            maxsteps=45;
            wayBack.push(startNode);
        }
        else{
            String reqStart= "x:"+(startNode.p.getX()+1)+" y:"+(startNode.p.getY()+1);
            String reqEnd = "x:"+(targetNode.p.getX()+1)+" y:"+(targetNode.p.getY()+1);
            System.out.println("Event "+event+" found!");
            System.out.println("Request made by node: "+reqStart+"");
            System.out.println("Event was found in node: "+reqEnd+"");
            System.out.println("Event time: "+targetNode.routTable.get(event).time+"");
            System.out.println("Steps it took: "+stepsToGoal+"\n");

            maxsteps = 0;
        }
    }

    /**
     * This method simulates a move the Request makes from one node to another.
     */
    public void move(){
        //if the request has returned after either found the event or ran out
        //of steps...
        Boolean returning = found || maxsteps <=0;
        if(wayBack.isEmpty() && returning){
            //Do the dumpinfomethod and ignore the rest of move
            dumpInfo();
            return;
        }
        //Or if it has found its event or has ran out of steps...
        else if(found||maxsteps==0){
            //...queue the last node to nodesToVisit.
            nodesToVisit=wayBack.peek();

        }
        //Or if it has found no information about its event in its node...
        else if (currentNode.routTable.get(event)==null){
            //...choose a random node among its neighbours
            Node n = currentNode.randomNeighbour();
            if(wayBack.isEmpty()){
                n = currentNode.randomNeighbour();
            }
            else{
                while (n == wayBack.peek() || n == null) {
                    n = currentNode.randomNeighbour();
                }
            }
            nodesToVisit = n;
        }
        //Or if informaion about its event is found...
        else {
            //...choose the correct node from the table and add to nodesToVisit.
            nodesToVisit = currentNode.routTable.get(event).node;
        }
        //If the node in nodesToVisit is not visided already...
        if(!nodesToVisit.visiting){
            //make the move by changing the nodevalues.
            currentNode.visiting = false;
            //different behavior if returning.
            if(returning) {
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