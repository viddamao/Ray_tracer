package ray.shader;

import java.util.List;

import ray.Light;
import ray.Scene;
import ray.math.Color;
import ray.math.Point;
import ray.math.Vector;
import ray.surface.Surface;


/**
 * A Lambertian material scatters light equally in all directions.
 *
 * @author YOUR NAME HERE
 */
public class Lambertian implements Shader {
	// These fields are read in from the input file.
	/** The color of the surface. */
	protected final Color diffuseColor = new Color(1, 1, 1);

	// PARSER METHODS
	public void setDiffuseColor (Color inDiffuseColor) {
		diffuseColor.set(inDiffuseColor);
	}

	/**
	 * @see Shader#shade()
	 */
	@Override
	public Color shade (Point intersectPt, Surface surface, Scene scene) {
		// TODO: calculate the intensity of the light along this ray
		//    	Color output = new Color(0,0,0);
		Vector normal = surface.getNormal(intersectPt).normalize();//unit vector of normal
		Vector l = new Vector();//vector of light
		for(Light light :scene.getLights()){

			l.x += light.position.x - intersectPt.x; 
			l.y += light.position.y - intersectPt.y;
			l.z += light.position.z - intersectPt.z;

			l.normalize();

			diffuseColor.x  += diffuseColor.x * light.color.x * Math.max(0, l.dot(normal));
			diffuseColor.y  += diffuseColor.y * light.color.y * Math.max(0, l.dot(normal));
			diffuseColor.z  += diffuseColor.z * light.color.z * Math.max(0, l.dot(normal));
		}

		//        return diffuseColor.clamp(0, 1);
		return diffuseColor;
	}
}
