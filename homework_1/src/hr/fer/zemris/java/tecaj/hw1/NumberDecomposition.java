package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program ispisuje na ekran dekompoziciju broja na proste faktore.
 * Broj dobiva kao argument naredbenog retka.
 * Ako se broj ne unese kao argument neredbenog retka, program prekida izvrsavanje,
 * uz poruku o gresci.
 * 
 * @author Jelena Drzaic
 *
 */
public class NumberDecomposition {
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
		int broj = Integer.parseInt(args[0]);
		System.out.println("You requested decomposition of number " + broj + " onto prime factors. Here they are:");
		dekompozicija(broj);
	}
	
	/**
	 * Metoda pronalazi rastav broja na proste faktore te ih ispisuje.
	 * Brojevi se ispisuju uzlaznim poretkom.
	 * @param broj broj cije proste faktore trazimo.
	 * @return metoda nema povratne vrijednosti.
	 */
	static void dekompozicija(int broj) {
		
		int brojacFaktora = 0;
		int[] faktor = new int[2];
		/*provjeravamo samo brojeve oblika 6k+1 i 6k-1, k prirodan broj
		 budući da su ostali brojevi djeljivi s barem jednim brojem razlicitim od 1 
		 i samog sebe(6k mod 6 = 0, 6k + 2 mod 2 = 0 = 6k + 4 mod 2, 
		 6k + 3 mod 3 = 0)*/
		for(int k = 0; (6 * k - 1) * (6 * k - 1) <= broj; ++k) {
			faktor[0] = k == 0 ? 2 : 6 * k - 1;
			faktor[1] = k == 0 ? 3 : 6 * k + 1;
			for(int index = 0; index < 2; ++index) {
				while(broj % faktor[index] == 0) {
					++brojacFaktora;
					System.out.println(brojacFaktora + ". " + faktor[index]);
					broj /= faktor[index];
				}
			}
		}
		/*broj koji je ostao je prost broj veci od sqrt(broj), postoji najvise jedan takav */
		if(broj > 1) {
			++brojacFaktora;
			System.out.println(brojacFaktora + ". " + broj);
		}
	}
}

