package hr.fer.zemris.java.fractals;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import hr.fer.zemris.java.fractals.complex.*;
import hr.fer.zemris.java.fractals.factory.DaemonicThreadPool;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;

/**
 * Razred implementira sucelje IFractalProducer.
 * Sluzi za rad s fraktalima nastalim Newton-Raphsonovim iteracijama.
 * @author jelena Drzaic
 *
 */
public class NewtonFractalProducer implements IFractalProducer{

	/**
	 * Maksimalni broj iteracija 
	 */
	private static final int N_OF_ITERATIONS = 16;
	
	/**
	 * Zadovoljavajuca okolina oko korijena polinoma
	 * da bismo zakljucili da tocka "konvergira" tom korijenu.
	 */
	private static final double ROOT_TRESHOLD = 0.0002;
	
	/**
	 * Zadovoljavajuca razlika n-te i n+1-e iteracije
	 */
	private static final double CONVERGENCE_TRESHOLD = 0.0001;
	
	/**
	 * Broj dretvi
	 */
	public static final int NUMBER_OF_THREADS = 8 * Runtime.getRuntime().
			availableProcessors();
	
	/**
	 * Polinom na temelju kojeg izracunavamo iteracije
	 */
	ComplexRootedPolynomial p;
	
	ExecutorService pool;
	
	/**
	 * Konstruktor razreda.
	 * Stvara novu instancu klase ExecutorService,
	 * 
	 * @param p polinom na temelju kojeg racunamo iteracije
	 */
	public NewtonFractalProducer(ComplexRootedPolynomial p) {
		this.p = p;
		 pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS, 
				 new DaemonicThreadPool());
	}
	
	/**
	 * Paralelna implementacija koja računa fraktal dobiven
	 * Newtonovim iteracijama.
	 * Omogućava da se u predano polje pohrane rezultati samo
	 * za zadani raspon y-koordinata (ostatak polja se ne dira).
	 * 
	 * @param reMin minimalna vrijednost po realnoj osi
	 * @param reMax maksimalna vrijednost po realnoj osi
	 * @param imMin minimalna vrijednost po imaginarnoj osi
	 * @param imMax maksimalna vrijednost po imaginarnoj osi
	 * @param width širina zaslona na kojem se prikazuje fraktal
	 * @param height visina zaslona na kojem se prikazuje fraktal
	 * @param m broj pokušaja otkrivanja divergencije
	 * @param yminu y-linija od koje se popunjava polje (uključiva)
	 * @param ymaxu y-linija do koje se popunjava polje (uključiva)
	 * @param data polje u koje treba pohraniti rezultat
	 */
	public short[] calculateParallel(double reMin, double reMax, double imMin, double imMax,
			int width, int height, int m, int yminu, int ymaxu, short[] data) {
		
			/**
			 * Razred implementira sucelje Callable
			 * @author jelena
			 *
			 */
			class Posao implements Callable<short[]> {
				
				/**
				 * Minimalna vertikalna "linija"
				 */
				int ymin; 
				
				/**
				 * Maksimalna vertikalna "linija"
				 */
				int ymax; 
				
				/**
				 * Konstruktor razreda
				 * @param ymin najmanji y za koji obavljamo racunanje
				 * @param ymax najveci y za koji obavljamo racunanje
				 */
				public Posao(int ymin, int ymax) {
					super();
					this.ymin = ymin;
					this.ymax = ymax;
				}
	
				/**
				 * Metoda racuna Newton-Raphesonove iteracije.
				 * Povratni argument je polje short-ova koji sadrze
				 * broj indeksa prema kojima su tocke konvergirale
				 */
				@Override
				public short[] call() {
					short[] data = new short[(ymax - ymin + 1) * width];
					int offset = 0; 
					for(int y = ymin; y <= ymax; y++) {
						for(int x = 0; x < width; x++) { 
							Complex c = new Complex(x/(width-1.0)*(reMax-reMin) + reMin, 
									((height-1)-y)/(height-1.0)*(imMax-imMin) + imMin);
							Complex zn = c;
							int iters = 0;
							double module;
							do {
								Complex numerator = p.apply(zn);
								Complex denominator = p.toComplexPolynom().derive().apply(zn);
								Complex fraction = numerator.divide(denominator);
								Complex zn1 = zn.sub(fraction);
								module = zn1.sub(zn).module();
								zn = zn1;
							} while(iters < N_OF_ITERATIONS && 
									 module > CONVERGENCE_TRESHOLD);
							int index = p.indexOfClosestRootFor(zn, ROOT_TRESHOLD);
							if(index == -1) {
								data[offset++] = 0;
							}
							else {
								data[offset++] = (short)index;
							}

						}
					}
					return data;
				}
			}
			
			List<Future<short[]>> rezultati = new ArrayList<Future<short[]>>();
		
			for(int i = 0; i < NUMBER_OF_THREADS; ++i) {
				rezultati.add(pool.submit(new Posao(yminu + i * (ymaxu - yminu) 
						/ NUMBER_OF_THREADS, yminu + (i + 1) * (ymaxu - yminu) / 
						NUMBER_OF_THREADS)));
			}
f:			for(int part = 0; part < rezultati.size(); ++part) {
				while(true) {
					try{
						for(int i = 0; i < rezultati.get(part).get().length; ++i) {
							data[(yminu + part * (ymaxu - yminu) / NUMBER_OF_THREADS) * width + i]
									= rezultati.get(part).get()[i];
						}
						continue f;
					}catch(Exception e) {
						System.out.println(e.getMessage() + e.getStackTrace());
					}
				}
			}
			return data;
	}
	
	@Override
	public void produce(double reMin, double reMax, double imMin, double imMax,
		int width, int height, long requestNo,
		IFractalResultObserver observer) {
		System.out.println("Započinjem izračune...");
		int m = 16*16*16;
		short[] data = new short[width*height];
		calculateParallel(reMin, reMax, imMin, imMax, width, height, m, 0, height-1, data);
		System.out.println("Izračuni gotovi...");
		observer.acceptResult(data, (short)(p.toComplexPolynom().order() + 1), requestNo);
		System.out.println("Dojava gotova...");
		
	}

}
