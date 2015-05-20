package hr.fer.zemris.java.student1191227371.hw08;

import hr.fer.zemris.java.fractals.complex.Complex;
import hr.fer.zemris.java.fractals.complex.ComplexPolynomial;

import org.junit.Assert;
import org.junit.Test;

public class ComplexPolynomialTest {
	
	@Test
	public void constructorTest() {
		Assert.assertEquals("[ 1.0+0.0i 1.0+0.0i -1.0+0.0i]", new ComplexPolynomial(Complex.ONE, 
				Complex.ONE, Complex.ONE_NEG).toString());
	}
	
	@Test
	public void deriveTest() {
		ComplexPolynomial p = new ComplexPolynomial(Complex.ONE, Complex.ZERO, 
				Complex.ZERO, Complex.ONE);
		Assert.assertEquals("[ 0.0+0.0i 0.0+0.0i 3.0+0.0i]", p.derive().toString());
	}
	
	@Test
	public void orderTest() {
		ComplexPolynomial p = new ComplexPolynomial(Complex.ONE, Complex.ZERO, 
				Complex.ZERO, Complex.ONE);
		ComplexPolynomial q = new ComplexPolynomial(Complex.ONE);
		Assert.assertEquals(3, p.order());
		Assert.assertEquals(0, q.order());
	}
	
	@Test
	public void multiplyTest() {
		ComplexPolynomial p = new ComplexPolynomial(Complex.ONE, Complex.ZERO, 
				Complex.ZERO, Complex.ONE);
		ComplexPolynomial q = new ComplexPolynomial(new Complex(0, 2));
		Assert.assertEquals("[ 0.0+2.0i 0.0+0.0i 0.0+0.0i 0.0+2.0i]", 
				p.multiply(q).toString());
	}
	
	@Test
	public void applyTest() {
		ComplexPolynomial p = new ComplexPolynomial(Complex.ONE, Complex.ZERO, 
				Complex.ZERO, Complex.ONE);
		Assert.assertEquals("2.0+0.0i", p.apply(Complex.ONE).toString());
	}
}
