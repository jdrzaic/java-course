package hr.fer.zemris.java.tecaj.hw4.grafika;
import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred izveden iz razreda GeometrijskiLik.
 * Razred reprezentira elipsu.
 * @author Jelena Drzaic
 *
 */
public class Elipsa extends GeometrijskiLik {
	
	/**
	 * x koordinata sredista
	 */
	int x0;
	
	/**
	 * y koordinata sredista
	 */
	int y0;
	
	/**
	 * radijus u smjeru x osi
	 */
	int radx;
	
	/**
	 * radijus u smjeru y osi
	 */
	int rady;
	
	/**
	 * Konstruktor razreda Elipse.
	 * Ako je barem jedan od radijusa <= 0, metoda izabcuje IllegalArgumentException
	 * @param x0 x koordinata sredista.
	 * @param y0 y koordinata sredista.
	 * @param radx radijus u smjeru x osi.
	 * @param rady radijus u smjeru y osi.
	 */
	public Elipsa(int x0,int y0, int radx, int rady) {
		if(radx <= 0 || rady <= 0){
			throw new IllegalArgumentException("radius must be positive.");
		}
		this.x0 = x0;
		this.y0 = y0;
		this.radx = radx;
		this.rady = rady;
	}
	
	@Override
	public boolean sadrziTocku(int x, int y) {
		return ((double)(x - x0) / radx) * ((double)(x - x0) / radx) + 
				((double)(y - y0) / rady) * ((double)(y - y0) / rady) <= 1;
	}
	
	@Override
	public void popuniLik(Slika slika) {
		int sirinaSlike = slika.getSirina();
		int visinaSlike = slika.getVisina();
		//nista ne mozemo nacrtati
		if(x0 + radx < 0 || x0 - radx >= sirinaSlike) {
			return;
		}
		if(y0 + rady < 0 || y0 - rady >= visinaSlike) {
			return;
		}
		
		int xKut = x0 - radx < 0 ? 0: x0 - radx;
		int yKut = y0 - rady < 0 ? 0: y0 - rady;
		int granicay = Math.min(visinaSlike, yKut + 2 * rady);
		for(int x = xKut, granicax = Math.min(sirinaSlike, xKut + 2 * radx); 
				x < granicax; ++x) {
			for(int y  = yKut; y < granicay; ++y) {
				if(sadrziTocku(x,y)) {
					slika.upaliTocku(x, y);
				}
			}
		}
	}
	
	public static final StvarateljLika STVARATELJ = new ElipsaStvaratelj();
	
	/**
	 * Razred implementira sucelje StvarateljLika.
	 * @author Jelena Drzaic
	 *
	 */
	private static class ElipsaStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {
			return "ELIPSA";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			String[] argumenti = parametri.split(" ");
			if(argumenti.length != 4) {
				throw new IllegalArgumentException("potrebno je elipsu"
						+ "zadati s sredistem i radijusima.");
			}
			int x1, y1, radx, rady;
			try {
				x1 = Integer.parseInt(argumenti[0]);
				y1 = Integer.parseInt(argumenti[1]);
				radx = Integer.parseInt(argumenti[2]);
				rady = Integer.parseInt(argumenti[3]);
			}catch(NumberFormatException e) {
				throw e;
			}
			return new Elipsa(x1, y1, radx, rady);
		}
		
	}
}
