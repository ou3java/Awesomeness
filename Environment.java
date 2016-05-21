import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Environment {

    private int id=0;
    private int width;
    private int height;
    //protected ArrayList<Integer> regEvents;
    protected ArrayList<Node> nodes;
    protected LinkedList<Messenger> messengers;
    protected ArrayList<Node> reqNodes;
    protected ArrayList<Boolean> events;
    private int timeStep =0;

    public Environment(int width, int height){
        this.width = width;
        this.height = height;
        int k=0;
        //regEvents=new ArrayList();
        nodes = new ArrayList();
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                nodes.add(k, new Node(new Position(y,x)));
                k++;
            }
        }
        for(Node n : nodes){
            addNeighbour(n, n.p.getPosToEast());
            addNeighbour(n, n.p.getPosToWest());
            addNeighbour(n, n.p.getPosToSouth());
            addNeighbour(n, n.p.getPosToNorth());
            addNeighbour(n, n.p.getPosToSouthEast());
            addNeighbour(n, n.p.getPosToSouthWest());
            addNeighbour(n, n.p.getPosToNorthEast());
            addNeighbour(n, n.p.getPosToNorthWest());

        }
        events = new ArrayList<>();
        messengers = new LinkedList<>();
        reqNodes = new ArrayList<>();

        Random rand = new Random();
        reqNodes.add(nodes.get(rand.nextInt(nodes.size())));
        reqNodes.add(nodes.get(rand.nextInt(nodes.size())));
        reqNodes.add(nodes.get(rand.nextInt(nodes.size())));
        reqNodes.add(nodes.get(rand.nextInt(nodes.size())));
    }

    private void addNeighbour(Node n, Position p){
        int x = p.getX();
        int y = p.getY();

        if(x > -1 && x < width && y > -1 && y < height){
            n.neighbours.add(nodes.get(y * width + x));
        }
    }

    public void timeTick(){

        timeStep++;
        Random rand = new Random();
        for(int i = 0; i < nodes.size(); i++){
            if(rand.nextInt(10000)==2){
                events.add(true);
                nodes.get(i).setEvent(id, this);
                id++;
            }
        }
        if(timeStep %400==0){
            for(Node n : reqNodes) {
                //messengers.add(n.sendRequest(rand.nextInt(events.size()), n));
                messengers.add(n.sendRequest(rand.nextInt(events.size()), n));
            }
        }
        for(Iterator<Messenger> iterator = messengers.iterator(); iterator.hasNext();){
            Messenger m = iterator.next();
            m.move();
            if(m.maxsteps <= 0){
                iterator.remove();
            }
        }
    }


    public Node getNode(Position p){
        return nodes.get(p.getX()+width*p.getY());

    }
}