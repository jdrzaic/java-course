package hr.fer.zemris.linearna.demo;

import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.Vector;

public class VectorDemo {

	public static void main(String[] args) {
		 IVector a = Vector.parseSimple("3 1 3");
		 IVector b = new Vector(1,1,1);
		 System.out.println(a.toString());
		 double n = a.add(new Vector(1,1,1)).add(b).scalarProduct(new Vector(2,3,2));
		 System.out.println(n);
		 IVector c = new Vector(-2,4,1);
		 IVector d = c.copyPart(5);
		 System.out.println(d.toString());
	}
}
