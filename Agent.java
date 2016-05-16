import java.util.ArrayList;
import java.util.Random;

public class Agent extends Messenger {

    private ArrayList<Boolean> events = new ArrayList();
    private Node lastNode;
    private Node currentNode;
    private ArrayList<Integer> distance = new ArrayList();

    public Agent(int id, Node n) {
        events.add(id, true);
        distance.add(id, 0);
        currentNode = n;
        maxsteps = 50;
        currentNode.visiting = true;
    }

    public void addEvent() {
        for(int i= 0; i<currentNode.distance.size();i++){
            for(int j=0; j<distance.size(); i++){

            }
        }

    }

    public void getEvent() {

    }

    @Override // OFÄRDIG
    public void move() {
        Random rand = new Random();
        Node u;
        while (nodesToVisit==null) {
            u = currentNode.neighbours.get(rand.nextInt(currentNode.neighbours.size()));
            if (u != lastNode) {
                if (u.visiting) {
                    nodesToVisit = u;
            }
        }
        currentNode = nodesToVisit;
        currentNode.visiting = true;
        lastNode.visiting = false;
        getEvent();
        addEvent();
    }
}
