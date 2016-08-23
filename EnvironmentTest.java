import org.junit.Assert;
import org.junit.Test;

/**
 * Created by konig on 2016-08-23.
 */
public class EnvironmentTest {

    @Test
    public void NeighbourPositionTest() {
        //new Environment 3x3
        Environment env = new Environment(3, 3, 50, 1, 0.0);

        //making sure each node has correct nr of neighbours
        //Top left
        Assert.assertEquals(env.nodes.get(0).neighbours.size(), 3);
        //top
        Assert.assertEquals(env.nodes.get(1).neighbours.size(), 5);
        //top right
        Assert.assertEquals(env.nodes.get(2).neighbours.size(), 3);
        //left
        Assert.assertEquals(env.nodes.get(3).neighbours.size(), 5);
        //middle
        Assert.assertEquals(env.nodes.get(4).neighbours.size(), 8);
        //right
        Assert.assertEquals(env.nodes.get(5).neighbours.size(), 5);
        //bottom left
        Assert.assertEquals(env.nodes.get(6).neighbours.size(), 3);
        //bottom
        Assert.assertEquals(env.nodes.get(7).neighbours.size(), 5);
        //bottom right
        Assert.assertEquals(env.nodes.get(8).neighbours.size(), 3);

        //make sure top Left position is northwest of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToNorthWest().getX(), env.nodes.get(0).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToNorthWest().getY(), env.nodes.get(0).p.getY());
        //make sure top position is north of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToNorth().getX(), env.nodes.get(1).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToNorth().getY(), env.nodes.get(1).p.getY());
        //make sure top right position is northeast of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToNorthEast().getX(), env.nodes.get(2).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToNorthEast().getY(), env.nodes.get(2).p.getY());
        //make sure left position is west of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToWest().getX(), env.nodes.get(3).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToWest().getY(), env.nodes.get(3).p.getY());
        //make sure right position is east of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToEast().getX(), env.nodes.get(5).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToEast().getY(), env.nodes.get(5).p.getY());
        //make sure bottom left position is southwest of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToSouthWest().getX(), env.nodes.get(6).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToSouthWest().getY(), env.nodes.get(6).p.getY());
        //make sure bottom position is south of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToSouth().getX(), env.nodes.get(7).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToSouth().getY(), env.nodes.get(7).p.getY());
        //make sure bottom  right position is southeast of middle position
        Assert.assertEquals(env.nodes.get(4).p.getPosToSouthEast().getX(), env.nodes.get(8).p.getX());
        Assert.assertEquals(env.nodes.get(4).p.getPosToSouthEast().getY(), env.nodes.get(8).p.getY());
    }
}