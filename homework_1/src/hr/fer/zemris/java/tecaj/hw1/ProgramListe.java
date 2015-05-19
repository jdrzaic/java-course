package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program demonstrira osnovne funkcionalnosti liste;
 * ubacivanje u listu, ispisivanje liste, sortiranje liste,
 * recunanje velicine liste.

 * @author Jelena Drzaic
 */

public class ProgramListe {

	/**
	 * Klasa sadrzi definiciju cvora liste.
	 *
	 */
	static class CvorListe {

		CvorListe sljedeci;
		String podatak;
	}

    /**
     * Metoda koja se poziva prilikom pokretanja programa.
     * Nema ulaznih argumenata.
     *
     */
	public static void main(String[] args) {

		CvorListe cvor = null;

		cvor = ubaci(cvor, "Jasna");
		cvor = ubaci(cvor, "Ana");
		cvor = ubaci(cvor, "Ivana");

		System.out.println("Ispisujem listu uz originalni poredak:");

		ispisiListu(cvor);
		cvor = sortirajListu(cvor);

		System.out.println("Ispisujem listu nakon sortiranja:");
		ispisiListu(cvor);

		int vel = velicinaListe(cvor);
		System.out.println("Lista sadrzi elemenata: "+vel);
	}

	static int velicinaListe(CvorListe cvor) {
		int brojElemenata = 0;
		while(cvor != null) {
			cvor = cvor.sljedeci;
			++brojElemenata;
		}
		return brojElemenata;
	}

	/**
	 * Metoda sluzi za ubacivanje elemenata u listu.
	 * Element se ubacuje na kraj liste.
	 * @param prvi Prvi element liste
	 * @param podatak String koji ubacujemo u listu(u njen novi cvor)
	 * @return prvi element promijenene liste
	 */
	static CvorListe ubaci(CvorListe prvi, String podatak) {
		CvorListe novi = new CvorListe(), tmp = prvi;
		novi.podatak = podatak;
		novi.sljedeci = null;
		if(prvi == null) {
			return novi;
		}
		while(true) {
			if(tmp.sljedeci == null) {
				tmp.sljedeci = novi;
				break;
			}
			tmp = tmp.sljedeci;
		}
		return prvi;
	}
	static void ispisiListu(CvorListe cvor) {
		while(cvor != null) {
			System.out.println(cvor.podatak);
			cvor = cvor.sljedeci;
		}
	}

	/**
	 * Metoda sluzi za sortiranje liste.
	 * Listu sortira leksikografski uzlazno(A-Z)
	 * Kod sortiranja koristi funkciju merge(CvorListe, CvorListe)
     * @param cvor prvi element liste
	 * @return Prvi element sortirane liste
	 */
	static CvorListe sortirajListu(CvorListe cvor) {
		int duljina = velicinaListe(cvor);
		CvorListe prvaLista = cvor, drugaLista = null;
		if(duljina == 1) {
			return cvor;
		}
		for(int i = 0; i < duljina / 2 - 1; ++i) {
			cvor = cvor.sljedeci;
		}
		drugaLista = cvor.sljedeci;
		cvor.sljedeci = null;
		prvaLista = sortirajListu(prvaLista);
		drugaLista = sortirajListu(drugaLista);

		return merge(prvaLista, drugaLista);
	}

	/**
	 * Metoda spaja dvije sortirane liste
	 * @param prvi prvi element prve jedne liste
	 * @param drugi prvi element druge liste
	 * @return prvi element novonastale "merge-ane" liste
	 */
	static CvorListe merge(CvorListe prvi, CvorListe drugi) {
		CvorListe novi = null, tmp = null;
		while(prvi != null && drugi != null) {
			if(prvi.podatak.compareTo(drugi.podatak) < 0) {
				if(novi == null) {
					novi = prvi;
					tmp = novi;
				}
				else {
					tmp.sljedeci = prvi;
					tmp = tmp.sljedeci;
				}
				prvi = prvi.sljedeci;
			}
			else {
				if(novi == null) {
					novi = drugi;
					tmp = novi;
				}
				else {
					tmp.sljedeci = drugi;
					tmp = tmp.sljedeci;
				}
				drugi = drugi.sljedeci;
			}
		}
		if(prvi == null) {
			tmp.sljedeci = drugi;
		}
		if(drugi == null) {
			tmp.sljedeci = prvi;
		}
		return novi;
	}
}

