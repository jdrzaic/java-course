package hr.fer.zemris.java.tecaj.hw4.grafika;
import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred izveden iz razreda GeometrijskiLik.
 * Razred reprezentira duzinu u ravnini.
 * @author Jelena Drzaic
 *
 */
public class Linija extends GeometrijskiLik{
	
	/**
	 * x koordinata pocetne tocke
	 */
	private int x1;
	
	/**
	 * y koordinata pocetne tocke
	 */
	private int y1;
	
	/**
	 * x koordinata krajnje tocke
	 */
	private int x2;
	
	/**
	 * y koordinata krajnje tocke
	 */
	private int y2;
	
	/**
	 * Konstruktor razreda.
	 * @param x1 x koordinata poc. tocke
	 * @param y1 y koordinata poc. tocke
	 * @param x2 x koordinata krajnje tocke
	 * @param y2 y koordinata krajnje tocke
	 */
	public Linija(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public boolean sadrziTocku(int x, int y) {
		int vektProdukt = (x1 - x2) * (y1 - y) - (y1 - y2) * (x1 - x);
		if(vektProdukt == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metoda vraca vrijednost x koordinate tocke na duzini s y koordinatom 
	 * proslijedenog iznosa. 
	 * @param y vrijednost y koordinate
	 * @return vrijednost x koordinate tocke na duzini.
	 */
	private int solveX(int y) {
		if(y1 == y2)  {
			//ne koristi se u kodu
			return x1;
		}
		double x = ((double)(x2-x1) / (y2 - y1)) * (double)(y - y1) + x1;
		return x - (int)x < 0.5 ? (int)x : (int)x + 1;
	}
	
	/**
	 * Metoda vraca vrijednost y koordinate tocke na duzini s x koordinatom 
	 * proslijedenog iznosa. 
	 * @param x vrijednost x koordinate
	 * @return vrijednost y koordinate tocke na duzini.
	 */
	private int solveY(int x) {
		if(x1 == x2) {
			throw new IllegalArgumentException("dijeljenje s 0.");
		}
		double y = ((double)(y2 - y1) / (x2 - x1)) * (double)(x - x1) + (double)y1;
		return y - (int)y < 0.5 ? (int)y : (int)y + 1;
	}
	
	/**
	 * Metoda crta duzinu.
	 * @param x0 x koordinata lijeve tocke
	 * @param y0 y koordinata lijeve tocke
	 * @param x1 x koordinata desne tocke
	 * @param y1 y koordinata desne tocke
	 * @param slika slika na koju iscrtavamo
	 */
	private void nacrtaj(int x0, int y0, int x1, int y1, Slika slika) {
	
		int dy = y1 - y0;
		int dx = x1 - x0;
		if(dx == 0 && dy == 0) {
			slika.upaliTocku(x0, y0);
			return;
		}
		if(Math.abs(dy) <= dx) {
			double k = ((double)dy) / dx;
			double y = y0;
			for(int x = x0; x <= x1; ++x) {
				slika.upaliTocku(x, (int)Math.round(y));
				y += k;
			}
		} else {
			double k = ((double)dx) / dy;
			double x = x0;
			for(int y = Math.min(y0, y1), granica = Math.max(y0, y1); 
					y <= granica; ++y) {
				slika.upaliTocku((int)Math.round(x), y);
				x += k;
			}
		}
		/*int dx = x1 - x0;
		int dy = y1 - y0;
		int D = 2 * dy - dx;
		slika.upaliTocku(x0, y0);
		if(x0 == x1) { 
			return;
		}
		int y = y0;
		System.out.println(x0 + " " + x1);
		for(int x = x0 + 1; x < x1; ++x) {
			if(D > 0) {
				if(y + 1 >= slika.getVisina()){ 
					break;
				}
				y  = y + 1;
				if(y1 < y0) {
					slika.upaliTocku(-x, y);
				}else {
					slika.upaliTocku(x, y);
				}
			}else {
				if(y1 < y0) {
					slika.upaliTocku(-x, y);
				}else {
					slika.upaliTocku(x, y);
				}
				D = D + 2 * dy;
			}
		}*/
	}
	
	
	@Override
	public void popuniLik(Slika slika) {
		//lijevija tocka
		int xPoc = x1 < x2 ? x1 : x2;
		int yPoc = x1 < x2 ? y1 : y2;
		//desna tocka
		int xKraj = x1 < x2 ? x2 : x1;
		int yKraj = x1 < x2 ? y2 : y1;
		
		int sirinaSlike = slika.getSirina();
		int visinaSlike = slika.getVisina();
		//nemamo sto crtati
		if(xPoc >= sirinaSlike || xKraj < 0) {
			return;
		}
		
		//"rezemo" slijeva
		if(xPoc < 0) {
			xPoc = 0;
			yPoc = solveY(xPoc);
		}
		//"rezemo" zdesna
		if(xKraj >= sirinaSlike) {
			xKraj = sirinaSlike - 1;
			yKraj = solveY(xKraj);
		}
		
		if((yPoc < 0 && yKraj < 0) || (yPoc >= visinaSlike && yKraj >= visinaSlike)) {
			return;
		}
		
		
		//rezemo gore
		if(yPoc < 0) {
			yPoc = 0;
			xPoc = solveX(yPoc);
		}
		
		//rezemo dolje
		if(yPoc >= visinaSlike) {
			yPoc = visinaSlike - 1;
			xPoc = solveX(yPoc);
		}
		if(yKraj < 0) {
			yKraj = 0;
			xKraj = solveX(yKraj);
		}
		if(yKraj >= visinaSlike) {
			yKraj = visinaSlike - 1;
			xKraj = solveX(yKraj);
		}
		nacrtaj(xPoc, yPoc, xKraj, yKraj, slika);
	}
	
	public static final StvarateljLika STVARATELJ = new LinijaStvaratelj();
	
	/**
	 * Razred implementira sucelje StvarateljLika.
	 * @author Jelena Drzaic
	 *
	 */
	private static class LinijaStvaratelj implements StvarateljLika {

		@Override
		public String nazivLika() {
			return "LINIJA";
		}

		@Override
		public GeometrijskiLik stvoriIzStringa(String parametri) {
			String[] argumenti = parametri.split(" ");
			if(argumenti.length != 4) {
				throw new IllegalArgumentException("potrebno je liniju "
						+ "zadati s dvije tocke.");
			}
			int x1, y1, x2, y2;
			try {
				x1 = Integer.parseInt(argumenti[0]);
				y1 = Integer.parseInt(argumenti[1]);
				x2 = Integer.parseInt(argumenti[2]);
				y2 = Integer.parseInt(argumenti[3]);
			}catch(NumberFormatException e) {
				throw e;
			}
			return new Linija(x1, y1, x2, y2);
		}
	}
}
