
/**
 * Created by Yoana on 12.6.2016 г..
 */
public class Main {
    public static void main(String[] Args){

       // MonteCarloRunner runner = new MonteCarloRunner(20000000, 40);
        MonteCarloRunner runner = new MonteCarloRunner(new Integer(Args[0]), new Integer(Args[1]));
        runner.run();
    }
}
