package hr.fer.zemris.java.student1191227371.hw06;

import org.junit.Assert;
import org.junit.Test;

public class CijeliBrojeviTest {
	CijeliBrojevi a = new CijeliBrojevi();
	@Test
	public void brojJeParan() {
		Assert.assertTrue(a.jeParan(4));
	}
	
	@Test
	public void brojNijeParan() {
		Assert.assertFalse(a.jeParan(9));
	}
	
	@Test
	public void brojJeNeparan() {
		Assert.assertTrue(a.jeNeparan(9));
	}
	
	@Test
	public void brojNijeNeparan() {
		Assert.assertFalse(a.jeNeparan(8));
	}
	
	@Test
	public void brojJeProst() {
		Assert.assertTrue(a.jeProst(7));
	}
	
	@Test
	public void brojNijeProst() {
		Assert.assertFalse(a.jeProst(10));
	}
}
