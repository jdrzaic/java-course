package hr.fer.zemris.java.raytracer;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import hr.fer.zemris.java.raytracer.coordinates.SpaceParameters;
import hr.fer.zemris.java.raytracer.model.IRayTracerProducer;
import hr.fer.zemris.java.raytracer.model.LightSource;
import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Ray;
import hr.fer.zemris.java.raytracer.model.RayIntersection;
import hr.fer.zemris.java.raytracer.model.Scene;
import hr.fer.zemris.java.raytracer.viewer.RayTracerViewer;
import hr.fer.zemris.java.raytracer.model.IRayTracerResultObserver;
import hr.fer.zemris.java.raytracer.model.GraphicalObject;


/**
 * Razred implementira raycaster.
 * Izracuni se provode visedretveno.
 * @author Jelena Drzaic
 *
 */
public class RayCasterParallel {

	private static final double EPSILON = 0.01;
	
	//najveci broj redaka koji se obraduju sekvencijalno
	private static final int LIMIT = 50;
	/**
	 * Metoda koja se poziva prilikom pokretanja programa
	 * @param args argumenti komandne linije, zanemaruju se.
	 */
	public static void main(String[] args) {
		RayTracerViewer.show(getIRayTracerProducer(),
		new Point3D(10,0,0),
		new Point3D(0,0,0),
		new Point3D(0,0,10),
		20, 20);
	}
	
	/**
	 * Metoda vraca implementaciju IRayTracerProducer
	 * @return implementacija sucelja IRayTracerProducer.
	 */
	private static IRayTracerProducer getIRayTracerProducer() {
		return new IRayTracerProducer() {
		
			@Override
			public void produce(Point3D eye, Point3D view, Point3D viewUp,
				double horizontal, double vertical, int width, int height,
				long requestNo, IRayTracerResultObserver observer) {
				
				System.out.println("Započinjem izračune...");
				short[] red = new short[width*height];
				short[] green = new short[width*height];
				short[] blue = new short[width*height];
			
				Point3D zAxis = SpaceParameters.getZAxis(eye, view);
				viewUp = viewUp.modifyNormalize();
				Point3D yAxis = SpaceParameters.getYAxis(zAxis, eye, view, viewUp);
				Point3D xAxis = SpaceParameters.getXAxis(yAxis, zAxis,eye, view, viewUp);
				Point3D screenCorner = SpaceParameters.getCorner(xAxis, yAxis, viewUp, 
						horizontal, vertical - 1.5);
				Scene scene = RayTracerViewer.createPredefinedScene();
				
				ForkJoinPool pool = new ForkJoinPool();
				pool.invoke(new ThreadWork(scene, 0, height, width, height, horizontal,
						vertical, screenCorner, xAxis, yAxis, eye, green, red, blue));
				pool.shutdown(); //zavrsio poslove
				
				System.out.println("Izračuni gotovi...");
				observer.acceptResult(red, green, blue, requestNo);
				System.out.println("Dojava gotova...");
			}
		};
	}
	
	/**
	 * Posao koji obavlja jedna dretva
	 * @author jelena Drzaic
	 *
	 */
	private static class ThreadWork extends RecursiveAction{

		private static final long serialVersionUID = 1L;

		/**
		 * Scena koju promatramo
		 */
		Scene scene;
		
		/**
		 * Minimalna y komponenta
		 */
		int ym;
		
		/**
		 * Maksimalna y komponenta
		 */
		int yM; 
		
		int width;
		int height; 
		double horizontal; 
		double vertical;
		Point3D corner;
		Point3D xAxis;
		Point3D yAxis; 
		Point3D eye;
		short[] green; 
		short[] red;
		short[] blue;
		
		/**
		 * Konstruktor razreda.
		 * @param scene scena koju promatramo
		 * @param ym minimalna y komponenta
		 * @param yM2 maksimalna y komponenta
		 * @param width sirina 
		 * @param height visina 
		 * @param horizontal sirina
		 * @param vertical visina
		 * @param corner koordinate lijevog gornjeg kuta ravnine
		 * @param xAxis x os, jedinicni vektor
		 * @param yAxis y os, jedinicni vektor
		 * @param eye polozaj oka promatraca
		 * @param green polje informacija o intenzitetu zelene
		 * @param red polje informacija o intenzitetu zelene
		 * @param blue polje informacija o intenzitetu zelene
		 */
		public ThreadWork(Scene scene, int ym, int yM2, int width, int height,
				double horizontal, double vertical, Point3D corner,
				Point3D xAxis, Point3D yAxis, Point3D eye, short[] green,
				short[] red, short[] blue) {
			super();
			this.scene = scene;
			this.ym = ym;
			yM = yM2;
			this.width = width;
			this.height = height;
			this.horizontal = horizontal;
			this.vertical = vertical;
			this.corner = corner;
			this.xAxis = xAxis;
			this.yAxis = yAxis;
			this.eye = eye;
			this.green = green;
			this.red = red;
			this.blue = blue;
		}

		@Override
		protected void compute() {
			if(yM - ym  > LIMIT) {
				computeDirect();
				return;
			}else {
				invokeAll(new ThreadWork(scene, yM, ym + (yM - ym) / 2, 
						width, height, horizontal, vertical, corner, xAxis, yAxis, 
						eye, red, blue, green),
						new ThreadWork(scene, ym + (yM - ym) / 2, yM, width, height,
								horizontal, vertical, corner, xAxis, yAxis, eye, 
								red, blue, green));
			}
		}
		
		/**
		 * Metoda obavlja posao jedne dretve.
		 * Poziva se kada je raspon y manji od LIMIT
		 */
		private void computeDirect() {
			
			short[] rgb = new short[3];
			int offset = ym * width; //od kojeg piksela krecemo
			for(int y = ym; y < yM; y++) {
				for(int x = 0; x < width; x++) {
					
					Point3D screenPoint = SpaceParameters.getScreenPoint(
						horizontal, vertical, width, height, x, y, 
					xAxis, yAxis.negate(), corner);
					
					Ray ray = Ray.fromPoints(eye, screenPoint);
					tracer(scene, ray, rgb);
					red[offset] = rgb[0] > 255 ? 255 : rgb[0];
					green[offset] = rgb[1] > 255 ? 255 : rgb[1];
					blue[offset] = rgb[2] > 255 ? 255 : rgb[2];
					offset++;
				}
			}
		}
		
	}
	
	/**
	 * Metoda izracunava sjeciste vektora s objektom u sceni.
	 * Takoder, odreduje se boja kojom je piksel sjecista potrebno obojati.
	 * @param scene konstruirana scena
	 * @param ray vektor cije sjeciste s objektima trazimo.
	 * @param rgb boje piksela, popunjavamo ih.
	 */
	public static void tracer(Scene scene, Ray ray, short[] rgb) {
		
		RayIntersection closest = findClosestIntersection(scene, ray);
		//ne postoji sjeciste
		if(closest != null) {
			double[] rgbDouble = new double[3];
			determineColorFor(scene, ray, closest, rgbDouble);
			rgb[0] = (short) rgbDouble[0];
			rgb[1] = (short) rgbDouble[1];
			rgb[2] = (short) rgbDouble[2];
		}else {
			rgb[0] = rgb[1] = rgb[2] = 0;
		}
	}
	
	/**
	 * Metoda vraca najblizu tocku sjecista vektora i bilo kojeg od
	 * objekata iz scene. 
	 * @param scene scena na kojoj promatramo
	 * @param ray vektor cije sjeciste s objektima promatramo
	 * @return najblize sjeciste vektora
	 */
	private static RayIntersection findClosestIntersection(
			Scene scene, Ray ray) {
		RayIntersection closest = null;
		List<GraphicalObject> objects = scene.getObjects();
		for(GraphicalObject obj : objects) {
			RayIntersection current = obj.
					findClosestRayIntersection(ray);
			if(current != null && (closest == null || (
					current.getDistance() < closest.getDistance()))) {
				closest = current;
			}
		}
		return closest;
	}
	
	/**
	 * Metoda izracunava reflektirajucu komponentu boje.
	 * @param ls izvor svjetlosti
	 * @param ray reflektirani vektor
	 * @param rgb polje koje popunjavamo podacima o bojama
	 * @param inters sjeciste
	 */
	private static void ReflectiveColor(LightSource ls, Ray ray,
			double[] rgb, RayIntersection inters) {
		
		Point3D n = inters.getNormal();
		Point3D l = ls.getPoint().sub(inters.getPoint());
		
		Point3D lightProject = n.scalarMultiply(l.scalarProduct(n));
		Point3D r = lightProject.add(lightProject.negate().add(l).
				scalarMultiply(-1.0));
		
		Point3D v = ray.start.sub(inters.getPoint());
		double cosr_v = r.normalize().scalarProduct(v.normalize());

		if(Double.compare(cosr_v, 0) >= 0) {
			cosr_v = Math.pow(cosr_v,  inters.getKrn()); //mnozimo s 
			//koeficjetnom grubosti
			
			rgb[0] += ls.getR() * inters.getKrr() * cosr_v;
			rgb[1] += ls.getG() * inters.getKrg() * cosr_v;
			rgb[2] += ls.getB() * inters.getKrb() * cosr_v;
		}		

	}
	
	/**
	 * Metoda izracunava difuzijsku komponentu boje.
	 * @param ls izvor svjetlosti
	 * @param rgb polje koje popunjavamo podacima o bojama
	 * @param inters sjeciste
	 */
	private static void DiffuseColor(LightSource ls, double[] rgb, 
			RayIntersection inters) {
		
		Point3D l = ls.getPoint().sub(inters.getPoint()).normalize();
		Point3D n = inters.getNormal();
		double cosl_n = l.scalarProduct(n);
		
		//crveno
		rgb[0] += ls.getR() * inters.getKdr() * Math.max(cosl_n, 0.0);
		//zeleno
		rgb[1] += ls.getG() * inters.getKdg() * Math.max(cosl_n, 0.0);
		//plavo
		rgb[2] += ls.getB() * inters.getKdb() * Math.max(cosl_n, 0.0);
	}
	
	/**
	 * Metoda izracunava boju koju poprima piksel (proslijedeni RayIntersection).
	 * @param scene scena koju promatramo
	 * @param ray vektor koji povezuje tocku koju promatramo i glediste promatraca.
	 * @param closest sjeciste objekta i vektora
	 * @param rgb polje koje popunjavamo podacima o boji
	 */
	private static void determineColorFor(Scene scene, Ray ray, RayIntersection closest, double[] rgb) {
		rgb[0] = rgb[1] = rgb[2] = 15;
		List<LightSource> lights = scene.getLights();
		for(LightSource ls: lights) {
			Ray rray = Ray.fromPoints(ls.getPoint(), closest.getPoint());
			RayIntersection inters = findClosestIntersection(scene, rray);

			if(inters != null) {
				double lsDistance = ls.getPoint().sub(inters.getPoint()).norm();
				double eyeDistance = ls.getPoint().sub(closest.getPoint()).norm();

				if(Double.compare(lsDistance + EPSILON, eyeDistance) < 0) {
					continue;
				}else {
					DiffuseColor(ls, rgb, inters);
					ReflectiveColor(ls, ray, rgb, inters);
				}
			}
		}
	}
	
	
}
