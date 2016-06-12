import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yoana on 12.6.2016 г..
 */
public class MonteCarloRunner {
    private int nPoints;
    private int countThreads;

    public MonteCarloRunner(int nPoints, int countThreads){
        this.nPoints = nPoints;
        this.countThreads = countThreads;
    }

    public void run(){
        long start = System.currentTimeMillis();
        List<MonteCarloThread> threadList = new ArrayList<>();
        for(int i = 1; i <= countThreads; i++){
            threadList.add(new MonteCarloThread(nPoints / countThreads, i));
        }

        threadList.get(0).addPoints(nPoints % countThreads);

        threadList.stream().forEach(thread -> thread.start());
        threadList.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        });

        double pointsInCircle = threadList.stream().map(thread -> thread.getPointsInCircle()).reduce(0.0, (a, b) -> (a + b));

        double Pi = 4.0 * pointsInCircle/nPoints;

        System.out.println("Pi: " + Pi);
        System.out.println("Execution time for the program " + (System.currentTimeMillis() - start));
    }
}
