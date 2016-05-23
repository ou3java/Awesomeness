/**
 * Objektorienterad programmeringsmetodik obligatorisk uppgift 3
 * Grupp 2
 * id15vjn
 * ens11jye
 * id15jnn
 */

/**
 * An applicationclass for the rumorrouting classes
 */
public class Rumorrouting {

    public static void main(String [] args){
        //Create a nodegrid with 50*50 nodes
        Environment env = new Environment(50, 50);
        //run the nodeenvironment 10000 ticks
        for(int i = 0;i < 10000;i++){
           env.timeTick();
        }

    }
}
