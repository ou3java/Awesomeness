

//Route består av en specifik nod och ett tillhörande distance....som man kan stoppa
//i movetabellerna i stället för att ändra i 2 tabeller( direction och distance)
public class Route {

    Node node;
    int distance;
    int time;

    public Route(Node n, int distance, int time){
        this.node = n;
        this.distance = distance;
        this.time = time;
    }
    //en constructor som skapar en kopia av en rout....nått med objektorientering
    //har inte klurat igenom varför man måste riktigt...ska stirra vidare på
    //varför de måste göras i addevent
    public Route(Route r){
        this.node = r.node;
        this.distance = r.distance;
        this.time = r.time;
    }
}
