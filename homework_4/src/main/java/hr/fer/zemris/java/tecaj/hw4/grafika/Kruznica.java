package hr.fer.zemris.java.tecaj.hw4.grafika;

/**
 * Razred naslijeden iz razreda ELipsa.
 * Razred sluzi za reprezentaciju kruga.
 * @author Jelena Drzaic
 *
 */
public class Kruznica extends Elipsa {
	
	/**
	 * Konstruktor razreda.
	 * @param x0 x koordinata sredista
	 * @param y0 y koordinata sredista
	 * @param radijus radijus kruznice
	 */
	public Kruznica(int x0, int y0, int radijus) {
		super(x0, y0, radijus, radijus);
	}
	
	public static final StvarateljLika STVARATELJ = new KruznicaStvaratelj();
	
	/**
	 * Razred implementira sucelje StvarateljLika
	 * @author Jelena Drzaic
	 *
	 */
	private static class KruznicaStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {
			return "KRUG";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			String[] argumenti = parametri.split(" ");
			if(argumenti.length < 3 || argumenti.length > 4) {
				throw new IllegalArgumentException("potrebno je kruznicu"
						+ "zadati s sredistem i radijusom.");
			}
			int x1, y1, radx, rady;
			try {
				x1 = Integer.parseInt(argumenti[0]);
				y1 = Integer.parseInt(argumenti[1]);
				radx = Integer.parseInt(argumenti[2]);
				//ako su predana 2 radijusa, moraju biti jednaka
				if(argumenti.length == 4) {
					rady = Integer.parseInt(argumenti[3]);
					if(radx != rady) {
						throw new IllegalArgumentException("nemoguće konsturirati"
								+ "kruznicu.");
					}
				}
			}catch(NumberFormatException e) {
				throw e;
			}
			return new Kruznica(x1, y1, radx);
		}
		
	}
}
