package ray.surface;

import ray.math.Point;
import ray.math.Ray;
import ray.math.Vector;

/**
 * Represents a sphere as a center and a radius.
 *
 * @author YOUR NAME HERE
 */
public class Sphere extends Surface {
    // These fields are read in from the input file.
    /** The center of the sphere. */
    protected Point center = new Point();
    /** The radius of the sphere. */
    protected double radius = 1.0;

    // PARSER METHODS
    public void setCenter (Point center) {
        this.center.set(center);
    }

    public void setRadius (double radius) {
        this.radius = radius;
    }


    /**
     * @see Surface#getIntersection()
     */
    @Override
    public double[] getIntersection (Ray ray) {
        // TODO: return t values at which this ray intersects this surface
        return new double[0];
    }

    /**
     * @see Surface#getNormal()
     */
    @Override
    public Vector getNormal (Point pt) {
        // TODO: return vector representing this surface's normal at this point
        return new Vector();
    }
}
