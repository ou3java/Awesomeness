import java.util.ArrayList;

public class Environment {

    private int id=0;
    public int happening;
    private ArrayList<Event> regEvents;
    private ArrayList<Node> nodes;

    public Environment(int x, int y){

        int k=0;
        regEvents=new ArrayList();
        nodes = new ArrayList();
        for(int i=0; i<y; i++) {
            for(int j=0; j<x; i++) {
                nodes.add(k, new Node(new Position(j,i)));
                k++;
            }
        }
    }

    public int createEventId(){
        id++;
        return id;
    }

    public void timeTick(){

    }

    public void addEvent(Event e){
        regEvents.add(id,e);

    }

    public Node getNode(Position p){
        return nodes.get(p.getX()+50*p.getY());

    }
}

