package mobility;

import Graphics.IClonable;

/**
 * The Point class represents a point in a 2D coordinate system.
 * It includes the x and y coordinates of the point.
 */
public class Point implements IClonable {
    private int x;
    private int y;

    /**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(int x, int y)
    {

        this.x = x;
        this.y = y;
    }
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Checks if this point is equal to another object.
     * Two points are considered equal if they have the same x and y coordinates.
     *
     * @param p the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object p) {
        boolean result = false;
        if (p instanceof Point) {
            result = (x == ((Point) p).x) && (y == ((Point) p).y);
        }
        return result;
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string describing the point in the format (x, y)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Gets the x coordinate of the point.
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate of the point.
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x coordinate of the point.
     *
     * @param x the new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the point.
     *
     * @param y the new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
