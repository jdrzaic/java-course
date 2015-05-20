package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.complex.*;
import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;

/**
 * Osnovna potpora za prikaz fraktala dobivenih Newton-Raphsonovim iteracijama.
 * 
 * @author jdrzaic
 *
 */
public class NewtonImpl {

	/**
	 * Metoda otvara graficko sucelje i prikazuje fraktal koji 
	 * se računa paralelno.
	 */
	public static void showParallel(ComplexRootedPolynomial p) {
		FractalViewer.show(getParallelFractalproducer(p));
	}

	/**
	 * Vraća objekt koji fraktal generira .
	 * 
	 * @return visedretveni generator fraktala nastalog Newton-Raphsonovim iteracijama
	 */
	private static IFractalProducer getParallelFractalproducer(
			ComplexRootedPolynomial p) {
		return new NewtonFractalProducer(p);
	}
	
}
