package mobility;



import static java.lang.Math.sqrt;

public class Mobile implements ILocatable
{
    private Point location;
    private double totalDistance;


    public  Mobile(Point location, double totalDistance)
    {
        this.location = location;
        this.totalDistance = totalDistance;
    }
    public void addTotalDistance(double distance)
    {
        totalDistance = totalDistance + distance;
    }
    public double calcDistance(Point locationB)
    {
        return sqrt((location.getY() - locationB.getY()) * (location.getY() - locationB.getY()) + (location.getX() - locationB.getX()) * location.getX() - locationB.getX());
    }
    public double move(Point distance)
    {
       
    }
    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public boolean setLocation(Point p) {return false;
    }
}
