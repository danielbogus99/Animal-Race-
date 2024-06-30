package mobility;

import javax.swing.text.Position;

public class Mobile implements ILocatable
{
    private Point location;
    private double totalDistance;


    public Mobile(Point location, double totalDistance){}
    public void addTotalDistance(double distance){}
    public double calcDistance(Point locationB){}
    public double move(Point distance){}
    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public boolean setLocation(Point p) {
        return false;
    }
}
