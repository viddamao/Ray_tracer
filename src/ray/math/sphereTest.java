package ray.math;

import static org.junit.Assert.*;

import org.junit.Test;

import ray.surface.Sphere;

public class sphereTest {

    @Test
    public void test() {
	assertEquals("1", "1");
	double dx = 1, dy = 1, dz = 1, radius = 1.25;
	Point start = new Point(0, 0, 0), center = new Point(1, 1, 1);
	double a = SphereMath.getA(dx, dy, dz);
	double b = SphereMath.getB(dx, dy, dz, start, center);
	double c = SphereMath.getC(dx, dy, dz, start, center, radius);
	assertEquals(a, 3.0, 0.0001);
	assertEquals(b, -6, 0.0001);
	assertEquals(c, 1.4375, 0.0001);
	assertEquals(SphereMath.getT(a, b, c)[0], 1.7217, 0.01);
	assertEquals(SphereMath.getT(a, b, c)[1], 0.2783, 0.01);
	// Vector reflection test
	Vector vL = new Vector(1, 0, 1), normal = new Vector(0, 1, 0);
	assertEquals(SphereMath.getReflection(vL, normal).x, -1, 0.001);
	assertEquals(SphereMath.getReflection(vL, normal).y, 0, 0.001);
	assertEquals(SphereMath.getReflection(vL, normal).z, -1, 0.001);

    }

}
