import java.util.Stack;

public class Request extends Messenger {

    private Stack<Node> wayBack = new Stack();
    private int event;

    public Request(Integer id, Node n){
        event = id;
        maxsteps=45;
        wayBack.push(n);
        wayBack.peek().visiting = true;
    }

    public void checkEvent(){

    }

    public void dumpInfo(){

    }
}