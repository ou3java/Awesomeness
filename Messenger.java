/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */
import java.util.LinkedList;

/**
 * This is a parentclass for agent and request.
 */
public class Messenger {

    protected int maxsteps;
    protected Node nodesToVisit=null;
    protected Node currentNode;
    protected LinkedList<Node> visitedNodes = new LinkedList<>();
    //protected Set<Node> visitedNodes = new Set<Node>();
    public Messenger(){
    }

    public void move(){
    }
}
