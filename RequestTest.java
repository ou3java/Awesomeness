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

public class RequestTest {



    @Test
    public void requestRunOutOfMoves() {
        //new Environment 1x2
        Environment env = new Environment(1, 2, 50, 1, 0.0001);
        //new request with 1 moves in node 1,1
        Request request = new Request(1, env.nodes.get(0), 1);
        request.timeoutCounter = 50;

        //let the request move its single move
        request.move();

        //The first node should not be flagged as visited but the second one should
        Assert.assertEquals(env.nodes.get(0).visiting, false);
        Assert.assertEquals(env.nodes.get(1).visiting, true);

        //move if able (should not)
        request.move();

        //both nodes should be free
        Assert.assertEquals(env.nodes.get(0).visiting, false);
        Assert.assertEquals(env.nodes.get(1).visiting, false);

    }

    @Test
    public void requestTimeOutResend() {

        //new Environment 1x2
        Environment env = new Environment(1, 2, 50, 1, 0.0001);
        //new request with 1 moves in node 1,1
        Request request = new Request(1, env.nodes.get(0), 1);
        request.startNode = env.nodes.get(0);
        //let the request move its single move
        request.move();

        //The first node should not be flagged as visited but the second one should
        Assert.assertEquals(env.nodes.get(0).visiting, false);
        Assert.assertEquals(env.nodes.get(1).visiting, true);

        //move and check that both nodes are free
        request.move();
        Assert.assertEquals(env.nodes.get(0).visiting, false);
        Assert.assertEquals(env.nodes.get(1).visiting, false);

        //move the remaining 7 times to trigger the resend
        for(int i = 0; i < 7; i++) {
            request.move();
        }
        //the request should be in the first node
        Assert.assertEquals(env.nodes.get(0).visiting, true);
        Assert.assertEquals(env.nodes.get(1).visiting, false);

        request.maxsteps = 0;
        for(int i = 0; i < 8; i++) {
            request.move();
        }
        //the request should not be resent
        Assert.assertEquals(env.nodes.get(0).visiting, false);
        Assert.assertEquals(env.nodes.get(1).visiting, false);
    }

    @Test
    public void requestFindEvent() {
        //new Environment 1x2
        Environment env = new Environment(1, 2, 50, 45, 0.0);
        //new request in node 1,1
        Request request = new Request(1, env.nodes.get(0), 45);
        request.found = false;
        //add event in node 1,2 without sending agent
        env.nodes.get(1).chanceToSend = 0;
        env.nodes.get(1).setEvent(1, env, 10);

        //The request should not have found its event prior to the move
        Assert.assertEquals(request.found, false);

        //let the request move to the node with the event
        request.move();

        //The request should have found its event
        Assert.assertEquals(request.found, true);

        //making sure the request heads back using its wayback stack
        Assert.assertEquals(request.wayBack.size(), 1);
        request.move();
        Assert.assertEquals(request.wayBack.size(), 0);


    }

}

