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

       return totalDistance;
    }
    @Override
    public Point getLocation()
    {
        return location;
    }

    @Override
    public boolean setLocation(Point p)
    {
        if(p.getX() < 0 || p.getY() < 0)
        {
            return false;
        }
        double distance=calcDistance(p);
        addTotalDistance(distance);
        location.setX(p.getX());
        location.setY(p.getY());
        return true;
     }
}
