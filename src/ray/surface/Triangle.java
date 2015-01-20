package ray.surface;

import ray.math.Point;
import ray.math.Ray;
import ray.math.Vector;

public class Triangle extends Surface {
    // These fields are read in from the input file.
    /* These points are the vertices of the triangle */
    protected final Point a = new Point();
    protected final Point b = new Point();
    protected final Point c = new Point();

    // PARSER METHODS
    public void setA (Point pt) {
        a.set(pt);
    }

    public void setB (Point pt) {
        b.set(pt);
    }

    public void setC (Point pt) {
        c.set(pt);
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
    public Vector getNormal (Point point) {
        // TODO: return vector representing this surface's normal at this point
        return new Vector();
    }
}
