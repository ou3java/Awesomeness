public class test {

    public static void main(String [] args){

        Environment env = new Environment(50, 50);
        for(int i = 0;i < 10000;i++){
           env.timeTick();
        }

    }
}
