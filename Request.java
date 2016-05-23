
import java.util.Stack;

public class Request extends Messenger {

    private Stack<Node> wayBack = new Stack();
    private Node startNode;
    private int event;
    private boolean found= false;
    private Node targetNode;
    private boolean hasReturned=false;
    private int stepsToGoal;

    public Request(int id, Node n){
        event = id;
        maxsteps=45;
        currentNode = n;
        startNode=n;
        currentNode.visiting = true;


    }

    public void checkEvent(){
        if(currentNode.routTable.get(event) != null){
            if(currentNode.routTable.get(event).distance == 0){
                found = true;
                targetNode = currentNode;
                stepsToGoal = wayBack.size();
            }
        }
    }

    public void dumpInfo(){
        if(targetNode==null && hasReturned==false){
            hasReturned=true;
            maxsteps=45;
            wayBack.push(startNode);
        }
        else{
            String reqStart= ""+startNode.p.getX()+" "+startNode.p.getY();
            String reqEnd = ""+targetNode.p.getX()+" "+targetNode.p.getY();
            System.out.println("Event "+event+" found!");
            System.out.println("Request startnode position: "+reqStart+"");
            System.out.println("Event was found in node: "+reqEnd+"");
            System.out.println("Event timestep: "+targetNode.routTable.get(event).time+"");
            System.out.println("Steps it took: "+stepsToGoal+"\n");

            maxsteps = 0;
        }
    }

    public void move(){

        Boolean returning = found || maxsteps <=0;
        if(wayBack.isEmpty() && returning){
            dumpInfo();
            return;
        }
        else if(found||maxsteps==0){

                nodesToVisit=wayBack.peek();

        }
        else if (currentNode.routTable.get(event)==null){
                //Random rand = new Random();
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
        else {
            nodesToVisit = currentNode.routTable.get(event).node;
        }
        if(!nodesToVisit.visiting){
            currentNode.visiting = false;
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
            checkEvent();
        }
    }
}