import java.util.Stack;

public class Request extends Messenger {

    private Stack<Node> wayBack = new Stack();
    private Event event;

    public Request(Node n, Event e){
        event=e;
        wayBack.push(n);
    }

    public void checkEvent(){

    }

    public void dumpInfo(){

    }
}
