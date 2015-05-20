package hr.fer.zemris.java.tecaj.hw3;

import hr.fer.zemris.java.tecaj.hw3.CString;

import org.junit.Assert;
import org.junit.Test;

public class CStringTest {
	
	@Test
	public void testConstructors() {
		char[] data = {'a', 'b', 'c', 'd'};
		CString c = new CString(data);
		data[3] = 'e';
		Assert.assertEquals("abcd", c.toString());
		c = new CString(data, 1, 3);
		Assert.assertEquals("bce", c.toString());
		CString d = new CString(c);
		Assert.assertEquals(c.toString(), d.toString());
		c = new CString("abxz");
		Assert.assertEquals("abxz", c.toString());
		d = new CString(c.substring(1, 2));
		Assert.assertEquals("b", d.toString());
	}
	
	@Test
	public void testSubstringLengths() {
		CString s = new CString("abcdefgh");
		CString t = s.substring(2, 5);
		Assert.assertEquals(3, t.length());
		CString c = new CString(t);
		Assert.assertEquals(3, c.length());
	}
	
	@Test
	public void testSubstringElements() {
		CString s = new CString("test test");
		CString t = s.substring(3, 8);
		Assert.assertEquals('t', t.charAt(2));
		CString u = t.substring(2, 4);
		Assert.assertEquals('e', u.charAt(1));
	}
	
	@Test
	public void testSubstringVariants() {
		CString s = new CString("01234567890");
		CString t = s.right(5);
		Assert.assertEquals('8', t.charAt(2));
		CString u = t.left(3);
		Assert.assertEquals('8', u.charAt(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSubstringException() {
		new CString("1234").substring(3, 10);
	}
	
	@Test
	public void testStartsWith() {
		Assert.assertTrue(new CString("1234").startsWith(new CString("12")));
		Assert.assertFalse(new CString("1234").startsWith(new CString("23")));
		Assert.assertFalse(new CString("1234").startsWith(new CString("12345")));
	}
	
	@Test
	public void testEndsWith() {
		Assert.assertTrue(new CString("1234").endsWith(new CString("34")));
		Assert.assertFalse(new CString("1234").endsWith(new CString("35")));
		Assert.assertFalse(new CString("1234").endsWith(new CString("01234")));
	}
	
	@Test
	public void testContains() {
		Assert.assertTrue(new CString("1234").contains(new CString("23")));
		Assert.assertFalse(new CString("1234").contains(new CString("24")));
	}
	
	@Test
	public void testIndexOf() {
		CString c = new CString("12345").substring(2, 5);
		Assert.assertEquals(1, c.indexOf('4'));
		Assert.assertEquals(-1, c.indexOf('7'));
	}
	
	@Test
	public void testAdd() {
		CString s = new CString("part1part2");
		CString p1 = s.left(5);
		CString p2 = s.right(5);
		Assert.assertEquals(s.toString(), p1.add(p2).toString());
	}
	
	@Test
	public void testReplaceAllCharacter() {
		CString s = new CString("abcddcabcab").replaceAll('a', 'e');
		Assert.assertEquals("ebcddcebceb", s.toString());
	}
	
	@Test
	public void testReplaceAllString() {
		CString s = new CString("abcdefabcdef").replaceAll(new CString("cde"), new CString("cdecde"));
		Assert.assertEquals("abcdecdefabcdecdef", s.toString());
	}
	
	@Test
	public void testToCharArray() {
		CString s = new CString("abcd");
		char[] a = s.toCharArray();
		a[3] = 'e';
		char[] b = s.toCharArray();
		Assert.assertNotEquals(a[3], b[3]);
	}
}
