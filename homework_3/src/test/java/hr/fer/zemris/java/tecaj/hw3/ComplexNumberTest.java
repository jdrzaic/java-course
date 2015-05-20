package hr.fer.zemris.java.tecaj.hw3;

import hr.fer.zemris.java.tecaj.hw3.ComplexNumber;

import org.junit.Assert;
import org.junit.Test;

public class ComplexNumberTest {

	private final double DELTA = 1e-10;

	@Test 
	public void testToString() {
		ComplexNumber c = new ComplexNumber(1,1);
		Assert.assertEquals("1.0+1.0i", c.toString());
		Assert.assertEquals("1.0", new ComplexNumber(1,0).toString());
		Assert.assertEquals("1.0i", new ComplexNumber(0,1).toString());
		Assert.assertEquals("1.0-1.0i", new ComplexNumber(1,-1).toString());
	}
	@Test
	public void testConstructor() {
		ComplexNumber c = new ComplexNumber(3, 2);
		Assert.assertEquals(3.0, c.getReal(), DELTA);
		Assert.assertEquals(2.0, c.getImaginary(), DELTA);

		c = new ComplexNumber();
		Assert.assertEquals(0.0, c.getReal(), DELTA);
		Assert.assertEquals(0.0, c.getImaginary(), DELTA);
	}

	@Test
	public void testFromReal() {
		ComplexNumber c = ComplexNumber.fromReal(34.145);
		Assert.assertEquals(34.145, c.getReal(), DELTA);
		Assert.assertEquals(0.0, c.getImaginary(), DELTA);
	}

	@Test
	public void testFromImag() {
		ComplexNumber c = ComplexNumber.fromImaginary(13.374);
		Assert.assertEquals(0.0, c.getReal(), DELTA);
		Assert.assertEquals(13.374, c.getImaginary(), DELTA);
	}

	@Test
	public void testFromMagnutudeAndAngle() {
		ComplexNumber c = ComplexNumber.fromMagnitudeAndAngle(3.0, Math.PI / 4);
		Assert.assertEquals(9.0, 2 * c.getReal() * c.getReal(), DELTA);
		Assert.assertEquals(9.0, 2 * c.getImaginary() * c.getImaginary(), DELTA);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFromMagnitudeAndAngleException() {
		ComplexNumber.fromMagnitudeAndAngle(-3.4, Math.PI);
	}

	@Test
	public void testParse() {
		ComplexNumber c;
		c = ComplexNumber.parse("3.14");
		Assert.assertEquals(3.14, c.getReal(), DELTA);
		Assert.assertEquals(0.0, c.getImaginary(), DELTA);

		c = ComplexNumber.parse("i");
		Assert.assertEquals(0.0, c.getReal(), DELTA);
		Assert.assertEquals(1.0, c.getImaginary(), DELTA);
		
		c = ComplexNumber.parse("-3+4i");
		Assert.assertEquals(-3.0, c.getReal(), DELTA);
		Assert.assertEquals(4.0, c.getImaginary(), DELTA);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseException() {
		ComplexNumber.parse("-3+9");
	}
	
	@Test
	public void testGetMagnitude() {
		ComplexNumber c = new ComplexNumber(1.0, 2.0);
		Assert.assertEquals(5.0, c.getMagnitude() * c.getMagnitude(), DELTA);
	}

	@Test
	public void testGetAngle() {
		ComplexNumber c = new ComplexNumber(3.0, -3.0);
		Assert.assertEquals(7 * Math.PI / 4, c.getAngle(), DELTA);

		c = new ComplexNumber(0.0, 5.0);
		Assert.assertEquals(Math.PI / 2, c.getAngle(), DELTA);
	}
	
	@Test
	public void testAdd() {
		ComplexNumber a = new ComplexNumber(1.0, 3.0);
		ComplexNumber b = new ComplexNumber(2.3, 5.4);
		ComplexNumber c = a.add(b);
		Assert.assertEquals(1.0, a.getReal(), DELTA);
		Assert.assertEquals(3.0, a.getImaginary(), DELTA);
		Assert.assertEquals(3.3, c.getReal(), DELTA);
		Assert.assertEquals(8.4, c.getImaginary(), DELTA);
	}
	
	@Test
	public void testSub() {
		ComplexNumber a = new ComplexNumber(1.0, 3.0);
		ComplexNumber b = new ComplexNumber(2.3, 5.4);
		ComplexNumber c = b.sub(a);
		Assert.assertEquals(2.3, b.getReal(), DELTA);
		Assert.assertEquals(5.4, b.getImaginary(), DELTA);
		Assert.assertEquals(1.3, c.getReal(), DELTA);
		Assert.assertEquals(2.4, c.getImaginary(), DELTA);
	}
	
	@Test
	public void testMul() {
		ComplexNumber a = new ComplexNumber(1.0, 3.0);
		ComplexNumber b = new ComplexNumber(2.3, 5.4);
		ComplexNumber c = a.mul(b);
		Assert.assertEquals(1.0, a.getReal(), DELTA);
		Assert.assertEquals(3.0, a.getImaginary(), DELTA);
		Assert.assertEquals(-13.9, c.getReal(), DELTA);
		Assert.assertEquals(12.3, c.getImaginary(), DELTA);
	}
	
	@Test
	public void testDiv() {
		ComplexNumber a = new ComplexNumber(-13.9, 12.3);
		ComplexNumber b = new ComplexNumber(2.3, 5.4);
		ComplexNumber c = a.div(b);
		Assert.assertEquals(-13.9, a.getReal(), DELTA);
		Assert.assertEquals(12.3, a.getImaginary(), DELTA);
		Assert.assertEquals(1.0, c.getReal(), DELTA);
		Assert.assertEquals(3.0, c.getImaginary(), DELTA);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDivException() {
		ComplexNumber.parse("3").div(ComplexNumber.parse("0"));
	}
	
	@Test
	public void testPower() {
		ComplexNumber a = new ComplexNumber(1.0, 2.0);
		ComplexNumber b = a.power(3);
		Assert.assertEquals(1.0, a.getReal(), DELTA);
		Assert.assertEquals(2.0, a.getImaginary(), DELTA);
		Assert.assertEquals(-11.0, b.getReal(), DELTA);
		Assert.assertEquals(-2.0, b.getImaginary(), DELTA);
		
		b = a.power(0);
		Assert.assertEquals(1.0, b.getReal(), DELTA);
		Assert.assertEquals(0.0, b.getImaginary(), DELTA);
	}
	
	@Test
	public void testRoot() {
		ComplexNumber a = new ComplexNumber(1.0, 0.0);
		ComplexNumber roots[] = a.root(4);
		ComplexNumber expected[] = {ComplexNumber.parse("1"), 
									ComplexNumber.parse("i"), 
									ComplexNumber.parse("-1"),
									ComplexNumber.parse("-i")};
		Assert.assertEquals(1.0, a.getReal(), DELTA);
		Assert.assertEquals(0.0, a.getImaginary(), DELTA);
		for (int i = 0; i < 4; ++i) {
			Assert.assertEquals(expected[i].getReal(), roots[i].getReal(), DELTA);
			Assert.assertEquals(expected[i].getImaginary(), roots[i].getImaginary(), DELTA);
		}	
	}
	
}
