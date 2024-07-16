package mobility;

import static java.lang.Math.sqrt;

/**
 * The Mobile class represents an object that can move to different points in a 2D coordinate system.
 * It implements the ILocatable interface to provide location-related functionalities.
 */
public class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;


    /**
     * Constructs a Mobile object with the specified initial location and total distance.
     *
     * @param location      the initial location of the object
     * @param totalDistance the total distance the object has traveled
     */
    public Mobile(Point location, double totalDistance) {
        this.location = location;
        this.totalDistance = totalDistance;
    }
    public Mobile() {
        this.location = new Point(0, 0);
        this.totalDistance = 0;
    }

    /**
     * Adds the specified distance to the total distance traveled by the object.
     *
     * @param distance the distance to add
     */
    public void addTotalDistance(double distance) {
        totalDistance += distance;
    }

    /**
     * Calculates the distance between the current location and the specified location.
     *
     * @param locationB the location to calculate the distance to
     * @return the distance between the current location and locationB
     */
    public double calcDistance(Point locationB) {
        return sqrt((location.getY() - locationB.getY()) * (location.getY() - locationB.getY()) +
                (location.getX() - locationB.getX()) * (location.getX() - locationB.getX()));
    }

    /**
     * Moves the object by updating its location and adding the distance to the total distance traveled.
     *
     * @param distance the distance to move
     * @return the updated total distance traveled
     */
    public double move(Point distance) {
        if (distance.getX() < 0 || distance.getY() < 0) {
            return totalDistance;
        }
        double distances = calcDistance(distance);
        addTotalDistance(distances);
        location.setX(distance.getX());
        location.setY(distance.getY());
        return totalDistance;
    }

    /**
     * Gets the current location of the object.
     *
     * @return the current location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Sets the location of the object to the specified point if the point is valid.
     * Adds the distance traveled to the total distance.
     *
     * @param p the new location
     * @return true if the location is successfully set, false otherwise
     */
    public boolean setLocation(Point p) {
        if (p.getX() < 0 || p.getY() < 0) {
            return false;
        }
        double distance = calcDistance(p);
        addTotalDistance(distance);
        location.setX(p.getX());
        location.setY(p.getY());
        return true;
    }

    /**
     * Returns a string representation of the Mobile object.
     *
     * @return a string describing the location and total distance traveled
     */
    @Override
    public String toString() {
        return "location=" + location + ", totalDistance=" + totalDistance;
    }

    /**
     * Checks if this object is equal to another object.
     * Two Mobile objects are considered equal if they have the same location and total distance traveled.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && location.equals(((Mobile) obj).location) && totalDistance == ((Mobile) obj).totalDistance;
    }
}
