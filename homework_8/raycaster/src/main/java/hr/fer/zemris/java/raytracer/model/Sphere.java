package hr.fer.zemris.java.raytracer.model;

/**
 * Razred nasljeduje apstraktni razred GraphicalObject.
 * Razred reprezentira sferu zadanu centrom te radijusom.
 * @author Jelena Drzaic
 *
 */
public class Sphere extends GraphicalObject {

	/**
	 * Konstanta koja se koristi kod racunanja s podatcima tipa double
	 * u ovoj klasi
	 */
	public static final double DELTA = 0.0001;
	
	/**
	 * Centar sfere
	 */
	private Point3D center;
	
	/**
	 * Radijus sfere
	 */
	private double radius;
	
	/**
	 * Difuzne komponente boje
	 */
	private double kdr; //crvena
	private double kdg; //zelena
	private double kdb; //plava
	
	/**
	 * Reflektirane komponente boje
	 */
	private double krr; //crvena
	private double krg; //zelena
	private double krb; //plava
	private double krn; //konstanta grubosti materijala
	
	/**
	 * Konstruktor razreda
	 * @param center centar sfere
	 * @param radius radijus sfere
	 * @param kdr difuzna komp. crvene
	 * @param kdg difuzna komp. zelene
	 * @param kdb difuzna komp. plave
	 * @param krr reflektirana komp. crvene
	 * @param krg reflektirana komp. zelene
	 * @param krb reflektirana komp. plave
	 * @param krn koeficjent grubosti
	 */
	public Sphere(Point3D center, double radius, double kdr, double kdg,
			double kdb, double krr, double krg, double krb, double krn) {
		this.kdr = kdr;
		this.kdg = kdg;
		this.kdb = kdb;
		this.krr = krr;
		this.krg = krg;
		this.krb = krb;
		this.krn = krn;
		this.center = center;
		this.radius = radius;
	}

	/**
	 * Metoda vraca implementaciju klase RayIntersection.
	 *  
	 */
	@Override
	public RayIntersection findClosestRayIntersection(Ray ray) {
		Point3D startPoint = ray.start;
		Point3D dir = ray.direction;
		Point3D sub = startPoint.sub(this.center);
		double b = 2 * dir.scalarProduct(sub);
		double c = sub.scalarProduct(sub) - radius * radius;
		double determinant = b * b - 4 * c;
		double t = 0;
		boolean out;
		if(determinant < 0) {
			return null;
		}else {
			double t1 = - (b + Math.sqrt(b * b - 4 * c)) / 2;
			double t2 = (-b + Math.sqrt(b * b - 4 * c)) / 2;
			if(Math.abs(t1 - t2) < DELTA) {
				t = t1;
				out = true;
			} else {
				if((t1 > DELTA) && (t2 > DELTA)) {
					t = t1;
					out = true;
				} else if(t2 > DELTA) {
					t = t2;
					out = false;
				} else {
					return null;
				}
			} 
		}
		return getRaySphereIntersection(startPoint, t, dir, out);
	}

	/**
	 * Generiranje zrake presjeka iz argumenata.
	 * @param ts pocetna tocka
	 * @param t1 koeficijent
	 * @param d vektor smjera
	 * @param outer true ako zraka dolazi izvana, inace false.
	 * @return zraka presjeka.
	 */
	private RayIntersection getRaySphereIntersection(Point3D startPoint, double t1, Point3D d, boolean outer) {
		final Point3D intersection = startPoint.add(d.scalarMultiply(t1));
		double distance = intersection.sub(startPoint).scalarProduct(
				intersection.sub(startPoint));
		return new RayIntersection(intersection, Math.sqrt(distance), outer) {

			@Override
			public Point3D getNormal() {
				return this.getPoint().sub(center).normalize();
			}

			@Override
			public double getKrb() {
				return krb;
			}
			
			@Override
			public double getKrr() {
				return krr;
			}

			@Override
			public double getKrn() {
				return krn;
			}

			@Override
			public double getKrg() {
				return krg;
			}

			@Override
			public double getKdr() {
				return kdr;
			}

			@Override
			public double getKdg() {
				return kdg;
			}

			@Override
			public double getKdb() {
				return kdb;
			}
		};
	};
}
