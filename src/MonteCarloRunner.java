import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import java.util.Map;

/**
 * Created by Yoana on 12.6.2016 г..
 */
public class MonteCarloRunner {
  /*  private int nPoints;
    private int countThreads;*/

    private final Map<String, Integer> params;

    public MonteCarloRunner(String[] args) {
        this.params = parseArguments(args);
    }

    public MonteCarloRunner(Map<String, Integer> params) {
        this.params = params;
    }


 /*   public MonteCarloRunner(int nPoints, int countThreads) {
        this.nPoints = nPoints;
        this.countThreads = countThreads;
    }*/

    private static Map<String, Integer> parseArguments(String[] args) {
        Map<String, Integer> params = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-t":
                    params.put("threads", new Integer(args[i + 1]));
                    break;
                case "-s":
                    params.put("points", new Integer(args[i + 1]));
                    break;
                case "-q":
                    params.put("quiet", 1);
                    break;
                case "-mr":
                    params.put("math.random",1);
            }
        }
        if (!params.containsKey("quiet")) {
            params.put("quiet", 0);
        }
        if(!params.containsKey("math.random")){
            params.put("math.random",0);
        }
        return params;
    }

    public void run() {
        int countThreads = params.get("threads");
        int nPoints = params.get("points");
        boolean isQuiet = params.get("quiet") == 1;
        boolean wantsMathRandomGenerator = params.get("math.random")==1;
        RandomGenerator generator = wantsMathRandomGenerator?new MathRandomGenerator():new ThreadSafeRandomGenerator();
        long start = System.currentTimeMillis();
        List <MonteCarloThread> tasks = new ArrayList<>();
        for (int i = 1; i <= countThreads; i++) {
            tasks.add(new MonteCarloThread(nPoints / countThreads, i + 1, isQuiet, generator));
        }

        tasks.get(0).addPoints(nPoints % countThreads);
        List <Thread> threadList = new ArrayList<>();
        tasks.stream().forEach(task->threadList.add(new Thread(task)));
        threadList.stream().forEach(thread -> thread.start());
        threadList.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        });

        double pointsInCircle = tasks.stream().map( thread -> thread.getPointsInCircle()).reduce(0.0, (a, b) -> (a + b));

        double Pi = 4.0 * pointsInCircle / nPoints;

        System.out.println("Pi: " + Pi);
        System.out.println("Execution time for the program " + (System.currentTimeMillis() - start));

        if (!isQuiet) {
            System.out.println("Threads used in current run: <" + countThreads + ">");
        }
    }
}
