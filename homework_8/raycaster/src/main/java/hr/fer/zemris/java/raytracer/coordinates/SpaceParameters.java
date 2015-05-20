package hr.fer.zemris.java.raytracer.coordinates;

import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Scene;

/**
 * Razred sadrzi staticke metode za izracunavanje parametara koji sluze za opis 
 * prostora u kojem se nalazi promatrana {@link Scene}
 * @author Jelena Drzaic
 *
 */
public interface SpaceParameters {
	
	/**
	 * Metoda vraca vektor i - jedinicni vektor u smjeru x osi 
	 * @param eye pozicija oka promatraca
	 * @param view promatrana tocka 
	 * @param yAxis Y-os, jedinicni vektor
	 * @param zAxis Z-os, jedinicni vektor
	 * @param viewUp view-up vektor koji se koristi za izracunavanje y osi
	 * @return jedinicni vektor u smjeru X osi
	 */
	public static Point3D getXAxis(Point3D yAxis, Point3D zAxis, 
			Point3D eye, Point3D view, Point3D viewUp) {
		
		return zAxis.vectorProduct(yAxis).normalize();
	}
	
	/**
	 * Metoda vraca vektor i - jedinicni vektor u smjeru x osi 
	 * @param eye pozicija oka promatraca
	 * @param view promatrana tocka 
	 * @param zAxis Z-os, jedinicni vektor
	 * @param viewUp view-up vektor koji se koristi za izracunavanje y osi
	 * @return jedinicni vektor u smjeru Y osi
	 */
	public static Point3D getYAxis(Point3D zAxis, Point3D eye, Point3D view, 
		Point3D viewUp) {
		return viewUp.sub(zAxis.scalarMultiply(
				viewUp.scalarProduct(zAxis))).normalize();
	}
	
	/**
	 * Metoda vraca vektor z - jedinicni vektor u smjeru Z osi 
	 * @param eye pozicija oka promatraca
	 * @param view promatrana tocka 
	 * @return jedinicni vektor u smjeru Z osi
	 */
	public static Point3D getZAxis(Point3D eye, Point3D view) {
		return view.sub(eye).modifyNormalize();
	}
	
	/**
	 * Metoda vraca 3D koordinate lijevog gornjeg kuta pravokutnika u promatranoj ravnini
	 * opisanoj u zadatku domace zadace
	 * @param view promatrana tocka
	 * @param xAxis jedinicni vektor u smjeru x osi
	 * @param yAxis jedinicni vektor u smjeru y osi
	 * @param horizontal sirina promatranog prostora
	 * @param vertical visina promatranog prostora
	 * @return koordinate lijevog gornjeg kuta
	 */
	public static Point3D getCorner(Point3D xAxis, Point3D yAxis,
			Point3D view, double horizontal, double vertical) {
		
		return view.sub(xAxis.scalarMultiply(horizontal / 2)).add(
				yAxis.scalarMultiply(vertical / 2));
	}
	
	/**
	 * Metoda vraca 3D koordinate tocke u promatranoj ravnini
	 * @param horizontal sirina promatranog prostora
	 * @param vertical visina promatranog prostora
	 * @param width sirina promatrane ravnine, u pikselima
	 * @param height visina promatrane ravnine, u pikselima
	 * @param x x-koordinata tocke, u pikselima
	 * @param y y-koordinata tocke, u pikselima
	 * @param i jedinicni vektor u smjeru x
	 * @param j jedinicni vektor u smjeru y
	 * @param corner gornji lijevi kut ravnine
	 * @return Point3D, koordinate tocke promatrane ravnine u prostoru
	 */
	public static Point3D getScreenPoint(double horizontal, double vertical, 
			int width, int height, int x, int y, Point3D i, Point3D j, Point3D corner) {
		return corner.add(i.scalarMultiply
				(x / (width - 1.0) * horizontal)).sub(
						j.negate().scalarMultiply(y / (height - 1.0)* vertical));	
	}
}
