package hr.fer.zemris.java.tecaj.hw4.grafika;

/**
 * Sucelje reprezentira metodu za stvaranje novih geometrijskih likova.
 * @author Jelena Drzaic
 *
 */
public interface StvarateljLika {
	
	/**
	 * Metoda vraca naziv(vrstu) lika na kojem je pozvana.
	 * @return naziv lika na kojem je pozvana. 
	 */
	String nazivLika();
	
	/**
	 * Metoda prima parametre za stvaranje novog lika u obliku Stringa, odvojene jednim razmakom(spaceom).
	 * @param parametri String u kojem su zapisani parametri za stvaranje novog lika.
	 * @return novokreirani geometrijski lik.
	 */
	GeometrijskiLik stvoriIzStringa(String parametri);
}
