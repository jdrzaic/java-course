package hr.fer.zemris.java.tecaj.hw4.grafika;
import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Apstraktni razred.
 * Sluzi za reprezentaciju razlicitih geometrijskih likova.
 * @author Jelena Drzaic
 *
 */
public abstract class GeometrijskiLik {
	
	/**
	 * Metoda za danu tocku provjerava sadrzi li je geometrijski lik na kojem 
	 * je pozvana.
	 * @param x x koordinata tocke.
	 * @param y y koordinata tocke.
	 * @return true ako lik sadrzi tocku, false inace.
	 */
	public abstract boolean sadrziTocku(int x, int y);
	
	/**
	 * Metoda iscrtava zadani lik.
	 * @param slika slika na koju iscrtavamo lik.
	 */
	public void popuniLik(Slika slika) {
		for(int y = 0,visina = slika.getVisina(), sirina = slika.getSirina(); 
		y < visina; ++y) {
			for(int x = 0; x < sirina; ++x) {
				if(this.sadrziTocku(x, y)) {
					slika.upaliTocku(x, y);
				}
			}
		}
	}
}
