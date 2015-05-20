package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.complex.Complex;
import hr.fer.zemris.java.fractals.complex.ComplexRootedPolynomial;
import hr.fer.zemris.java.fractals.complex.parser.ComplexParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Razred predstavlja program za korisnicku interakciju s crtacem 
 * fraktala nastalih Newton-Raphsonovim iteracijama.
 * Jednom kad su uneseni potrebni parametri(korijeni polinoma),
 * u grafickom sucelju iscrtava se dobiveni fraktal.
 * @author jdrzaic
 *
 */
public class Newton {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa
	 * @param args argumenti komandne linije, zanemaruju se.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
		System.out.println("Please enter at least two roots, one root per line. "
				+ "Enter 'done' when done.");	
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
			String input;
			List<Complex> roots = new ArrayList<>();
			System.out.print("Root 1> ");
			int k = 2;
			while(!(input=br.readLine().trim()).equals("done")){
				System.out.print("Root "+ k + "> ");
				
				++k;
				Complex root;
				try {
				root = ComplexParser.parse(input);
				} catch(NumberFormatException e) {
					System.err.println("It is not a complex number, try again.");
					--k;
					continue;
				}
				roots.add(root);
			}
			Complex[] rootsArray = new Complex[roots.size()];
			
			for(int i = 0; i < rootsArray.length; ++i) {
				rootsArray[i] = roots.get(i);
			}
			
	        System.out.println("Image of fractal will appear shortly. Thank you.");

			ComplexRootedPolynomial p = new ComplexRootedPolynomial(rootsArray);
			NewtonImpl.showParallel(p);
		}catch(IOException io){
			System.err.println(io.getMessage());
			io.printStackTrace();
		}
	}
}
