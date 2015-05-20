package hr.fer.zemris.java.fractals.complex;

import java.util.Arrays;

/**
 * Razred reprezentira kompleksne polinome,
 * zadane koeficjentima uz potencije.
 * Konstruktor prima navedene koeficijente.
 * @author jelena Drzaic
 *
 */
public class ComplexPolynomial {

	/**
	 * Koeficjenti polinoma
	 */
	Complex[] factors;
	
	/**
	 * Stupanj polinoma
	 */
	short order;
	
	/**
	 * Konstruktor razreda 
	 * Stvara novi polinom s zeljenim koeficjentima
	 * Koeficjenti se proslijeduju redom po potencijama, uzlazno,
	 * do zadnjeg ne nul koeficjenta.
	 * iznimka je jedino 0-polinom.
	 * @param factors koeficjenti polinoma.
	 */
	public ComplexPolynomial(Complex ...factors) {
		this.factors = Arrays.copyOf(factors, factors.length);
		order = (short)(factors.length - 1);
	}
	
	/**
	 * Metoda vraca stupanj polinoma.
	 * Npr. za (7+2i)z^3+2z^2+5z+1 vraca 3.
	 * @return stupanj polinoma
	 */
	public short order() {
		return order;
	}
	
	/**
	 * Metoda vraca umnozak polinoma na kojem je pozvana i proslijedenog polinoma.
	 * @param p polinom kojim mnozimo
	 * @return umnozak polinoma
	 */
	public ComplexPolynomial multiply(ComplexPolynomial p) {
		Complex[] newFact = new Complex[p.order + order + 1];
		for(int pot = 0; pot < newFact.length; ++pot) {
			newFact[pot] = Complex.ZERO;
			for(int i = 0, granica = (pot < factors.length ? pot : order); 
				i <= granica; ++i) {
				if(pot - i < p.factors.length) {
					newFact[pot] = newFact[pot].add(factors[i].multiply(
							p.factors[pot - i]));
				}
			}
		}
		return new ComplexPolynomial(newFact);
	}

	/**
	 * Metoda vraca prvu derivaciju polinoma na kojem je pozvana.
	 * @return derivacija polinoma.
	 */
	public ComplexPolynomial derive() {
		Complex[] deriv = new Complex[order];
		deriv[0] = factors[1];
		for(int i = 1; i < deriv.length; ++i) {
			deriv[i] = factors[i + 1].multiply(new Complex(i + 1, 0));
		}
		return new ComplexPolynomial(deriv);
	}
	
	/**
	 * Metoda evaluira polinom proslijedenoj tocki.
	 * @param z tocka u kojoj racunamo vrijednost polinoma.
	 * @return vrijednost polinoma u tocki
	 */
	public Complex apply(Complex z) {
		Complex pot = Complex.ONE;
		Complex value = Complex.ZERO;
		for(int i = 0; i < factors.length; ++i) {
			value = value.add(factors[i].multiply(pot));
			pot = pot.multiply(z);
		}
		return value;
	}
	
	/**
	 * Metoda vraca string reprezentaciju polinoma u obliku
	 * [ a0 a1 ... an] gdje je p(x) = a0 + a1x + ...anx^n
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ " + factors[0].toString());
		for(int i = 1; i < factors.length; ++i) {
			sb.append(" " + factors[i]);
		}
		sb.append("]");
		return sb.toString();
	}
}