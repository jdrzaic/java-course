package hr.fer.zemris.java.fractals.complex;

import java.util.Arrays;

/**
 * Razred implementira polinome s kompleksnim nultockama.
 * Polinomi su reprezentirani pomocu svojih nultocaka,
 * koje konstruktor i prima kao argumente
 * @author jelena Drzaic
 *
 */
public class ComplexRootedPolynomial {
	
	/**
	 * Korijeni polinoma
	 */
	Complex[] roots;
	
	/**
	 * Konstruktor razreda.
	 * Prima niz kompleksnih brojeva te stvara polinom s tim nultockama.
	 * @param roots korijeni polinoma
	 */
	public ComplexRootedPolynomial(Complex ...roots) {
		this.roots = Arrays.copyOf(roots, roots.length);
	}
	
	/**
	 * Metoda racuna vrijednost polinoma u zadanoj tocki.
	 * @param z tocka u kojoj racunamo vrijednost polinoma.
	 * @return vrijednost polinoma u tocki
	 */
	public Complex apply(Complex z) {
		Complex value = z.sub(roots[0]);
		for(int i = 1; i < roots.length; ++i) {
			value = value.multiply(z.sub(roots[i]));
		}
		return value;
	}
	
	/**
	 * Metoda pretvara instancu ove klase u instancu klase ComplexPolynomial, 
	 * tj u polinom zadan koeficjentima uz potencije.
	 * @return instanca klase ComplexPolynomial
	 */
	public ComplexPolynomial toComplexPolynom() {
		ComplexPolynomial polynom = new ComplexPolynomial(Complex.ONE);
		for(int i = 0; i < roots.length; ++i) {
			polynom = polynom.multiply(new ComplexPolynomial(roots[i].negate(), Complex.ONE));
		}
		return polynom;
	}
	
	/**
	 * MEtoda vraca string reprezentaciju polinoma,
	 * u obliku [ z0 z1 ... zn ]  gdje su zi korijeni polinoma,
	 * i = 1,...,n
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ " + roots[0]);
		for(int i = 1; i < roots.length; ++i) {
			sb.append(" " + roots[i]);
		}
		sb.append(" ]");
		return sb.toString();
	}
	
	/**
	 * Metoda vraca najblizi korijen polinoma proslijedenoj tocki, 
	 * ako postoji takav korijen da je manji od zadanog treshold-a
	 * @param z tocka u cijoj okolini promatramo korijene polinoma
	 * @param treshold najveca dopustena udaljenost korijena polinoma od
	 * proslijedene tocke
	 * @return indeks najblizeg korijena, ako postoji, -1 inace.
	 */
	public int indexOfClosestRootFor(Complex z, double treshold) {
		int index = -1;
		int min = -1;
		for(int i = 0; i < roots.length; ++i) {
			if(z.sub(roots[i]).module() < treshold && (min == -1 || 
					z.sub(roots[i]).module() < min)) {
				index = i;
			}
		}
		return index;
	}
}
