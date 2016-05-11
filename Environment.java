import java.util.ArrayList;

public class Environment {

    private int id=0;
    private int width;
    private int height;
    public int happening;
    private ArrayList<Event> regEvents;
    protected ArrayList<Node> nodes;

    private void addNeighbour(Node n, Position p){
        int x = p.getX();
        int y = p.getY();

        if(x > -1 && x < width && y > -1 && y < height){
            n.neighbours.add(nodes.get(y * width + x));
            //System.out.printf("\n %d %d ", x , y);
        }
    }

    public Environment(int width, int height){
        this.width = width;
        this.height = height;
        int k=0;
        regEvents=new ArrayList();
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