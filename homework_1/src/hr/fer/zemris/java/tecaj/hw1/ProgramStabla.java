package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program demonstrira mogucnosti koristenja binarnog stabla.
 * Ne prima parametre.
 *
 */

public class ProgramStabla {

	/**
	 *Klasa definira cvor unutar stabla.
	 *
	 */
	static class CvorStabla {
		CvorStabla lijevi;
		CvorStabla desni;
		String podatak;
	}

	/**
	 * Metoda se poziva prilikom pokretanja programa.
	 *
	 */
	public static void main(String[] args) {
		CvorStabla cvor = null;
		cvor = ubaci(cvor, "Jasna");
		cvor = ubaci(cvor, "Ana");
		cvor = ubaci(cvor, "Ivana");
		cvor = ubaci(cvor, "Anamarija");
		cvor = ubaci(cvor, "Vesna");
		cvor = ubaci(cvor, "Kristina");
		System.out.println("Ispisujem stablo inorder:");
		ispisiStablo(cvor);
		int vel = velicinaStabla(cvor);
		System.out.println("Stablo sadrzi elemenata: "+vel);
		boolean pronaden = sadrziPodatak(cvor, "Ivana");
		System.out.println("Trazeni podatak je pronaden: "+pronaden);
	}

	/**
	 * Metoda provjerava nalazi li se zadani podatak u nekom od cvorova
	 * u stablu. Ako takav postoji, vraca true, inace false.
	 * @param korijen korijen stabla u kojem trazimo podatak
	 * @param podatak trazeni podatak
	 * @return true ako je podatak pronaden, false inace
	 */
	static boolean sadrziPodatak(CvorStabla korijen, String podatak) {
		if(korijen == null) {
			return false;
		}
		if(korijen.podatak == podatak) {
			return true;
		}
		if(podatak.compareTo(korijen.podatak) < 0) {
			return sadrziPodatak(korijen.lijevi, podatak);
		}
		else {
			return sadrziPodatak(korijen.desni, podatak);
		}
	}

	/**
	 * Metoda vraca broj cvorova pohranjenih unutar stabla.
	 * @param cvor korijen stabla ciju velicinu trazimo.
	 * @return broj cvorova stabla.
	 */
	static int velicinaStabla(CvorStabla cvor) {
		if(cvor.lijevi == null && cvor.desni == null) {
			return 1;
		}
		else if(cvor.lijevi == null) {
			return velicinaStabla(cvor.desni) + 1;
		}
		else if(cvor.desni == null) {
			return velicinaStabla(cvor.lijevi) + 1;
		}
		return 1 + velicinaStabla(cvor.desni) + velicinaStabla(cvor.lijevi);
	}

	/**
	 * Metoda sluzi za ubacivanje cvorova s podatkom "podatak"
	 * u stablo s korijenom korijen.
	 * @param korijen korijen stabla u koje ubacujemo
	 * @param podatak podatak koji ubacujemo
	 */
	static CvorStabla ubaci(CvorStabla korijen, String podatak) {
		if(korijen == null) {
			korijen = new CvorStabla();
			korijen.podatak = podatak;
			korijen.lijevi = korijen.desni = null;
			return korijen;
		}
		else {
			if(podatak.compareTo(korijen.podatak) < 0) {
				korijen.lijevi = ubaci(korijen.lijevi, podatak);
				return korijen;
			}
			else {
				korijen.desni = ubaci(korijen.desni, podatak);
				return korijen;
			}
		}
	}

	/**
	 * Metoda sluzi za ispisivanje sadrzaja stabla.
	 * @param cvor korijen stabla koje ispisujemo
	 * @return nema povratnih vrijednosti
     */
	static void ispisiStablo(CvorStabla cvor) {
		if(cvor == null) {
			return;
		}
		if(cvor.lijevi != null) {
			ispisiStablo(cvor.lijevi);
		}
		System.out.println(cvor.podatak);
		if(cvor.desni != null) {
			ispisiStablo(cvor.desni);
		}
	}
}

