import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Agent extends Messenger {

    //private ArrayList<Node> events = new ArrayList();
    protected HashMap<Integer, Route> routTable = new HashMap<>();
    private Node lastNode;
    private Node currentNode;
    //private ArrayList<Integer> distance = new ArrayList();

    public Agent(int id, Node n) {

        routTable.put(id, new Route(n, 0));
        currentNode = n;
        maxsteps = 50;
        currentNode.visiting = true;
    }

    public void addEvent(Node n) {

        //för varje nyckel i agents routtable...
        for(int id : this.routTable.keySet()){
            //...som inte finns i nodens routtable...
            if(!n.routTable.containsKey(id)){
                //..stoppa in i nodens routtable. (här måste man göra en ny rout av nån anledning
                n.routTable.put(id, new Route(this.routTable.get(id)));
            }
            //..som finns i nodens routtable men har högre distance...
            else if(this.routTable.get(id).distance < n.routTable.get(id).distance){
                //...ersätt nodens rout med agents
                n.routTable.put(id, new Route(this.routTable.get(id)));
            }
        }
    }

    public void getEvent(Node n) {
        //för varje nyckel i nodens routtable...
        for(int id : n.routTable.keySet()){
            //...som inte finns i agentens routtable...
            if(!this.routTable.containsKey(id)){
                //stoppa in i agentens routtable
                this.routTable.put(id, new Route(n.routTable.get(id)));
            }
            //...som finns i agentens routtable men har högre distance...
            else if(n.routTable.get(id).distance < this.routTable.get(id).distance){
                //...ersätt agentens rout med nodens
                this.routTable.put(id, new Route(n.routTable.get(id)));
            }
        }

    }

    @Override // OFÄRDIG
    public void move() {

        //Om det inte finns en nod att gå till i nodesToVisit...
        if (nodesToVisit == null) {
            //..välj en nod från grannlistan och stoppa in i nodesToVisit
            //***på nått sätt måste vi få den att inte räkna med noden den kom ifrån vilket är ogjort***
            Node u;
            Random rand = new Random();
            u = currentNode.neighbours.get(rand.nextInt(currentNode.neighbours.size()));
            nodesToVisit = u;
        }
        //Om noden i nodesToVisit inte är upptagen...
        if (!nodesToVisit.visiting) {
            //ändra nod och distance i routtable och gör förflyttningen efter
            for(int id : this.routTable.keySet()){
                this.routTable.get(id).node = nodesToVisit;
                this.routTable.get(id).distance++;
            }
            lastNode = currentNode;
            currentNode = nodesToVisit;
            currentNode.visiting = true;
            maxsteps--;
            //och uppdatera tabellerna med den nya noden
            addEvent(currentNode);
            getEvent(currentNode);
        //ingen förändring sker i aktuellt timetick om den redan har en nod i nodesToVisit och den är upptagen
        }
    }
}