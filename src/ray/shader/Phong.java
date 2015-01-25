package ray.shader;

import ray.Scene;
import ray.math.Color;
import ray.math.Point;
import ray.surface.Surface;

/**
 * A Phong material uses the modified Blinn-Phong model which is energy
 * preserving and reciprocal.
 * 
 * Note, all specular materials also contain some diffuse reflection, so extend
 * that shader to include it in the calculation.
 *
 * @author YOUR NAME HERE
 */
public class Phong extends Lambertian {
    // These fields are read in from the input file.
    /** The color of the specular reflection. */
    protected final Color specularColor = new Color(1, 1, 1);
    /** The exponent controlling the sharpness of the specular reflection. */
    protected double exponent = 1.0;

    // PARSER METHODS
    public void setSpecularColor(Color specularColor) {
	this.specularColor.set(specularColor);
    }

    public void setExponent(double exponent) {
	this.exponent = exponent;
    }

    /**
     * @see Shader#shade()
     * 
     *      Don't forget to call super.shade() to include that color component
     *      as well.
     */
    @Override
    public Color shade(Point intersectPt, Surface surface, Scene scene) {
	// TODO: calculate the intensity of the light along this ray
	return specularColor.add(super.shade(intersectPt, surface, scene));
    }
}
