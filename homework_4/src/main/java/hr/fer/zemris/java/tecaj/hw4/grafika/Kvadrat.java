package hr.fer.zemris.java.tecaj.hw4.grafika;

/**
 * Razred izveden iz razreda Pravokutnik.
 * Razred sluzi za reprezentaciju kvadrata.
 * @author Jelena Drzaic
 *
 */
public class Kvadrat extends Pravokutnik {
	
	/**
	 * Konstruktor razreda.
	 * @param x0 x koordinata gornjeg lijevog ruba.
	 * @param y0 y koordinata gornjeg lijevog ruba.
	 * @param duljina duljina stranice kvadrata.
	 */
	public Kvadrat(int x0, int y0, int duljina) {
		super(x0, y0, duljina, duljina);
	}
	
	public static final StvarateljLika STVARATELJ = new KvadratStvaratelj();
	
	/**
	 * Razred implementira sucelje StvarateljLika.
	 * @author Jelena Drzaic
	 *
	 */
	private static class KvadratStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {
			return "KVADRAT";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			String[] argumenti;
			argumenti = parametri.split(" ");
			if(argumenti.length < 3 || argumenti.length > 4) {
				throw new IllegalArgumentException("Broj argumenata nije dobar.");
			}
			int x0, y0, sirina, visina;
			try {
				x0 = Integer.parseInt(argumenti[0]);
				y0 = Integer.parseInt(argumenti[1]);
				sirina = Integer.parseInt(argumenti[2]);
				//duljina i sirina ako su prosliejdene moraju biti jednake
				if(argumenti.length == 4) {
					visina = Integer.parseInt(argumenti[3]);
					if(sirina !=  visina) {
						throw new IllegalArgumentException("Krivi argumenti, "
								+ "nemoguće konstruirati kvadrat.");
					}
				}
			}catch(NumberFormatException e) {
				throw e;
			}
			return new Kvadrat(x0, y0, sirina);
		}
		
	}
	
}
