package mobility;

/**
 * The ILocatable interface provides methods for getting and setting the location of an object.
 */
public interface ILocatable {
    /**
     * Gets the current location of the object.
     *
     * @return the current location as a Point object
     */
    public Point getLocation();

    /**
     * Sets the location of the object to the specified point.
     *
     * @param p the new location as a Point object
     * @return true if the location is successfully set, false otherwise
     */
    public boolean setLocation(Point p);
}
