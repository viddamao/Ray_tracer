package ray.shader;

import ray.Light;
import ray.Scene;
import ray.math.Color;
import ray.math.Point;
import ray.math.Ray;
import ray.math.Vector;
import ray.surface.Surface;

public class Glazed extends Lambertian{
	// These fields are read in from the input file.
	/** The color of the surface. */
	protected final Color diffuseColor = new Color(1, 1, 1);

	// PARSER METHODS
	public void setDiffuseColor (Color inDiffuseColor) {
		diffuseColor.set(inDiffuseColor);
	}

	@Override
	public Color shade(Point intersectPt, Surface surface, Scene scene) {
		// TODO Auto-generated method stub
		Color output = new Color(0,0,0);
		Vector n = surface.getNormal(intersectPt).normalize();
		Vector l = new Vector();//vector of light

		Vector v=new Vector(intersectPt.sub(scene.getCamera().viewPoint)).normalize();
		Vector r = n.scale((n.dot(v))*2).sub(v);

		Color newColor = trace(new Ray(intersectPt,r),surface,scene);
		
		for(Light light :scene.getLights()){

			l.x = light.position.x - intersectPt.x; 
			l.y = light.position.y - intersectPt.y;
			l.z = light.position.z - intersectPt.z;

			l.normalize();

			output.x  += diffuseColor.x * light.color.x * Math.max(0, l.dot(n));
			output.y  += diffuseColor.y * light.color.y * Math.max(0, l.dot(n));
			output.z  += diffuseColor.z * light.color.z * Math.max(0, l.dot(n));
		}

		//        return diffuseColor.clamp(0, 1);
		return output.add(newColor);
	}

	private Color trace(Ray ray, Surface surface,Scene scene) {
		// TODO: complete this method, you may want to add parameters to it if
		// you implement recursion

		Surface closest = null;
		double tmin = Double.MAX_VALUE;

		for (Surface s : scene.getSurfaces()) 
			if (!s.equals(surface)){
				if (s.intersects(ray)) {
					double[] tvals = s.getIntersection(ray);
					if (tvals.length == 1 && tmin >= tvals[0]) {
						tmin = tvals[0];
						closest = s;
					}
					if (tvals.length == 2 && tmin >= Math.min(tvals[0], tvals[1])) {
						tmin = Math.min(tvals[0], tvals[1]);
						closest = s;
					}
				}
			}

		if (closest == null) {
			return new Color();
//					surface.getShader().shade(ray.evaluate(tmin), surface, scene);
		} else {

			return closest.getShader()
					.shade(ray.evaluate(tmin), closest, scene);
		}
	}

}
