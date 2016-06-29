
/**
 * Created by Yoana on 12.6.2016 г..
 */
public class Main {
    public static void main(String[] args){

       MonteCarloRunner runner = new MonteCarloRunner(args);
        //MonteCarloRunner runner = new MonteCarloRunner(new Integer(Args[0]), new Integer(Args[1]));
        runner.run();
    }
}
