import java.util.ArrayList;
import java.util.Hashtable;

public class Agent extends Messenger{

    private ArrayList<Event> events = new ArrayList();
    private Position lastPos;
    private ArrayList<Integer> distance = new ArrayList();

    public Agent(Event e){
        events.add(0,e);
        distance.add(0,0);

    }

    public void addEvent(){

    }

    public void getEvent(){

    }

}
