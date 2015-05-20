package hr.fer.zemris.java.fractals.complex.parser;

import hr.fer.zemris.java.fractals.complex.Complex;

/**
 * Metoda sluzi za parse-anje stringova koji kao zapis sadrze kompleksni broj.
 * @author jelena Drzaic
 *
 */
public class ComplexParser {
	
	/**
	 * Metoda parse-a string u kompleksni broj,
	 * instancu klase Complex
	 * @param s string koji sadrzi zapis kompleksnog broja
	 * @return kompleksni broj dobiven iz stringa
	 * @throws NumberFormatException u slucaju nelegalnog stringa
	 */
	public static Complex parse(String s) throws NumberFormatException {
		if(s.trim().equals("i")) {
			return new Complex(0, 1);
		}
		if(s.trim().equals("-i")) {
			return new Complex(0, -1);
		}
		try {
			double re = Double.parseDouble(s.trim());
			return new Complex(re, 0);
		}catch(NumberFormatException e) {
		}
		try {
			double im  = Double.parseDouble(s.trim().substring(0, s.trim().
					length() - 1));
			return new Complex(0, im);
		}catch(NumberFormatException e) {
		}
		
		if(s.indexOf("+") > 0) {
			return splitBy(s, "\\+");
			
		}else if(s.lastIndexOf("-") != -1) {
			return splitBy(s, "-");
		}else {
			throw new NumberFormatException("Not a complex number");
		}
	}
	
	/**
	 * Metoda konstruira kompleksni broj 
	 * 
	 * @param s string koji sadrzi kompleksni broj
	 * @param regex + ili -, poveznica izmedu realnog i imaginarnog dijela u broju.
	 * @return kompleksni broj dobiven iz stringa
	 * @throws NumberFormatException ako string ne sadrzi kompleksni broj.
	 */
	private static Complex splitBy(String s, String regex) throws NumberFormatException {
		String[] parts;
		parts = s.split(regex);
		double im;
		double re;
		if(parts.length != 2) {
			throw new NumberFormatException("Not a complex number");
		}else {
			re = Double.parseDouble(parts[0]);
			if(parts[1].trim().equals("i")) {
				im = 1.0;
			}else {
				im = Double.parseDouble(parts[1].trim().substring(
						0, parts[1].trim().length() - 1));
			}
		}
		return new Complex(re, regex.equals("-") ? -im : im);
	}
	
}
