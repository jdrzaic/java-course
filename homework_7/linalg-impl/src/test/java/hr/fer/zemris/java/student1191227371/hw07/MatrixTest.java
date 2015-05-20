package hr.fer.zemris.java.student1191227371.hw07;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.Matrix;

import org.junit.Test;
import org.junit.Assert;

public class MatrixTest {
	
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
	public void constructorTest() {
		double[][] data1 = {{0, 0}, {0, 0}, {0, 0}};
		IMatrix m = new Matrix(3, 2);
		Assert.assertEquals(3, m.getRowsCount());
		Assert.assertEquals(2, m.getColsCount());
		assertEquals(data1, m.toArray());
		
		double[][] data2 = {{3, 2, 1}, {5, 3, 2}};
		m = new Matrix(2, 3, data2, false);
		assertEquals(data2, m.toArray());
	}
	
	@Test
	public void parseTest() {
		assertEquals(new double[][] {{1, 2, 3}, {4, 5, 6}},
				     Matrix.parseSimple("1 2 3 | 4 5 6").toArray());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void parseErrorTest() {
		Matrix.parseSimple("1 2 abc | defg|");
	}
	
	@Test
	public void copyTest() {
		double[][] data = {{1, 2, 3}, {4, 5, 6}};
		IMatrix m = new Matrix(2, 3, data, false);
		IMatrix c = m.copy();
		assertEquals(data, c.toArray());
		m.set(0, 0, 15);
		assertEquals(data, c.toArray());
	}
	
	@Test
	public void newInstanceTest() {
		IMatrix m = new Matrix(2, 2);
		assertEquals(new double[][] {{0, 0, 0}, {0, 0, 0}}, m.newInstance(2, 3).toArray());
	}
	
	@Test
	public void operationTest() {
		double data[][] = {
				{1, 2, 3},
				{4, 5, 15},
				{7, 8, 9}
		};
		IMatrix m = Matrix.parseSimple("1 2 3 | 4 5 6 | 7 8 9");
		Assert.assertEquals(3, m.getRowsCount());
		Assert.assertEquals(3, m.getColsCount());
		assertEquals(3, m.get(0, 2));
		m.set(1, 2, 15);
		assertEquals(data, m.toArray());
	}
}
