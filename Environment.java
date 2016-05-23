/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * A class that simulates an environment of nodes in a grid. The nodes can
 * detect its neighbours in 8 directions.
 */
public class Environment {

    private int id=0;
    private int width;
    private int height;
    private int timeStep =0;
    protected ArrayList<Node> nodes;
    protected LinkedList<Messenger> messengers;
    protected ArrayList<Node> reqNodes;
    protected ArrayList<Boolean> events;

    /**
     * Constructor for Environment. the nodes will be placed in a grid and
     * the total number of nodes is width * height
     * @param width the number of columns
     * @param height the number of rows
     */
    public Environment(int width, int height){
        this.width = width;
        this.height = height;
        int k=0;
        nodes = new ArrayList();
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                nodes.add(k, new Node(new Position(y,x)));
                k++;
            }
        }
        //add neighbours to nodes for each neighbourdirection
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

    /**
     * Helpmethod for the constructor. used to fill each nodes neighbourlist
     * @param n The node to fill
     * @param p the nodes position
     */
    private void addNeighbour(Node n, Position p){
        int x = p.getX();
        int y = p.getY();

        if(x > -1 && x < width && y > -1 && y < height){
            n.neighbours.add(nodes.get(y * width + x));
        }
    }

    /**
     * This method calls all methods that is to be done in each
     * timestepsimultaion
     */
    public void timeTick(){

        timeStep++;
        //add an event in each node with a 0,01% probability
        Random rand = new Random();
        for(int i = 0; i < nodes.size(); i++){
            if(rand.nextInt(10000)==2){
                events.add(true);
                nodes.get(i).setEvent(id, this, timeStep);
                id++;
            }
        }
        //each 400th timestep a request is sent from the 4 preset nodes
        if(timeStep %400==0){
            for(Node n : reqNodes) {
                messengers.add(n.sendRequest(rand.nextInt(events.size()), n));
            }
        }
        //each messenger makes a move and is removed after its done
        for(Iterator<Messenger> iterator = messengers.iterator(); iterator.hasNext();){
            Messenger m = iterator.next();
            m.move();
            if(m.maxsteps <= 0){
                iterator.remove();
            }
        }
    }
}