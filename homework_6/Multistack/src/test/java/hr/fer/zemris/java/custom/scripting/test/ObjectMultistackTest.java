package hr.fer.zemris.java.custom.scripting.test;

import org.junit.*;

import hr.fer.zemris.java.custom.scripting.exec.ObjectMultistack;
import hr.fer.zemris.java.custom.scripting.exec.ValueWrapper;

public class ObjectMultistackTest {

	static ObjectMultistack multistack = new ObjectMultistack();
	@Test
	public void pushTestInteger() {
		
		ValueWrapper year = new ValueWrapper(Integer.valueOf(2000));
		multistack.push("year", year);
		Assert.assertEquals(2000, multistack.peek("year").getValue());
	}
	
	@Test
	public void pushTestDouble() {
		
		ValueWrapper year = new ValueWrapper(Double.valueOf(2000.2));
		multistack.push("year", year);
		Assert.assertEquals(2000.2, multistack.peek("year").getValue());
	}
	
	@Test
	public void pushTestString() {
		
		ValueWrapper year = new ValueWrapper(Double.valueOf("2000.2"));
		multistack.push("year", year);
		Assert.assertEquals(2000.2, multistack.peek("year").getValue());
	}
	
	@Test
	public void peekTest() {
		
		Assert.assertEquals(null, multistack.peek("price"));
	}
	
	@Test
	public void popTest() {
		
		Assert.assertEquals(2000.2, multistack.pop("year").getValue());
	}
	
	@Test
	public void ConstructorNullTest() {
		multistack.push("january", new ValueWrapper(null));
		Assert.assertEquals(1, multistack.peek("january").getValue());
	}
	
	@Test(expected=RuntimeException.class)
	public void ConstructorIllegalTest() {
		multistack.push("january", new ValueWrapper(new ObjectMultistack()));
		Assert.assertEquals(1, multistack.peek("january"));
	}
	
	@Test
	public void IncrementTest() {
		
		multistack.peek("year").increment("5");
		Assert.assertEquals(2005, multistack.peek("year").getValue());
	}
	
	@Test
	public void IncrementTestDouble() {
		multistack.push("may", new ValueWrapper(new Double(5.2)));
		multistack.peek("may").increment("5.2");
		Assert.assertEquals(10.4, multistack.peek("may").getValue());
	}
	
	@Test
	public void IncrementTestDouble2() {
		multistack.push("march", new ValueWrapper(new Double(5.2)));
		multistack.peek("march").increment("5");
		Assert.assertEquals(10.2, multistack.peek("march").getValue());
	}
	
	@Test
	public void IncrementTestDouble3() {
		multistack.push("march", new ValueWrapper(new Integer(5)));
		multistack.peek("march").increment("5.2");
		Assert.assertEquals(10.2, multistack.peek("march").getValue());
	}
	
	@Test
	public void DecrementTest() {
		
		multistack.peek("year").decrement("5");
		Assert.assertEquals(2000, multistack.peek("year").getValue());
	}
	
	@Test
	public void DecrementTestDouble() {
		multistack.push("september", new ValueWrapper(new Double(5.2)));
		multistack.peek("september").decrement("5.2");
		Assert.assertEquals(0.0, multistack.peek("september").getValue());
	}
	
	@Test
	public void DecrementTestDouble2() {
		multistack.push("march", new ValueWrapper(new Double(5.2)));
		multistack.peek("march").increment("5");
		Assert.assertEquals(10.2, multistack.peek("march").getValue());
	}
	
	@Test
	public void DecrementTestDouble3() {
		multistack.push("monday", new ValueWrapper(new Integer(5)));
		multistack.peek("monday").decrement("3.5");
		Assert.assertEquals(1.5, multistack.peek("monday").getValue());
	}
	
	@Test(expected=RuntimeException.class)
	public void DecrementTestDoubleNotType() {
		multistack.push("monday", new ValueWrapper(new Integer(5)));
		multistack.peek("monday").decrement("3.5u88t");
		Assert.assertEquals(1.5, multistack.peek("monday").getValue());
	}
	
	@Test
	public void multiplyTest() {
		
		multistack.push("haha", new ValueWrapper(new Double(5.3)));
		multistack.peek("haha").multiply("5");
		Assert.assertEquals(26.5, multistack.peek("haha").getValue());
	}
	
	@Test
	public void multiplyTestDouble2() {
		multistack.push("hour", new ValueWrapper(new Double(5)));
		multistack.peek("hour").multiply("5");
		Assert.assertEquals(25.0, multistack.peek("hour").getValue());
	}
	
	@Test
	public void multiplyTestDouble3() {
		multistack.push("day", new ValueWrapper(new Integer(5)));
		multistack.peek("day").multiply("3.5");
		Assert.assertEquals(17.5, multistack.peek("day").getValue());
	}
	
	@Test
	public void divideTest() {
		
		multistack.push("haha", new ValueWrapper(new Double(15)));
		multistack.peek("haha").divide("2.5");
		Assert.assertEquals(new Double(6), multistack.peek("haha").getValue());
	}
	

	@Test
	public void divideTestDouble2() {
		multistack.push("second", new ValueWrapper(new Double(5)));
		multistack.peek("second").divide("5");
		Assert.assertEquals(1.0, multistack.peek("second").getValue());
	}
	
	@Test
	public void divideTestDouble3() {
		multistack.push("minute", new ValueWrapper(new Integer(5)));
		multistack.peek("minute").divide("5");
		Assert.assertEquals(1, multistack.peek("minute").getValue());
	}
	
	@Test
	public void divideTestDouble4() {
		multistack.push("apple", new ValueWrapper(new Double(0.5)));
		multistack.peek("apple").divide("0.5");
		Assert.assertEquals(1.0, multistack.peek("apple").getValue());
	}
	@Test
	public void divideTestInteger() {
		multistack.push("minute", new ValueWrapper(new Integer(5)));
		multistack.peek("minute").divide("5");
		Assert.assertEquals(1, multistack.peek("minute").getValue());
	}
	
	@Test
	public void isEmptyTestTrue() {
		
		Assert.assertEquals(true, multistack.isEmpty("nothing"));
	}
	
	@Test
	public void isEmptyTestFalse() {
		
		Assert.assertEquals(false, multistack.isEmpty("year"));
	}
	
	@Test
	public void isEmptyTestTrue2() {
		
		multistack.push("february", new ValueWrapper(2));
		multistack.pop("february");
		Assert.assertEquals(true, multistack.isEmpty("february"));
	}
	
	@Test
	public void setValueDouble() {
		
		multistack.push("wednesday", new ValueWrapper("2.2"));
		multistack.peek("wednesday").setValue(5.2);
		Assert.assertEquals(5.2, multistack.peek("wednesday").getValue());
	}
	
	@Test
	public void setValueInteger() {
		
		multistack.push("friday", new ValueWrapper("2.2"));
		multistack.peek("friday").setValue(5);
		Assert.assertEquals(5, multistack.peek("friday").getValue());
	}
	
	@Test
	public void setValueString() {
		
		multistack.push("sunday", new ValueWrapper("2.2"));
		multistack.peek("sunday").setValue("5");
		Assert.assertEquals("5", multistack.peek("sunday").getValue());
	}

	@Test(expected=RuntimeException.class)
	public void setValueUnknown() {
		
		multistack.push("sunday", new ValueWrapper("2.2"));
		multistack.peek("sunday").setValue(new ObjectMultistack());
	}

	@Test
	public void numCompareTestBigger() {
		multistack.push("Mana", new ValueWrapper("5.2"));
		Assert.assertTrue(multistack.peek("Mana").numCompare("3.3") > 0);
	}
	
	@Test
	public void numCompareTestLess() {
		multistack.push("Mana", new ValueWrapper("2.2"));
		Assert.assertTrue(multistack.peek("Mana").numCompare("3.3") < 0);
	}
	
}
