package hr.fer.zemris.java.student1191227371.hw07;

import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.UnmodifiableObjectException;
import hr.fer.zemris.linearna.Vector;

import org.junit.Assert;
import org.junit.Test;

public class VectorTest {
	private static final double DELTA = 1e-8;
	
	private static void assertEquals(double expected, double actual) {
		Assert.assertEquals(expected, actual, DELTA);
	}
	
	private static void assertEquals(double[] expected, double[] actual) {
		Assert.assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; ++i) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	@Test
	public void constructorTest() {
		double[] data = {-1, 1, 3, 5};
		
		IVector v = new Vector(-1, 1, 3, 5);
		Assert.assertEquals(data.length, v.getDimension());
		assertEquals(data, v.toArray());
		
		v = new Vector(true, false, data);
		assertEquals(data, v.toArray());
	}
	
	@Test(expected=UnmodifiableObjectException.class)
	public void unmodifiableConstructorTest() {
		IVector v = new Vector(true, true, new double[] {1, 3, 5});
		v.set(1, 15.0);
	}
	
	@Test
	public void parseTest() {
		IVector v = Vector.parseSimple(" 1 3  4.0 4.3 7");
		double[] data = {1, 3, 4, 4.3, 7};
		assertEquals(data, v.toArray());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void parseFailTest() {
		Vector.parseSimple("1 3 5 a d b");
	}
	
	@Test
	public void copyTest() {
		double[] data = {3, 5, 7};
		IVector v = new Vector(data);
		IVector c = v.copy();
		assertEquals(data, c.toArray());
		c.set(1, 17);
		assertEquals(data, v.toArray());
		assertEquals(17.0, c.get(1));
	}
	
	@Test
	public void newInstanceTest() {
		IVector v = new Vector(true, true, new double[] {1, 3, 5});
		IVector n = v.newInstance(4);
		assertEquals(new double[] {0, 0, 0, 0}, n.toArray());
	}
	
	@Test
	public void operationsTest() {
		IVector v = new Vector(3.0, 5.2, 4.3);
		assertEquals(5.2, v.get(1));
		Assert.assertEquals(3, v.getDimension());
		
		v.set(1, 7.3);
		assertEquals(7.3, v.get(1));
	}
}
