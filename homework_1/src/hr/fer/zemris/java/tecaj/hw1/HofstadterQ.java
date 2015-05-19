package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program izracunava i ispisuje zadani Hofstadterov QBroj.
 * Koji je to broj po redu, unosi se kao argument naredbenog retka.
 * 
 * @param i redni broj zeljenog Hofstadterovog QBroja.
 * 
 * @author Jelena Drzaic
 *
 */
public class HofstadterQ {
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Kao argumente prima sljedece;
	 * @param args redni broj zeljenog Hofstadterovog QBroja
	 */
	
	public static void main(String[] args) {
		
		if(args.length != 1)  {
			System.err.println("Program expects a natural number as its argument!");
			System.exit(1);
		}
		long i = Long.parseLong(args[0]);
		if(i <= 0) {
			System.err.println("Program expects a natural number as its argument!");
		}
		System.out.print("You requested calculation of " + i + ". number of Hofstadter's Q-sequence. " +
				"The requested number is " + HofstadterQBroj(i) + ".");
	}
	/**
	 * metoda racuna i vraca n-ti Hofstadterov QBroj.
	 * @param n redni broj Hofstadterovog QBroja.
	 * @return n-ti Hofstadterov QBroj.
	 */
	 static long HofstadterQBroj(long n) {
	
		if(n == 1) return 1;
		if(n == 2) return 1;
		return HofstadterQBroj(n - HofstadterQBroj(n - 1)) +
			   HofstadterQBroj(n - HofstadterQBroj(n - 2));
	}
}

