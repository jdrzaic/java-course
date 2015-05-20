package hr.fer.zemris.java.student1191227371.hw07;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Matrix;

import org.junit.Test;
import org.junit.Assert;

public class AbstractMatrixTest {
	
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
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix b = Matrix.parseSimple("6 5 4 | 1 2 3");
		IMatrix c = Matrix.parseSimple("7 7 7 | 5 7 9");
		a.add(b);
		assertEquals(c.toArray(), a.toArray());
	}
	
	@Test
	public void nAddTest() {
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix b = Matrix.parseSimple("6 5 4 | 1 2 3");
		IMatrix c = Matrix.parseSimple("7 7 7 | 5 7 9");
		IMatrix d = a.copy();
		assertEquals(c.toArray(), a.nAdd(b).toArray());
		assertEquals(d.toArray(), a.toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nAddExceptionTest() {
		Matrix.parseSimple("1 2 3 | 4 5 6").add(Matrix.parseSimple("1 2 | 3 4"));
	}
	
	@Test
	public void subTest() {
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix b = Matrix.parseSimple("6 5 4 | 1 2 3");
		IMatrix c = Matrix.parseSimple("-5 -3 -1 | 3 3 3");
		a.sub(b);
		assertEquals(c.toArray(), a.toArray());
	}
	
	@Test
	public void nSubTest() {
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix b = Matrix.parseSimple("6 5 4 | 1 2 3");
		IMatrix c = Matrix.parseSimple("-5 -3 -1 | 3 3 3");
		IMatrix d = a.copy();
		assertEquals(c.toArray(), a.nSub(b).toArray());
		assertEquals(d.toArray(), a.toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nSubExceptionTest() {
		Matrix.parseSimple("1 2 3 | 4 5 6").sub(Matrix.parseSimple("1 2 | 3 4"));
	}
	
	@Test
	public void scalarMultiplyTest() {
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix c = Matrix.parseSimple("2 4 6 | 8 10 12");
		a.scalarMultiply(2);
		assertEquals(c.toArray(), a.toArray());
	}
	
	@Test
	public void nScalarMultiplyTest() {
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix c = Matrix.parseSimple("2 4 6 | 8 10 12");
		IMatrix d = a.copy();
		assertEquals(c.toArray(), a.nScalarMultiply(2).toArray());
		assertEquals(d.toArray(), a.toArray());
	}
	
	@Test
	public void nMultiplyTest() {
		IMatrix a = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix b = Matrix.parseSimple("6 5 | 4 1 | 2 3");
		IMatrix c = Matrix.parseSimple("20 16 | 56 43");
		assertEquals(c.toArray(), a.nMultiply(b).toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nMultiplyExceptionTest() {
		Matrix.parseSimple("1 2 3 | 4 5 6").nMultiply(Matrix.parseSimple("1 2 3 | 4 5 6"));
	}
	
	@Test
	public void determinantTest() {
		assertEquals(3 * 4, Matrix.parseSimple("1 0 1 | 3 3 3 | -2 0 2").determinant());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void determinantExceptionTest() {
		Matrix.parseSimple("1 2 3 | 4 5 6").determinant();
	}
	
	@Test
	public void makeIdentityTest() {
		assertEquals(Matrix.parseSimple("1 0 | 0 1 | 0 0").toArray(),
				  	 Matrix.parseSimple("1 2 | 3 4 | 5 6").makeIdentity().toArray());
	}
	
	@Test
	public void nTransposeTest() {
		IMatrix m = Matrix.parseSimple("1 2 3 | 4 5 6");
		IMatrix t1 = Matrix.parseSimple("1 4 | 2 5 | 3 6");
		IMatrix t2 = Matrix.parseSimple("1 4 | 2 10 | 3 6");
		IMatrix lt = m.nTranspose(true);
		IMatrix nlt = m.nTranspose(false);
		assertEquals(t1.toArray(), lt.toArray());
		assertEquals(t1.toArray(), nlt.toArray());
		m.set(1, 1, 10);
		assertEquals(t2.toArray(), lt.toArray());
		assertEquals(t1.toArray(), nlt.toArray());
	}
	
	@Test
	public void subMatrixTest() {
		IMatrix m = Matrix.parseSimple("1 2 3 | 4 5 6 | 7 8 9");
		IMatrix t1 = Matrix.parseSimple("1 3 | 7 9");
		IMatrix t2 = Matrix.parseSimple("1 3 | 10 9");
		IMatrix lt = m.subMatrix(1, 1, true);
		IMatrix nlt = m.subMatrix(1, 1, false);
		assertEquals(t1.toArray(), lt.toArray());
		assertEquals(t1.toArray(), nlt.toArray());
		m.set(2, 0, 10);
		assertEquals(t2.toArray(), lt.toArray());
		assertEquals(t1.toArray(), nlt.toArray());
	}
	
	@Test
	public void toVectorTest() {
		double[] t1 = {1, 2, 3};
		double[] t2 = {1, 5, 3};
		IMatrix m = Matrix.parseSimple("1 2 3");
		IVector lv = m.toVector(true);
		IVector nlv = m.toVector(false);
		assertEquals(t1, lv.toArray());
		assertEquals(t1, nlv.toArray());
		m.set(0, 1, 5);
		assertEquals(t2, lv.toArray());
		assertEquals(t1, nlv.toArray());
		
		m = Matrix.parseSimple("1 | 2 | 3");
		lv = m.toVector(true);
		nlv = m.toVector(false);
		assertEquals(t1, lv.toArray());
		assertEquals(t1, nlv.toArray());
		m.set(1, 0, 5);
		assertEquals(t2, lv.toArray());
		assertEquals(t1, nlv.toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void toVectorErrorTest() {
		Matrix.parseSimple("1 2 | 3 4").toVector(true);
	}
	
	@Test
	public void toStringTest() {
		Assert.assertEquals("1.111 2.222\n3.333 4.444",
							Matrix.parseSimple("1.1111 2.2222 | 3.3333 4.4444").toString());
	}
	
	@Test
	public void nInvertTest() {
		IMatrix m = Matrix.parseSimple("1 2 3 | 3 2 0 | 0 0 3");
		assertEquals(Matrix.parseSimple("1 0 0 | 0 1 0 | 0 0 1").toArray(),
					 m.nInvert().nMultiply(m).toArray());
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void nInversErrorTest() {
		Matrix.parseSimple("1 2 3 | 4 5 6 | 7 8 9").nInvert();
	}
}
