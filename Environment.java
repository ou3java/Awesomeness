import java.util.ArrayList;

public class Environment {

    private int id=0;
    public int happening;
    private ArrayList<Event> regEvents = new ArrayList();

    public Environment(int x, int y){

    }

    public int createEventId(){
        id++;
        return id;
    }

    public void timeTick(){

    }

    public void addEvent(Event e){

    }

    public Node getNode(Position p){

    }
}
