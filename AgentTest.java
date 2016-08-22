/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */

import org.junit.Assert;
import org.junit.Test;

public class AgentTest {

    @Test
    public void testTableUpdateShortetstDistance() {

        //the agents node at pos 1,1
        Node node = new Node(new Position(1,1));
        //the second node at 2,2
        Node node2 = new Node(new Position(2,2));
        //second node gets event 1 and 2 with distance 2
        node2.routTable.put(1,new Route(node2,2,2));
        node2.routTable.put(2,new Route(node2,2,2));
        //agent get event 1 with distance 0 and event 2 with distance 3
        Agent agent = new Agent(1, node, 1, 50);
        agent.routTable.put(2,new Route(node2,3,2));
        //exchange event information and check distance

        //At node2 event 1s distance is expected to be 2
        Assert.assertEquals(2, node2.routTable.get(1).distance);
        agent.addEvent(node2);
        //node2s event 1 should have distance 0
        Assert.assertEquals(0, node2.routTable.get(1).distance);
        //agents event 2 should have distance 3
        Assert.assertEquals(3, agent.routTable.get(2).distance);
        agent.getEvent(node2);
        //agents event 2 should have distance 2
        Assert.assertEquals(2, node2.routTable.get(2).distance);


    }

    @Test
    public void agentRunOutOfMoves() {

        Environment env = new Environment(1, 55, 50, 45, 0.0001);
        Agent agent = new Agent(1, env.nodes.get(0), 1, 50);
        //let the agent move 60 times if able
        for(int i = 0; i < 60;i++){
            agent.move();
        }
        //the agent should only have spread information to 50 nodes
        Assert.assertEquals(env.nodes.get(54).routTable.size(), 0);
        Assert.assertEquals(env.nodes.get(1).routTable.size(), 1);

        Assert.assertEquals(0, agent.maxsteps);
    }

    @Test
    public void agentChooseUnvisited() {

        Environment env = new Environment(3, 3, 50, 45, 0.0001);
        //create agent in the middle of 3x3 grid
        Agent agent = new Agent(1, env.nodes.get(4), 1, 50);

        int i;
        Node n;
        //make all neighbours except one visited
        for(i = 0;i < 7;i++) {
            n = agent.currentNode.neighbours.get(i);
            agent.visitedNodes.add(n);
        }
        //make sure the agent chooses the remaining node
        n = agent.currentNode.neighbours.get(7);
        agent.move();
        Assert.assertEquals(agent.currentNode, n);
    }

}
