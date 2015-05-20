package hr.fer.zemris.java.tecaj.hw4.grafika;
import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred izveden iz razreda GeometrijskiLik.
 * Sluzi za reprezentaciju pravokutnika sa stranicama paralelnim s x i y osi.
 * @author Jelena Drzaic
 *
 */
public class Pravokutnik extends GeometrijskiLik {
	
	/**
	 * x koordinata gornjeg lijevog kuta pravokutnika
	 */
	int x0;
	
	/**
	 * y koordinata gornjeg lijevog kuta pravokutnika
	 */
	int y0;
	
	/**
	 * sirina pravokutnika
	 */
	int sirina;
	
	/**
	 * visina pravokutnika
	 */
	int visina;
	
	/**
	 * Konstruktor razreda.
	 * @param x0 x koordinata gornjeg lijevog kuta pravokutnika
	 * @param y0 y koordinata gornjeg lijevog kuta pravokutnika
	 * @param sirina sirina pravokutnika
	 * @param visina visina pravokutnika
	 */
	public Pravokutnik(int x0, int y0, int sirina, int visina) {
		this.x0 = x0;
		this.y0 = y0;
		this.sirina = sirina;
		this.visina = visina;
	}
	
	@Override
	public boolean sadrziTocku(int x, int y) {
		if(x < x0 || x > x0 + sirina) {
			return false;
		}else if(y < y0 || y > y0 + visina) {
			return false;
		}
		return true;
	}
	
	@Override
	public void popuniLik(Slika slika) {
		int xPoc = x0;
		int yPoc = y0;
		//crtamo samo vidljiv dio
		if(x0 < 0) {
			xPoc = 0;
		}
		int sirinaSlike = slika.getSirina();
		//pravokutnik ne stane na sliku
		if(x0 >= sirinaSlike) {
			return;
		}
		if(y0 < 0) {
			yPoc = 0;
		}
		int visinaSlike = slika.getVisina();
		if(y0 >= visinaSlike) {
			return;
		}
		//donji rub pravokutnika
		int granicay = Math.min(visinaSlike - 1, y0 + visina - 1);
		//desni rub pravkutnika
		for(int x = xPoc, granicax = Math.min(sirinaSlike - 1, x0 + sirina - 1);
				x < granicax; ++x) {
			for(int y = yPoc; y < granicay; ++y) {
				slika.upaliTocku(x, y);
			}
		}
	}
	
	public static final StvarateljLika STVARATELJ  = new PravokutnikStvaratelj();
	
	/**
	 * Razred implementira sucelje StvarateljLika.
	 * @author Jelena Drzaic
	 *
	 */
	private static class PravokutnikStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {
			return "PRAVOKUTNIK"; 
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			String[] argumenti = parametri.split(" ");
			if(argumenti.length != 4) {
				throw new IllegalArgumentException("Broj parametara nije dobar.");
			}
			int x0, y0, sirina, visina;
			try {
				x0 = Integer.parseInt(argumenti[0]);
				y0 = Integer.parseInt(argumenti[1]);
				sirina = Integer.parseInt(argumenti[2]);
				visina = Integer.parseInt(argumenti[3]);
			}catch(NumberFormatException e) {
				throw e;
			}
			return new Pravokutnik(x0, y0, sirina, visina);
		}
	}
}
