package ray.shader;

import java.util.List;

import ray.Light;
import ray.Scene;
import ray.math.Color;
import ray.math.Point;
import ray.math.Ray;
import ray.math.Vector;
import ray.surface.Surface;

/**
 * A Lambertian material scatters light equally in all directions.
 *
 * @author Wei
 */
public class Lambertian implements Shader {
    // These fields are read in from the input file.
    /** The color of the surface. */
    protected final Color diffuseColor = new Color(1, 1, 1);

    // PARSER METHODS
    public void setDiffuseColor(Color inDiffuseColor) {
	diffuseColor.set(inDiffuseColor);
    }

    /**
     * @see Shader#shade()
     */
    @Override
    public Color shade(Point intersectPt, Surface surface, Scene scene) {
	// TODO: calculate the intensity of the light along this ray
	// Color output = new Color(0,0,0);
	Vector normal = surface.getNormal(intersectPt).normalize();// unit
								   // vector of
								   // normal
	Vector l = new Vector();// vector of light
	Color output = new Color(0, 0, 0);

	for (Light light : scene.getLights()) {

	    l = light.position.sub(intersectPt);
	    l.normalize();

	    boolean flag = false;
	    Ray currentRay = new Ray(intersectPt, l);
	    for (Surface s : scene.getSurfaces()) {
		if (s != surface)
		    if (s.intersects(currentRay)) {
			flag = true;
			break;
		    }
	    }
	    if (!flag) {
		output.x += diffuseColor.x * light.color.x
			* Math.max(0, l.dot(normal));
		output.y += diffuseColor.y * light.color.y
			* Math.max(0, l.dot(normal));
		output.z += diffuseColor.z * light.color.z
			* Math.max(0, l.dot(normal));
	    } else {
		output.x += 0.1 * diffuseColor.x * light.color.x
			* Math.max(0, l.dot(normal));
		output.y += 0.1 * diffuseColor.y * light.color.y
			* Math.max(0, l.dot(normal));
		output.z += 0.1 * diffuseColor.z * light.color.z
			* Math.max(0, l.dot(normal));

	    }
	}

	// return diffuseColor.clamp(0, 1);
	return output.add(AMBIENT_LIGHT_COLOR.scale(0.05));
    }
}
