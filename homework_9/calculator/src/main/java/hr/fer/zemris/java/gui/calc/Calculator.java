package hr.fer.zemris.java.gui.calc;

import java.util.HashMap;

import hr.fer.zemris.java.gui.calc.impl.CalculatorFrame;
import hr.fer.zemris.java.gui.calc.impl.operations.ArcosUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.ArctgUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.ArsinUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.ArtanUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.CosUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.CtgUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.DivideBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.EqualsBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.Exp10UnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.ExpUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.IBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.IUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.InverseUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.LnUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.LogUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.MinusBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.MultiplyBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.PlusBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.PlusUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.PotBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.RootBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.SinUnaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.TanUnaryOperation;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Razred reprezentira jednostavni kalkulator.
 * Racunanje se provodi pritiskom na tipke smjestene u GUI-u.
 * Zadace aritmetickih operacija su standarne.
 * Oznacavanjem JCheckBOx komponente dobivamo inverzne operacije,
 * tamo gdje to ima smisla.
 * @author jelena
 *
 */
public class Calculator {
	
	/**
	 * Klasa pohranjuje operacije(operatore) koristene
	 * u kalkulatoru.
	 * @author jelena
	 *
	 */
	public static class Environment {
		
		/**
		 * binarne operacije
		 * jednako takoder spada pod ovu kategoriju.
		 */
		private HashMap<String, IBinaryOperation> binary;
		/**
		 * unarne operacije
		 */
		private HashMap<String, IUnaryOperation> unary;
		
		/**
		 * Konstruktor razreda
		 * Mape puni dostupnim operacijama
		 */
		public Environment() {
			binary = new HashMap<String, IBinaryOperation>();
			
			unary = new HashMap<String, IUnaryOperation>();
			
			unary.put("+/-", new PlusUnaryOperation());
			unary.put("sin", new SinUnaryOperation());
			unary.put("cos", new CosUnaryOperation());
			unary.put("tan", new TanUnaryOperation());
			unary.put("ctg", new CtgUnaryOperation());
			unary.put("1/x", new InverseUnaryOperation());
			unary.put("ln", new LnUnaryOperation());
			unary.put("log", new LogUnaryOperation());
			unary.put("arsin", new ArsinUnaryOperation());
			unary.put("arcos", new ArcosUnaryOperation());
			unary.put("artan",new ArtanUnaryOperation());
			unary.put("arctg", new ArctgUnaryOperation());
			unary.put("10^", new Exp10UnaryOperation());
			unary.put("e^", new ExpUnaryOperation());
			
			binary.put("x^(1/n)", new RootBinaryOperation());
			binary.put("+", new PlusBinaryOperation());
			binary.put("-", new MinusBinaryOperation());
			binary.put("*", new MultiplyBinaryOperation());
			binary.put("/", new DivideBinaryOperation());
			binary.put("x^n", new PotBinaryOperation());
			binary.put("=", new EqualsBinaryOperation());
		}
		
		/**
		 * Metoda sluzi za dohvat mape binarnih operacija.
		 * @return mapa binarnih operacija
		 */
		public HashMap<String, IBinaryOperation> getBinary() {
			return binary;
		}
		
		/**
		 * Metoda sluzi za dohvat mape unarnih operacija.
		 * @return mapa unarnih operacija
		 */
		public HashMap<String, IUnaryOperation> getUnary() {
			return unary;
		}
	}
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije, zanemaruju se
	 */
	public static void main(String[] args) {	
		Environment e = new Environment();
		SwingUtilities.invokeLater(()-> {
			JFrame frame = new CalculatorFrame(e);
			frame.pack(); 
			frame.setVisible(true);
		});
	}

}
