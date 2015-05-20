package hr.fer.zemris.java.student1191227371.hw08;

import org.junit.Assert;

import hr.fer.zemris.java.fractals.complex.*;
import hr.fer.zemris.java.fractals.complex.parser.ComplexParser;

import org.junit.Test;

public class ComplexRootedPolynomialTest {

	@Test
	public void constructorTest() {
		ComplexRootedPolynomial p = new ComplexRootedPolynomial(
				Complex.ONE, Complex.IM, Complex.ONE);
		Assert.assertEquals("[ 1.0+0.0i 0.0+1.0i 1.0+0.0i ]", p.toString());
	}
	
	@Test
	public void applyTest() {
		ComplexRootedPolynomial p = new ComplexRootedPolynomial(Complex.ONE, Complex.ZERO, 
				Complex.ZERO, Complex.ONE);
		Assert.assertEquals("0.0+0.0i", p.apply(Complex.ONE).toString());
	}
	
	@Test
	public void toComplexPolynomialTest() {
		Complex c1 = ComplexParser.parse("1 + 0i");
		Complex c2 = ComplexParser.parse("-1 + 0i");
		Complex c3 = ComplexParser.parse("0 + i");
		Complex c4 = ComplexParser.parse("0 - i");
		ComplexRootedPolynomial p = new ComplexRootedPolynomial(c1, c2, c3, c4);
		Assert.assertEquals("[ -1.0+0.0i 0.0+0.0i 0.0+0.0i 0.0+0.0i 1.0+0.0i]", 
				p.toComplexPolynom().toString());
	}
	
	@Test
	public void indexOfClosestRootTest() {
		ComplexRootedPolynomial p = new ComplexRootedPolynomial(
				Complex.ONE, Complex.ZERO);
		Assert.assertEquals(0, p.indexOfClosestRootFor(new Complex(0.95, 0), 0.1));
		Assert.assertEquals(1, p.indexOfClosestRootFor(new Complex(0.05, 0), 0.1));
	}
}
