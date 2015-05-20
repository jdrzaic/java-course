package hr.fer.zemris.java.fractals.complex;

/**
 * Razred reprezentira nepromijenjive kompleksne brojeve.
 * Brojevi se pohranjuju kao par (realni dio, imaginarni dio),
 * te konstruktor prima realni i imaginarni dio.
 * @author Jelena Drzaic
 *
 */
public class Complex {
	
	public static final Complex ZERO = new Complex(0,0);
	public static final Complex ONE = new Complex(1,0);
	public static final Complex ONE_NEG = new Complex(-1,0);
	public static final Complex IM = new Complex(0,1);
	public static final Complex IM_NEG = new Complex(0,-1);
	
	/**
	 * Realni dio broja
	 */
	private double re;
	
	/**
	 * Imaginarni dio broja
	 */
	private double im;
	
	/**
	 * Defaultni konstruktor razreda.
	 */
	public Complex() {
		this(0,0);
	}
	
	/**
	 * KOnstruktor razreda.
	 * Stvara kompleksni broj s zadanim vrijednostima.
	 * @param re realni dio broja
	 * @param im imaginarni dio broja
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * Metoda vraca normu kompleksnog broja.
	 * @return norma broja.
	 */
	public double module() {
		return Math.sqrt(re * re + im * im);
	}
	
	/**
	 * Metoda vraca umnozak kompleksnog broja na kojem je pozvana i
	 * proslijedenog broja.
	 * @param c broj kojim mnozimo
	 * @return umnozak brojeva
	 */
	public Complex multiply(Complex c) {
		return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
	}
	
	/**
	 * Metoda vraca kvocijent kompleksnih brojeva.
	 * @param c kompleksni broj kojim dijelimo
	 * @return kvocijent brojeva
	 */
	public Complex divide(Complex c) {
		if(c.module() == 0) {
			throw new IllegalArgumentException("Cannot divide by zero.");
		}else {
			return new Complex((re * c.re + im * c.im) / (c.module() * c.module()), 
					(im * c.re - re * c.im) / (c.module() * c.module()));
		}
	}

	/**
	 * Metoda vraca zbroj kompleksnog broja na kojem je pozvana i proslijedenog broja.
	 * @param c broj kojeg zbrajamo 
	 * @return zbroj brojeva
	 */
	public Complex add(Complex c) {
		return new Complex(re + c.re, im + c.im);
	}
	
	/**
	 * Metoda vraca razliku broja na kojem je pozvana i proslijedenog broja
	 * @param c broj koji oduzimamo
	 * @return razliku brojeva
	 */
	public Complex sub(Complex c) {
		return this.add(c.negate());
	}
	
	/**
	 * Metoda vraca broj suprotan broju na kojem je pozvana, 
	 * tj broj sa suprotnim realnim i imaginarnim dijelom.
	 * @return suprotni broj.
	 */
	public Complex negate() {
		return new Complex(-re, -im);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("" + re);
		sb.append(im <  0 ? im + "i" : "+" + im + "i");
		return sb.toString();
	}
}
