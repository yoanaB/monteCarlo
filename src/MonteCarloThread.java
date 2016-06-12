/**
 * Created by Yoana on 12.6.2016 г..
 */
public class MonteCarloThread extends Thread{
    private int nPoints;
    private double pointsInCircle;
    private static final double radius = 0.5;
    private int number;

    public MonteCarloThread(int nPoints, int number){
        super();
        this.nPoints = nPoints;
        this.pointsInCircle = 0;
        this.number = number;
    }

    private boolean inCircle(double x, double y){
        return Math.sqrt(Math.pow((x - radius), 2) + Math.pow((y - radius), 2)) <= radius;
    }

    public void run(){
        System.out.println("Thread " + this.number + " started");
        long start = System.currentTimeMillis();
        for(int j = 1; j <= nPoints; j++){
            double xCoordinate = Math.random();
            double yCoordinate = Math.random();
            if(inCircle(xCoordinate, yCoordinate)){
                pointsInCircle++;
            }
        }

        System.out.println("Thread " + this.number + " ended");
        System.out.println("Thread " + this.number + " executed for " + (System.currentTimeMillis() - start));
    }

    public void addPoints(int points){
        this.nPoints += points;
    }

    public double getPointsInCircle() {
        return pointsInCircle;
    }
}
