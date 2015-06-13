package hr.fer.zemris.java.hw13.test;
import hr.fer.zemris.java.hw13.aplikacija2.trigonometry.TrigonometryUtility;

import org.junit.Test;
import org.junit.Assert;

/**
 * Razred testira metodu razreda {@link TrigonometryUtility}.
 * @author jelena
 *
 */
public class TrigonometricTest {

	/**
	 * Odstupanje koje je prihvatljivo kod racunanja
	 */
	private static final double DELTA = 10E-3;
	
	/**
	 * Metoda testira ispis vrijednosti za 0 do 45 stupnjeva
	 */
	@Test
	public void piOverFourNormalTest() {
		Assert.assertEquals("[(0.0,1.0), (0.0175,0.9998), (0.0349,0.9994), "
				+ "(0.0523,0.9986), (0.0698,0.9976), (0.0872,0.9962), "
				+ "(0.1045,0.9945), (0.1219,0.9925), (0.1392,0.9903), "
				+ "(0.1564,0.9877), (0.1736,0.9848), (0.1908,0.9816), "
				+ "(0.2079,0.9781), (0.225,0.9744), (0.2419,0.9703), "
				+ "(0.2588,0.9659), (0.2756,0.9613), (0.2924,0.9563), "
				+ "(0.309,0.9511), (0.3256,0.9455), (0.342,0.9397), "
				+ "(0.3584,0.9336), (0.3746,0.9272), (0.3907,0.9205), "
				+ "(0.4067,0.9135), (0.4226,0.9063), (0.4384,0.8988), "
				+ "(0.454,0.891), (0.4695,0.8829), (0.4848,0.8746), "
				+ "(0.5,0.866), (0.515,0.8572), (0.5299,0.848), "
				+ "(0.5446,0.8387), (0.5592,0.829), (0.5736,0.8192), "
				+ "(0.5878,0.809), (0.6018,0.7986), (0.6157,0.788), "
				+ "(0.6293,0.7771), (0.6428,0.766), (0.6561,0.7547), "
				+ "(0.6691,0.7431), (0.682,0.7314), (0.6947,0.7193), "
				+ "(0.7071,0.7071)]", 
				TrigonometryUtility.calculate(0, 45).toString());
	}
	
	/**
	 * Metoda testira obrunti redoslijed parametara
	 */
	@Test
	public void piOverTwoSwapTest() {
		Assert.assertEquals("[(0.0,1.0), (0.0175,0.9998), (0.0349,0.9994), "
				+ "(0.0523,0.9986), (0.0698,0.9976), (0.0872,0.9962), "
				+ "(0.1045,0.9945), (0.1219,0.9925), (0.1392,0.9903), "
				+ "(0.1564,0.9877), (0.1736,0.9848), (0.1908,0.9816), "
				+ "(0.2079,0.9781), (0.225,0.9744), (0.2419,0.9703), "
				+ "(0.2588,0.9659), (0.2756,0.9613), (0.2924,0.9563), "
				+ "(0.309,0.9511), (0.3256,0.9455), (0.342,0.9397), "
				+ "(0.3584,0.9336), (0.3746,0.9272), (0.3907,0.9205), "
				+ "(0.4067,0.9135), (0.4226,0.9063), (0.4384,0.8988), "
				+ "(0.454,0.891), (0.4695,0.8829), (0.4848,0.8746), "
				+ "(0.5,0.866), (0.515,0.8572), (0.5299,0.848), "
				+ "(0.5446,0.8387), (0.5592,0.829), (0.5736,0.8192), "
				+ "(0.5878,0.809), (0.6018,0.7986), (0.6157,0.788), "
				+ "(0.6293,0.7771), (0.6428,0.766), (0.6561,0.7547), "
				+ "(0.6691,0.7431), (0.682,0.7314), (0.6947,0.7193), "
				+ "(0.7071,0.7071)]", 
				TrigonometryUtility.calculate(45, 0).toString());
	}
	
	/**
	 * Metoda testira podesavanje parametra ako je prevelik
	 */
	@Test
	public void MoreThanSevenTwentyTest() {
		Assert.assertEquals(721, 
				TrigonometryUtility.calculate(0, 1000).size());
	}
	
	/**
	 * Metoda testira vrijednosti sinusa i kosinusa broja
	 */
	@Test
	public void MoreThanSevenTwentySwapTest() {
		Assert.assertEquals(721, 
				TrigonometryUtility.calculate(1000, 0).size());
		Assert.assertEquals(1.0, TrigonometryUtility.calculate(1000, 0).get(0).getCos(), DELTA);
		Assert.assertEquals(0, TrigonometryUtility.calculate(1000, 0).get(90).getCos(), DELTA);
	}
	
	
}
