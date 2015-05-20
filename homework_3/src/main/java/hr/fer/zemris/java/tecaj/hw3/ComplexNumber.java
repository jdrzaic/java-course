package hr.fer.zemris.java.tecaj.hw3;

import java.util.regex.*;

/**
 * Razred implementira podrsku za rad s kompleksnim brojevima.
 * Implementirane su standardne operacije na kompleksnim brojevima.
 * 
 * @author Jelena Drzaic
 *
 */
public class ComplexNumber {
	
	/**
	 * Realni dio kompleksnog broja.
	 */
	private double realniDio;
	
	/**
	 * Imaginarni dio kompleksnog broja.
	 */
	private double imaginarniDio;
	
	/**
	 * Defaultni konstruktor razreda ComplexNumber.
	 * Konstruira kompleksni broj '0 + 0i'.
	 */
	public ComplexNumber() {
		realniDio = 0.0;
		imaginarniDio = 0.0;
	}
	
	/**
	 * Konstruktor razreda ComplexNumber.
	 * Parametri su dani u nastavku;
	 * @param realniDio realni dio konstruiranog kompleksnog broja.
	 * @param imaginarniDio imaginarni dio konstruiranog kompleksnog
	 * broja.
	 */
	public ComplexNumber(final double realniDio, final double imaginarniDio) {
		this.realniDio = realniDio;
		this.imaginarniDio = imaginarniDio;
	}
	
	/**
	 * Metoda konstruira kompleksni broj od zadanog realnog broja.
	 * Parametri su objasnjeni u nastavku;
	 * @param realniBroj realni broj kojeg pretvaramo u kompleksni broj.
	 * @return novokonstruirani kompleksni broj. 
	 */
	public static ComplexNumber fromReal(final double realniBroj) {
		ComplexNumber noviBroj = new ComplexNumber(realniBroj, 0.0);
		return noviBroj;
	}
	
	/**
	 * Metoda konstruira kompleksni broj od zadanog imaginarnog broja.
	 * Parametri su objasnjeni u nastavku;
	 * @param imaginarniBroj strogo imaginarni broj kojeg pretvaramo u kompleksni broj.
	 * @return novokonstruirani kompleksni broj. 
	 * @param imaginarniBroj imaginarni broj kojeg pretvaramo u kompleksni broj-
	 * instancu razreda ComplexNumber.
	 * @return novokonstruirana instanca razreda ComplexNumber.
	 */
	public static ComplexNumber fromImaginary(final double imaginarniBroj) {
		ComplexNumber noviBroj = new ComplexNumber(0.0, imaginarniBroj);
		return noviBroj;
	}
	
	/**
	 * Metoda konstruira kompleksni broj sa zadanim modulom broja te kutom otklona od pozitivnog
	 * dijela x-osi.
	 * Konstruirani broj je oblika modul*(cos(kut) + i * sin(kut)).
	 * @param modulBroja modul novokonstruiranog kompleksnog broja (sqrt(realniDio^2 + imaginarniDio^2)).
	 * @param kut kut otklona kompleksnog broja od pozitivnog dijela x-osi.
	 * @return novokonstruirani kompleksni broj.
	 */
	public static ComplexNumber fromMagnitudeAndAngle(final double modulBroja, final double kut) {
		if(modulBroja < 0) {
			throw new IllegalArgumentException("Magnitude can not be negative.");
		}
		ComplexNumber noviBroj = new ComplexNumber(modulBroja * Math.cos(kut), 
												   modulBroja * Math.sin(kut));
		return noviBroj;
	}
	
	/**
	 * Metoda vraca kompleksni broj koji je zapisan u proslijedenom stringu.
	 * Parametri su objasnjeni u nastavku;
	 * @param s string koji reprezentira kompleksni broj.
	 * @return novokreirana instanca razreda ComplexNumber.
	 * @throws IllegalArgumentException ako string nije dobrog formata.
	 */
	public static ComplexNumber parse(final String s) {
		final Pattern complexRegex = Pattern.compile(
				"(?<real>[-+]?\\d+(?:\\.\\d*)?)(?<imag>[-+](?:\\d+(?:\\.\\d*)?)?i)?|" + 
				"(?<imagOnly>[-+]?(?:\\d+(?:\\.\\d*)?)?i)");
		final Matcher matcher = complexRegex.matcher(s);
		String realPart;
		String imaginaryPart;
		if (!matcher.matches()) {
			throw new IllegalArgumentException();
		}
		realPart = matcher.group("real");
		
		if (realPart == null) {
			realPart = "0";
			imaginaryPart = matcher.group("imagOnly");
		} else {
			imaginaryPart = matcher.group("imag");
		}
		if (imaginaryPart == null) {
			imaginaryPart = "0";
		} else if (imaginaryPart.length() == 1) {
			imaginaryPart = "1";
		} else if (imaginaryPart.length() == 2 && !Character.isDigit(imaginaryPart.charAt(0))) {
			imaginaryPart = imaginaryPart.charAt(0) + "1";
		} else {
			imaginaryPart = imaginaryPart.substring(0, imaginaryPart.length() - 1);
		}
		return new ComplexNumber(Double.parseDouble(realPart), Double.parseDouble(imaginaryPart));
	}
	
	/**
	 * Metoda vraca realni dio broja na kojem je pozvana.
	 * @return realniDio, tipa double.
	 */
	public double getReal() {
		return realniDio;
	}
	
	/**
	 * Metoda vraca imaginarni dio broja na kojem je pozvana.
	 * @return realni dio, tipa double.
	 */
	public double getImaginary() {
		return imaginarniDio;
	}
	
	/**
	 * Metoda vraca modul kompleksnog broja na kojem je pozvana.
	 * @return modul broja, tipa double.
	 */
	public double getMagnitude() {
		return Math.sqrt(realniDio * realniDio + imaginarniDio * imaginarniDio);
	}
	
	/**
	 * Metoda vraca kut za koji je kompleksni broj na kojem je pozvana otklonjem
	 * od pozitivnog dijela x-osi.
	 * Kut je iz intervala [0, 2PI).
	 * @return kut kompleksnog broj, tipa double.
	 */
	public double getAngle() {
		final double kut = Math.atan2(imaginarniDio, realniDio);
		return kut < 0 ? kut + 2 * Math.PI : kut;
	}
	
	/**
	 * Metoda vraca novi broj konstruiran kao zbroj kompleksnog broja na kojem
	 * je pozvana te proslijedenog kompleksnog broja.
	 * @param broj kompleksni broj s kojim zbrajamo.
	 * @return novokonstruirani zbroj kompleksnih brojeva, tipa ComplexNumber.
	 */
	public ComplexNumber add(final ComplexNumber broj) {
		return new ComplexNumber(realniDio + broj.realniDio, 
								imaginarniDio + broj.imaginarniDio);	
	}
	
	/**
	 * Metoda vraca novi broj konstruiran kao razlika kompleksnog broja na kojem
	 * je pozvana te proslijedenog kompleksnog broja.
	 * @param broj kompleksni broj koji oduzimamo.
	 * @return novokonstruirana razlika kompleksnih brojeva, tipa ComplexNumber.
	 */
	public ComplexNumber sub(final ComplexNumber broj) {
		return new ComplexNumber(realniDio - broj.realniDio, 
				imaginarniDio - broj.imaginarniDio);	
	}
	
	/**
	 * Metoda vraca novi broj konstruiran kao umnozak kompleksnog broja na kojem
	 * je pozvana te proslijedenog kompleksnog broja.
	 * @param broj kompleksni broj s kojim mnozimo.
	 * @return novokonstruirani umnozak kompleksnih brojeva, tipa ComplexNumber.
	 */
	public ComplexNumber mul(final ComplexNumber broj) {
		return new ComplexNumber(realniDio * broj.realniDio - 
								imaginarniDio * broj.imaginarniDio,
								realniDio * broj.imaginarniDio + 
								imaginarniDio * broj.realniDio);
	}
	
	/**
	 * Metoda vraca novi broj konstruiran kao kvocijent kompleksnog broja na kojem
	 * je pozvana te proslijedenog kompleksnog broja.
	 * Metoda izbacuje IllegalArgumentException ako dođe do dijeljenja s 0.
	 * @param broj kompleksni broj s kojim dijelimo.
	 * @return novokonstruirani kvocijent kompleksnih brojeva, tipa ComplexNumber.
	 * @throws IllegalArgumentException ako dolazi do  dijeljenja s 0.
	 */
	public ComplexNumber div(final ComplexNumber broj) {
		if(broj.imaginarniDio == 0 && broj.realniDio == 0) {
			throw new IllegalArgumentException("dividing by zero.");
		}
		double modulBroj = broj.imaginarniDio * broj.imaginarniDio + 
						   broj.realniDio * broj.realniDio;
		double realniNovi = (realniDio * broj.realniDio + 
				imaginarniDio * broj.imaginarniDio) / modulBroj;
		double imaginarniNovi = (imaginarniDio * broj.realniDio - 
				realniDio * broj.imaginarniDio) / modulBroj;
		return new ComplexNumber(realniNovi, imaginarniNovi);	
	}
	
	/**
	 * Metoda vraca n-tu potenciju kompleknog broja na kojem je pozvana.
	 * @param n zeljena potencija.
	 * @return n-ta potencija kompleksnog broja.
	 */
	public ComplexNumber power(final int n) {
		if(n == 0) {
			return new ComplexNumber(1.0, 0);
		}
		double modulNovi = Math.pow(getMagnitude(), n);
		double kutNovi = n * getAngle();
		/*De-Moivreov teorem za potenciranje kompleksnih brojeva u polarnom obliku.*/
		return new ComplexNumber(modulNovi * Math.cos(kutNovi), 
				modulNovi * Math.sin(kutNovi));
	}
	
	/**
	 * Metoda vraca polje objektata tipa ComplexNumber u kojem su pohranjeni 
	 * n-ti korijeni broja na kojem je metoda pozvana.
	 * @param n zeljeni korijen.
	 * @return korijeni broja, tipa ComplexNumber[].
	 */
	public ComplexNumber[] root(final int n) {
		ComplexNumber[] korijeni = new ComplexNumber[n];
		for(int i = 0; i < n; ++i) {
			korijeni[i] = new ComplexNumber(Math.pow(getMagnitude(), 1.0 / n) * 
					Math.cos((getAngle() + 2 * i * Math.PI) / n), 
					Math.pow(getMagnitude(), 1.0 / n) * Math.sin((getAngle() +
							2 * i * Math.PI) / n));
		}
		return korijeni;
	}
	
	/**
	 * Metoda vraca String reprezentaciju kompleksnog broja u obliku
	 * "realniDio +- imaginarniDioi", npr "1 + 2i";
	 * @return String reprezentacija kompleksnog broja.
	 */
	@Override
	public String toString() {
		if(imaginarniDio < 1e-15 && imaginarniDio > -1e-15) {
			return Double.toString(realniDio);
		}
		else if(realniDio < 1e-15 && realniDio > -10e-15) {
			return Double.toString(imaginarniDio) + "i";
		}
		else if(imaginarniDio < 0) {
			return Double.toString(realniDio) + imaginarniDio + "i";
		}
		else {
			return Double.toString(realniDio) + "+" + imaginarniDio + "i";
		}
	}
}

