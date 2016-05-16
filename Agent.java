import java.util.ArrayList;

public class Agent extends Messenger{

    private ArrayList<Boolean> events = new ArrayList();
    private Node lastNode;
    private Node currentNode;
    private ArrayList<Integer> distance = new ArrayList();

    public Agent(int id, Node n){
        events.add(id,true);
        distance.add(id,0);
        currentNode=n;
        maxsteps=50;
    }

    public void addEvent(){

    }

    public void getEvent(){

    }

}
