import java.util.ArrayList;
import java.util.Hashtable;

public class Node {

    private Position p;
    private ArrayList<Node> neighbours = new ArrayList();
    private ArrayList<Event> events = new ArrayList();
    private Hashtable<Integer, Node> direction=new Hashtable();
    private Hashtable<Integer, Integer> distance=new Hashtable();
    private boolean visiting=false;

    public Node(Position p){
        this.p=p;
    }

    public void sendAgent(){

    }

    public void sendRequest(){

    }

    public void setEvent(){

    }


}
