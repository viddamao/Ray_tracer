package ray.shader;

import ray.Light;
import ray.Scene;
import ray.math.Color;
import ray.math.Point;
import ray.math.SphereMath;
import ray.math.Vector;
import ray.surface.Surface;

/**
 * A Phong material uses the modified Blinn-Phong model which is energy
 * preserving and reciprocal.
 * 
 * Note, all specular materials also contain some diffuse reflection, so extend
 * that shader to include it in the calculation.
 *
 * @author Wenjun
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
		Vector normal = surface.getNormal(intersectPt).normalize();//unit vector of normal
		Vector  vL = new Vector(),//vector of light
				vR= new Vector(),//reflection
				vE=new Vector();//Camera
		Color output=new Color(0,0,0);
		for(Light light :scene.getLights()){

			vL=light.position.sub(intersectPt);
			vL.normalize();

			vR=SphereMath.getReflection(vL, normal);
			vR.normalize();

			vE=scene.getCamera().viewPoint.sub(intersectPt);
			vE.normalize();

			output.x  += diffuseColor.x * light.color.x * Math.max(0, vL.dot(normal));
			output.y  += diffuseColor.y * light.color.y * Math.max(0, vL.dot(normal));
			output.z  += diffuseColor.z * light.color.z * Math.max(0, vL.dot(normal));



			output.x  += specularColor.x * light.color.x * Math.pow((Math.max(0, vE.dot(vR))),exponent);
			output.y  += specularColor.y * light.color.y * Math.pow((Math.max(0, vE.dot(vR))),exponent);
			output.z  += specularColor.z * light.color.z * Math.pow((Math.max(0, vE.dot(vR))),exponent);

		}


		return output.add(AMBIENT_LIGHT_COLOR.scale(0.05));
	}
}
