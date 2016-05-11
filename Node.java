import java.util.ArrayList;

public class Node {

    private Position p;
    private ArrayList<Node> neighbours = new ArrayList();
    private ArrayList<Event> events = new ArrayList();
    private ArrayList<Node> direction=new ArrayList();
    private ArrayList<Integer> distance=new ArrayList();
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
