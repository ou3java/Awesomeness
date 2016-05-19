import java.util.Random;
import java.util.Stack;

public class Request extends Messenger {

    private Stack<Node> wayBack = new Stack();
    private Node startNode;
    private int event;
    private boolean found= false;
    private Node lastNode;
    private Node targetNode;
    private boolean hasReturned=false;
    private int stepsToGoal;

    public Request(Integer id, Node n){
        event = id;
        maxsteps=45;
        wayBack.push(n);
        startNode=n;
        wayBack.peek().visiting = true;


    }

    public void checkEvent(){
       if(wayBack.peek().routTable.get(event).distance==0){
           found=true;
           targetNode=wayBack.peek();
           stepsToGoal=wayBack.size();

       }
    }

    public void dumpInfo(){
        if(targetNode==null&&hasReturned==false){
            hasReturned=true;
            maxsteps=45;
            wayBack.push(startNode);
        }
        else{
            String reqStart= ""+startNode.p.getX()+" "+startNode.p.getY();
            String reqEnd = ""+targetNode.p.getX()+" "+targetNode.p.getY();
            System.out.println("Event request found!\n");
            System.out.println("Request startnode position: "+reqStart+"\n");
            System.out.println("Request was found in node: "+reqEnd+"\n");
            System.out.println("Steps it took: "+stepsToGoal+"\n");
            //HÄR SKA ALLT DÖÖ
        }

    }

    public void move(){
    if(wayBack.isEmpty()){
        dumpInfo();
        return;
    }
        if(found||maxsteps==0){
            if(!wayBack.isEmpty()){
                if(!wayBack.peek().visiting){
                    nodesToVisit=wayBack.peek();
                    wayBack.pop();
                }
            }

        }
        else if (wayBack.peek().routTable.get(event).node==null){
            if(nodesToVisit==null) {
                Node u;
                Random rand = new Random();
                u = wayBack.peek().neighbours.get(rand.nextInt(wayBack.peek().neighbours.size()));
                nodesToVisit = u;
            }
            if(!nodesToVisit.visiting){
                lastNode = wayBack.peek();
                wayBack.push(nodesToVisit);
                wayBack.peek().visiting = true;
                lastNode.visiting = false;
                maxsteps--;
                checkEvent();
            }

        }
        else {
            nodesToVisit=wayBack.peek().routTable.get(event).node;
            if(!nodesToVisit.visiting) {
                lastNode = wayBack.peek();
                wayBack.push(nodesToVisit);
                wayBack.peek().visiting = true;
                lastNode.visiting = false;
                maxsteps--;
                checkEvent();
            }
        }
     }
}