package hr.fer.zemris.java.fractals.sphere;

import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Ray;
import hr.fer.zemris.java.raytracer.model.Sphere;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testovi metode za trazenje sjecista sfere i vektora
 * @author jelena Drzaic
 *
 */
public class SphereRayIntersectionTest {

	private static final double DELTA = 0.001;
	Sphere sphere = new Sphere(new Point3D(), 1.0, 0.5, 0.5, 0.5, 0.5, 
			0.5, 0.5, 1);
	Ray ray1 = new Ray(new Point3D(-5, 0, 0), new Point3D(1, 0, 0));
	
	@Test
	public void SphereRayIntersectionTestUnitSphere1() {
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(4.0, dist, DELTA);
	}
	
	@Test
	public void SphereRayIntersectionTestUnitSphere2() {
		Ray ray1 = new Ray(new Point3D(0, 0, 0), new Point3D(1, 0, 0));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(1.0, dist, DELTA);
	}
	
	@Test
	public void SphereRayIntersectionTestUnitSphere3() {
		Ray ray1 = new Ray(new Point3D(-5, 1, 0), new Point3D(1, 0, 0));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(5.0, dist, DELTA);
	}

	@Test
	public void SphereRayIntersectionTestUnitSphereTangent() {
		Ray ray1 = new Ray(new Point3D(0, 0, 0), new Point3D(1, 0, 0));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(1.0, dist, DELTA);
	}
	
	@Test
	public void SphereRayIntersectionTestUnitSphere4() {
		Ray ray1 = new Ray(new Point3D(0, 0, -5), new Point3D(0, 0, 1));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(4.0, dist, DELTA);
	}
	
	/**
	 * sjeciste s sferom s centrom (-1,0,0)
	 */
	@Test(expected=NullPointerException.class)
	public void SphereRayIntersectionTestSphere() {
		Sphere sphere = new Sphere(new Point3D(-1,0,0), 1.0, 0.5, 0.5, 0.5, 0.5, 
				0.5, 0.5, 1);
		Ray ray1 = new Ray(new Point3D(5, 0, 0), new Point3D(0, 0, 1));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
	}
	
	@Test(expected=NullPointerException.class)
	public void SphereRayIntersectionTestUnitSphereNull2() {
		Ray ray1 = new Ray(new Point3D(1, 0, 0), new Point3D(1, 0, 0));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(0.0, dist, DELTA);
	}
	
	@Test(expected=NullPointerException.class)
	public void SphereRayIntersectionTestUnitSphereNull() {
		Ray ray1 = new Ray(new Point3D(5, 0, 0), new Point3D(1, 0, 0));
		double dist = sphere.findClosestRayIntersection(ray1).getDistance();
		Assert.assertEquals(4.0, dist,DELTA);
	}
}
