/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 * dv15nkn
 */

/**
 * An applicationclass for the rumorrouting classes
 */
public class Rumorrouting {

    // Program parameters

    //chance a node spawns an event. 1.0 = 100%,  0.0001 = 0.01%
    static final double eventChance = 0.0001;

    //maximum steps for agent and request
    static final int stepsAgent = 50;
    static final int stepsReq = 45;




    public static void main(String [] args){
        //Create a nodegrid with 50*50 nodes
        Environment env = new Environment(50, 50, stepsAgent, stepsReq, eventChance);
        //run the nodeenvironment 10000 ticks
        for(int i = 0;i < 10000;i++){
           env.timeTick();
        }

    }
}

