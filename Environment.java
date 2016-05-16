import java.util.ArrayList;
import java.util.Random;

public class Environment {

    private int id=0;
    private int width;
    private int height;
    //protected ArrayList<Integer> regEvents;
    protected ArrayList<Node> nodes;
    private ArrayList<Agent> agents;
    private ArrayList<Request> requests;
    private int happening=0;

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
    }

    private void addNeighbour(Node n, Position p){
        int x = p.getX();
        int y = p.getY();

        if(x > -1 && x < width && y > -1 && y < height){
            n.neighbours.add(nodes.get(y * width + x));
            //System.out.printf("\n %d %d ", x , y);
        }
    }

    public void timeTick(){
        happening++;
        boolean ifEvent;
        Random rand=new Random();
        for(int i=0; i<width*height; i++){
            ifEvent = nodes.get(i).setEvent(id);
            if(ifEvent) {
               //regEvents.add(id);
               if(rand.nextInt(1)==1){
                  agents.add(nodes.get(i).sendAgent(id, nodes.get(i)));
               }

            id++;
            }
        }
        if(happening%400==0){
           for(int i= 0; i<4;i++) {
               int randomNode = rand.nextInt(width * height);
               requests.add(nodes.get(randomNode).sendRequest(rand.nextInt(id), nodes.get(randomNode)));
           }
        }
    }


    public Node getNode(Position p){
        return nodes.get(p.getX()+50*p.getY());

    }
}
