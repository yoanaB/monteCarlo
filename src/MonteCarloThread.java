import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Yoana on 12.6.2016 г..
 */
public class MonteCarloThread implements Runnable{
    private int nPoints;
    private double pointsInCircle;
    private static final double radius = 0.5;
    private int threadNumber;
    private boolean isQuiet = false;
    private final RandomGenerator generator;

    public MonteCarloThread(int nPoints, int threadNumber, boolean isQuiet, RandomGenerator generator){
        super();
        this.nPoints = nPoints;
        this.pointsInCircle = 0;
        this.threadNumber = threadNumber;
        this.isQuiet = isQuiet;
        this.generator = generator;
    }

    private boolean inCircle(double x, double y){
        return Math.sqrt(Math.pow((x - radius), 2) + Math.pow((y - radius), 2)) <= radius;
    }

    public void run(){
        if(!isQuiet){
            System.out.println("Thread-<" + threadNumber + "> started.");
        }
        long start = System.currentTimeMillis();
        double xCoordinate, yCoordinate;
        for(int j = 1; j <= this.nPoints; j++){
            xCoordinate = this.generator.generate();
            yCoordinate = this.generator.generate();
            if(inCircle(xCoordinate, yCoordinate)){
                pointsInCircle++;
            }
        }

        if (!this.isQuiet) {
            System.out.println("Thread-<" + threadNumber + "> stopped.");
            System.out.println("Thread-<" + threadNumber + "> execution time was (millis): <"
                    + (System.currentTimeMillis() - start) + ">");
        }
    }

    public void addPoints(int points){
        this.nPoints += points;
    }

    public double getPointsInCircle() {
        return pointsInCircle;
    }
}
