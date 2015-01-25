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
    	Vector normal = surface.getNormal(intersectPt);
    	List<Light> lightList = scene.getLights();
    	for(Light l :lightList){
    		
    	}
    	
        return diffuseColor;
    }
}
