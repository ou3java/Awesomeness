/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */

/**
 * Route is a class that contains a specific node, distance and time.
 * The node is the neighbour node to get to a specific event with its distance
 * and time-integers
 */
public class Route {

    Node node;
    int distance;
    int time;

    /**
     * Constructor for Route
     * @param n The targetnode or neighbournode to get to get to the event
     * @param distance distance to the event by the node
     * @param time Timestep the event took place at
     */
    public Route(Node n, int distance, int time){
        this.node = n;
        this.distance = distance;
        this.time = time;
    }

    /**
     * Constructor for route. this creates a copy of an existing route
     * @param r The Routeobject to create the copy of.
     */
    public Route(Route r){
        this.node = r.node;
        this.distance = r.distance;
        this.time = r.time;
    }
}
