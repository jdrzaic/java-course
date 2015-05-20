package hr.fer.zemris.java.student1191227371.hw07;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;
import hr.fer.zemris.linearna.IncompatibleOperandException;

import org.junit.Test;
import org.junit.Assert;

public class AbstractVectorTest {
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
	
	private static void assertEquals(double[][] expected, double[][] actual) {
		Assert.assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; ++i) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	@Test
	public void addTest() {
		IVector a = new Vector(1, 2, 3);
		a.add(new Vector(4, 2, 1));
		assertEquals(new double[] {5, 4, 4}, a.toArray());
	}
	
	@Test
	public void nAddTest() {
		double[] data = {1, 2, 3};
		IVector a = new Vector(data);
		assertEquals(new double[] {5, 4, 4}, a.nAdd(new Vector(4, 2, 1)).toArray());
		assertEquals(data, a.toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nAddExceptionTest() {
		new Vector(1, 2, 3).nAdd(new Vector(1, 2));
	}
	
	@Test
	public void subTest() {
		IVector a = new Vector(1, 2, 3);
		a.sub(new Vector(0, 2, 1));
		assertEquals(new double[] {1, 0, 2}, a.toArray());
	}
	
	@Test
	public void nSubTest() {
		double[] data = {1, 2, 3};
		IVector a = new Vector(data);
		assertEquals(new double[] {1, 0, 2}, a.nSub(new Vector(0, 2, 1)).toArray());
		assertEquals(data, a.toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nSubExceptionTest() {
		new Vector(1, 2, 3).nSub(new Vector(1, 2));
	}
	
	@Test
	public void scalarMultiplyTest() {
		IVector a = new Vector(3, 1, 2);
		a.scalarMultiply(3);
		assertEquals(new double[] {9, 3, 6}, a.toArray());
	}
	
	@Test
	public void nScalarMultiplyTest() {
		double[] data = {3, 1, 2};
		IVector a = new Vector(data);
		assertEquals(new double[] {9, 3, 6}, a.nScalarMultiply(3).toArray());
		assertEquals(data, a.toArray());
	}
	
	@Test
	public void normTest() {
		assertEquals(Math.sqrt(5), new Vector(1, 0, 2).norm());
	}
	
	@Test
	public void normalizeTest() {
		IVector a = new Vector(1, 0, 2);
		a.normalize();
		assertEquals(new double[] {1 / Math.sqrt(5), 0, 2 / Math.sqrt(5)}, a.toArray());
	}
	
	@Test
	public void normalizeNullTest() {
		assertEquals(new double[] {0, 0, 0}, new Vector(0, 0, 0).normalize().toArray());
	}
	
	@Test
	public void nNormalizeTest() {
		double[] data = {1, 0, 2};
		IVector a = new Vector(data);
		assertEquals(new double[] {1 / Math.sqrt(5), 0, 2 / Math.sqrt(5)}, 
				     a.nNormalize().toArray());
		assertEquals(data, a.toArray());
	}
	
	@Test
	public void cosineTest() {
		assertEquals(0.0, new Vector(2, 2, 2).cosine(new Vector(1, 1, -2)));
		assertEquals(1.0, new Vector(2, 2, 2).cosine(new Vector(1, 1, 1)));
		assertEquals(0.0, new Vector(2, 2, 2).cosine(new Vector(0, 0, 0)));
	}
	
	@Test
	public void scalarProductTest() {
		assertEquals(6.0, new Vector(2, 2, 2).scalarProduct(new Vector(1, 1, 1)));
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void scalarProductExceptionTest() {
		new Vector(1, 2, 3).scalarProduct(new Vector(1, 2));
	}
	
	@Test
	public void nVectorProductTest() {
		assertEquals(new double[]{0, 0, 6}, 
				     new Vector(2, 0, 0).nVectorProduct(new Vector(0, 3, 0)).toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nVectorProductErrorTest() {
		new Vector(2, 1, 3, 4).nVectorProduct(new Vector(3, 3, 3, 3));
	}
	
	@Test
	public void nFromHomogeneusTest() {
		double[] data = {3, 2, 2, 2};
		IVector a = new Vector(data);
		assertEquals(new double[] {1.5, 1, 1}, a.nFromHomogeneus().toArray());
		assertEquals(data, a.toArray());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nFromHomogeneusTestException() {
		new Vector(1, 0, 0).nFromHomogeneus();
	}
	
	@Test
	public void copyPartTest() {
		assertEquals(new double[] {3, 2, 0}, new Vector(3, 2).copyPart(3).toArray());
		assertEquals(new double[] {3, 2}, new Vector(3, 2, 5).copyPart(2).toArray());
	}
	
	@Test
	public void toColumnMatrixTest() {
		double[][] data = {{3}, {1}, {2}};
		double[][] data2 = {{3}, {3}, {2}};
		IVector a = new Vector(3, 1, 2);
		IMatrix la = a.toColumnMatrix(true);
		IMatrix nla = a.toColumnMatrix(false);
		assertEquals(data, la.toArray());
		assertEquals(data, nla.toArray());
		
		a.set(1, 3);
		assertEquals(data2, la.toArray());
		assertEquals(data, nla.toArray());
	}
	
	@Test
	public void toRowMatrixTest() {
		double[][] data = {{3, 1, 2}};
		double[][] data2 = {{3, 3, 2}};
		IVector a = new Vector(3, 1, 2);
		IMatrix la = a.toRowMatrix(true);
		IMatrix nla = a.toRowMatrix(false);
		assertEquals(data, la.toArray());
		assertEquals(data, nla.toArray());
		
		a.set(1, 3);
		assertEquals(data2, la.toArray());
		assertEquals(data, nla.toArray());
	}
	
	@Test
	public void toStringTest() {
		Assert.assertEquals("3.001 2.002 1.001", new Vector(3.001, 2.002, 1.001).toString());
	}
}
