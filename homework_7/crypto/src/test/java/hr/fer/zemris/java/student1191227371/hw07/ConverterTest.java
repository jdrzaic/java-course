package hr.fer.zemris.java.student1191227371.hw07;

import hr.fer.zemris.java.student1191227371.hw07.crypto.Converter;

import org.junit.Test;
import org.junit.Assert;

public class ConverterTest {

	@Test
	public void testConverting() {
		Assert.assertEquals("[B@277050dc", Converter.hextobyte("0000").toString());
	}
}
