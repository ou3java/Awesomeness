public class Event {

    private int happening;
    private int id;
    private Node n;

    public Event(Node N, Environment e){
        n=N;
        happening = e.happening;
        id=e.createEventId();
    }
}
