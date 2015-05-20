package hr.fer.zemris.java.student1191227371.hw08;

import hr.fer.zemris.java.fractals.complex.Complex;

import org.junit.Assert;
import org.junit.Test;

public class ComplexTest {
	
	private static final double DELTA = 0.0001;
	@Test
	public void constructorDefaultTest() {
		Complex c1 = new Complex();
		Assert.assertEquals("0.0+0.0i", c1.toString());
	}
	
	@Test
	public void constructorTest() {
		Complex c = new Complex(1, 1);
		Assert.assertEquals("1.0+1.0i", c.toString());
	}
	
	@Test
	public void complexModuleTest() {
		Complex c = new Complex(3, 4);
		Assert.assertEquals(DELTA, 5.0, c.module());
	}
	
	@Test
	public void multiplyTest() {
		Complex c = new Complex(1, 1);
		Assert.assertEquals("-1.0+1.0i", c.multiply(Complex.IM).toString());
	}
	
	@Test
	public void divideTest() {
		Complex c = new Complex(2,2);
		Assert.assertEquals("1.0+1.0i", c.divide(new Complex(2, 0)).toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void divideTestException() {
		Complex.ONE.divide(Complex.ZERO);
	}
	
	@Test
	public void addTest() {
		Complex c = new Complex(1, 2);
		Assert.assertEquals("2.0+2.0i", c.add(Complex.ONE).toString());
	}
	
	@Test
	public void subTest() {
		Complex c = new Complex(1, 2);
		Assert.assertEquals("0.0+2.0i", c.sub(Complex.ONE).toString());
	}
	
	@Test
	public void negateTest() {
		Assert.assertEquals("-1.0-1.0i", new Complex(1, 1).negate().toString());
	}
}
