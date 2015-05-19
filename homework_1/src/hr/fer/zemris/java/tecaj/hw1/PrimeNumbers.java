package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program pronalazi i ispisuje prvih n prostih brojeva.
 * Broj n unosi se kao argument naredbenog retka.
 * Kao prvi prost broj uzimamo 2.
 * @param n broj prostih brojeva.
 * 
 * @author Jelena Drzaic
 *
 */

public class PrimeNumbers {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Kao argumente prima sljedece;
	 * @param args Argumenti iz naredbenog retka.
	 */
	public static void main(String[] args) {
		
		if(args.length != 1)  {
			System.err.println("Program expects a natural number as its argument!");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		System.out.println("You requested calculation of " + n + " prime numbers. Here they are:");
		nPrvihProstih(n);
	
	}
	/**
	 * 	Metoda koja provjerava je li proslijedeni broj prost.
	 * Ako je broj prost, vraca true, inace vraca false.
	 * @param broj broj za kojeg zelimo saznati je li prost.
	 * @return metoda true ako je dan broj prost, false inace.
	 */
	static boolean jeProst(int broj) {
		
		/*provjeravamo samo brojeve oblika 6k+1 i 6k-1, k prirodan broj
		 budući da su ostali brojevi djeljivi s barem jednim brojem razlicitim od 1 
		 i samog sebe(6k mod 6 = 0, 6k + 2 mod 2 = 0 = 6k + 4 mod 2, 
		 6k + 3 mod 3 = 0)*/
		if(broj == 2 || broj == 3) {
			return true;
		}
		for(int k = 1; (6 * k - 1) * (6 * k - 2)  <= broj; ++k) {
			if(broj % (6 * k + 1) == 0 || broj % (6 * k - 1) == 0) {
				return false;
			}
		}
		return true;
	} 
	/**
	 * Metoda racuna i ispisuje prvih n prirodnih brojeva.
	 * @param n broj prvih prostih brojeva.
	 * @return metoda nema povratnih vrijednosti.
	 */
	static void nPrvihProstih(int n) {
		
		int brojProstih = 0, kandidat1, kandidat2;
		for(int k = 0;; ++k) {
			kandidat1 = k == 0 ? 2 : 6 * k - 1;
			kandidat2 = k == 0 ? 3 : 6 * k + 1;
			if(jeProst(kandidat1)) {
				System.out.println(++brojProstih + ". " + kandidat1);
			}
			if(brojProstih == n) {
				break;
			}
			if(jeProst(kandidat2)) {
				System.out.println(++brojProstih + ". " + kandidat2);
			}
			if(brojProstih == n) {
				break;
			}
		}
	}
}

