package hr.fer.zemris.java.gui.calc.impl;

import hr.fer.zemris.java.gui.calc.Calculator;
import hr.fer.zemris.java.gui.calc.Calculator.Environment;
import hr.fer.zemris.java.gui.calc.impl.operations.EqualsBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.IBinaryOperation;
import hr.fer.zemris.java.gui.calc.impl.operations.IUnaryOperation;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.component.RCPosition;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 * Razred implementira sucelje jednostavnog kalkulatora.
 * opisanog u {@link Calculator}
 * @author jelena
 */
public class CalculatorFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	/**
	 * greska koju ispisujemo korisniku kada je to potrebno
	 */
	private static final String ERR = new String("error");
	
	/**
	 * Instanca razreda {@link Environment}
	 * Sadrzi sve operacije
	 */
	Environment env;
	
	/**
	 * Zadnje upisani broj
	 */
	private String tempNumber;
	
	/**
	 * Trenutni rezultat
	 */
	private double result;
	
	/**
	 * Zadnji binarni operator
	 */
	private IBinaryOperation operatorB;
	
	/**
	 * Zadnji unarni operator
	 */
	private IUnaryOperation operatorU;
	
	/**
	 * true ako je zadnji operator bio binarna,
	 * false inace
	 */
	private boolean bin;
	
	/**
	 * true ako je zadnje unesen broj,
	 * false inace
	 */
	private boolean num;
	
	Stack<Double> stack;
	/**
	 * Konstruktor razreda
	 * @param e Environment
	 */
	public CalculatorFrame(Environment e) {
		stack = new Stack<Double>();
		setLocation(20, 50);
		setTitle("Calculator");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		env = e;
		num = false;
		result = 0;
		bin = true;
		tempNumber = null;
		operatorB = new EqualsBinaryOperation();
		operatorU = null;
		initGUI();
	}
	
	/**
	 * Metoda stvara GUI kalkulatora
	 */
	private void initGUI() {
		
		getContentPane().setLayout(new GridLayout(1, 1));
		JPanel p = new JPanel(new CalcLayout(5));
		setMinimumSize(p.getMinimumSize());
		JLabel display = new JLabel();
		NumListener nListener = new NumListener(display);
		OpBinaryListener bListener = new OpBinaryListener(display);
		OpUnaryListener uListener = new OpUnaryListener(display);
		OtherListener oListener = new OtherListener(display);
		InvListener iListener = new InvListener(display, uListener, bListener, p);
		initNumbers(p, nListener);
		initBinary(p, bListener);
		initUnary(p, uListener);
		initOther(p, oListener);
		//dodajemo display
		display.setOpaque(true);
		display.setBackground(Color.WHITE);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border border = BorderFactory.createEmptyBorder();
		display.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		p.add(display, new RCPosition(1, 1));
		JCheckBox inv = new JCheckBox("Inv");
		inv.addActionListener(iListener);
		p.add(inv, "5,7");
		getContentPane().add(p);
	}
	
	/**
	 * Metoda stvara buttone operacija 'clr', 'pop', 'push', 'res'
	 * @param p panel u koji dodajemo 
	 * @param l ActionListener
	 */
	private void initOther(JPanel p, OtherListener l) {
		JButton button = new JButton("clr");
		button.addActionListener(l);
		p.add(button, "1,7");
		button = new JButton("res");
		button.addActionListener(l);
		p.add(button, "2,7");
		button = new JButton("push");
		button.addActionListener(l);
		p.add(button, "3,7");
		button = new JButton("pop");
		button.addActionListener(l);
		p.add(button, "4,7");
	}

	/**
	 * Metoda stvara buttone unarnih operacija
	 * @param p panel u koji dodajemo
	 * @param l ActionListener
	 */
	private void initUnary(JPanel p, OpUnaryListener l) {
		JButton button = new JButton("sin");
		button.addActionListener(l);
		p.add(button, "2,2");
		button = new JButton("cos");
		button.addActionListener(l);
		p.add(button, "3,2");
		button = new JButton("tan");
		button.addActionListener(l);
		p.add(button, "4,2");
		button = new JButton("ctg");
		button.addActionListener(l);
		p.add(button, "5,2");
		button = new JButton("1/x");
		button.addActionListener(l);
		p.add(button, "2,1");
		button = new JButton("log");
		button.addActionListener(l);
		p.add(button, "3,1");
		button = new JButton("ln");
		button.addActionListener(l);
		p.add(button, "4,1");
		button = new JButton("+/-");
		button.addActionListener(l);
		p.add(button, "5,4");
	}
	
	/**
	 * Metoda koja stvara buttone binarnih operacija
	 * @param p panel u koji dodajemo
	 * @param l ActionListener
	 */
	private void initBinary(JPanel p, OpBinaryListener l) {
		JButton button = new JButton("+");
		button.addActionListener(l);
		p.add(button, "5,6");
		button = new JButton("-");
		button.addActionListener(l);
		p.add(button, "4,6");
		button = new JButton("*");
		button.addActionListener(l);
		p.add(button, "3,6");
		button = new JButton("/");
		button.addActionListener(l);
		p.add(button, "2,6");
		button = new JButton("=");
		button.addActionListener(l);
		p.add(button, "1,6");
		button = new JButton("x^n");
		button.addActionListener(l);
		p.add(button, "5,1");
	}

	/**
	 * Metoda stvara buttone numerickih vrijednosti(i decimalne tocke)
	 * @param p panel u koji dodajemo
	 * @param l ActionListener
	 */
	private void initNumbers(JPanel p, NumListener l) {
		JButton button1 = new JButton("0");
		button1.addActionListener(l);
		p.add(button1, "5,3");
		button1 = new JButton(".");
		button1.addActionListener(l);
		p.add(button1, "5,5");
		int number = 1;
		//dodajemo buttone s brojevima
		for(int i = 4; i >= 2; --i) {
			for(int j = 3; j <= 5; ++j) {
				JButton button = new JButton(Integer.toString(number));
				button.addActionListener(l);
				p.add(button, new RCPosition(i, j));
				++number;
			}
		}
	}
	
	/**
	 * Razred implementira sucelje ActionListener.
	 * Ova implementacija prati dogadaje buttona s 
	 * numerickim vrijednostima.
	 * @author jelena
	 *
	 */
	private class NumListener implements ActionListener {
		
		/**
		 * Label u koju upisujemo rezultat
		 */
		JLabel labela;
		
		/**
		 * Konstruktor razreda.
		 * @param labela labela u koju upisujemo
		 */
		public NumListener(JLabel labela) {
			this.labela = labela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			num = true;
			JButton button = (JButton)e.getSource();
			labela.setText(button.getText());
			if(tempNumber == null) {
				tempNumber = button.getText();
			}else {
				tempNumber += button.getText();
			}
			labela.setText(tempNumber);
		}
	}
	
	/**
	 * Razred implementira sucelje ActionListener.
	 * Ova implementacija prati dogadaje buttona s 
	 * binarnim operatorima.
	 * @author jelena
	 *
	 */
	private class OpBinaryListener implements ActionListener{
		
		/**
		 * labela u koju upisujemo
		 */
		JLabel labela;
		
		/**
		 * Konstruktor razreda
		 * @param labela labela u koju upisujemo rezultat
		 */
		public OpBinaryListener(JLabel labela) {
			this.labela = labela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			num = false;
			JButton button = (JButton)e.getSource();
			IBinaryOperation operator = env.getBinary().get(button.getText());
			process(operator);
		}
		
		/**
		 * Metoda odraduje potrebni posao kalkulatora kada je
		 * unesena vrijednost binarni operator
		 * @param o instanca neke od implementacija sucelja IBinaryOperation
		 */
		private void process(IBinaryOperation o) {
			if(bin){
				if(operatorB instanceof EqualsBinaryOperation && tempNumber == null) {
					operatorB = o;
					tempNumber = null;
					return;
				}
				try {
					result = operatorB.compute(result, Double.parseDouble(tempNumber));
				}catch(IllegalArgumentException e) {
					labela.setText(ERR);
				}
			}else {
				try {
					result = operatorU.compute(result);
				}catch(IllegalArgumentException e) {
					labela.setText(ERR);
				}
			}
			
			tempNumber = null;
			bin = true;
			operatorB = o;
			if(!labela.getText().equals(ERR)) {
				labela.setText(Double.toString(result));
			}
		}
	}
	
	/**
	 * Razred implementira sucelje ActionListener.
	 * Ova implementacija prati dogadaje buttona s 
	 * unarnim operatorima.
	 * @author jelena
	 *
	 */
	private class OpUnaryListener implements ActionListener{
		
		/**
		 * labela u koju upisujemo
		 */
		JLabel labela;
		
		/**
		 * Konstruktor razreda
		 * @param labela labela u koju upisujemo rezultat
		 */
		public OpUnaryListener(JLabel labela) {
			this.labela = labela;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			num = false;
			JButton button = (JButton)e.getSource();
			IUnaryOperation operator = env.getUnary().get(button.getText());
			process(operator);
		}
		
		/**
		 * Metoda odraduje potrebni posao kalkulatora kada je
		 * unesena vrijednost unarni operator
		 * @param o instanca neke od implementacija sucelja IUnaryOperation
		 */
		private void process(IUnaryOperation o) {
			if(bin){
				if(operatorB instanceof EqualsBinaryOperation && tempNumber == null) {
					operatorU = o;
					tempNumber = null;
					bin = false;
					labela.setText(Double.toString(o.compute(result)));
					return;
				}
				try {
					result = operatorB.compute(result, Double.parseDouble(tempNumber));
				}catch(IllegalArgumentException e) {
					labela.setText(ERR);
				}
			}else {
				try {
					result = operatorU.compute(result);
				}catch(IllegalArgumentException e) {
					labela.setText(ERR);
				}
			}
			tempNumber = null;
			bin = false;
			operatorU = o;
			labela.setText(Double.toString(o.compute(result)));
		}
		
	}
	
	/**
	 * Razred implementira sucelje ActionListener.
	 * Ova implementacija prati dogadaje buttona s 
	 * operatorima 'pop', 'clr', 'res', 'push'.
	 * @author jelena
	 *
	 */
	private class OtherListener implements ActionListener{
		
		/**
		 * labela u koju upisujemo
		 */
		JLabel labela;
		
		/**
		 * Konstruktor razreda
		 * @param labela labela u koju upisujemo
		 */
		public OtherListener(JLabel labela) {
			this.labela = labela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(button.getText().equals("clr")) {
				processClr();
			}else if(button.getText().equals("res")) {
				processRes();
			}else if(button.getText().equals("pop")) {
				processPop();
			}else {
				processPush();
			}
		}
		
		/**
		 * Metoda odraduje potrebni posao kalkulatora kada je
		 * unesena vrijednost 'clr' operator
		 */
		private void processClr() {
			if(tempNumber != null) {
				tempNumber = null;
				labela.setText("");
			}
		}
		
		/**
		 * Metoda odraduje potrebni posao kalkulatora kada je
		 * unesena vrijednost 'res' operator
		 */
		private void processRes() {
			result = 0;
			bin = true;
			tempNumber = null;
			operatorB = new EqualsBinaryOperation();
			operatorU = null;
			labela.setText("0.0");
		}
		
		/**
		 * Metoda odraduje potrebni posao kalkulatora kada je
		 * unesena vrijednost 'push' operator
		 */
		private void processPush() {
			stack.push(Double.parseDouble(tempNumber));
		}
		
		/**
		 * Metoda odraduje potrebni posao kalkulatora kada je
		 * unesena vrijednost 'pop' operator
		 */
		private void processPop() {
			if(stack.isEmpty()) {
				labela.setText(ERR);
			}else {
				labela.setText(Double.toString(stack.peek()));
				//zadnje je bio broj
				if(num) tempNumber = Double.toString(stack.pop());
				else result = stack.pop();
			}
		}
	}
	
	/**
	 * Razred implementira sucelje ActionListener.
	 * Ova implementacija prati dogadaje buttona s 
	 * operatorom Inv.
	 * @author jelena
	 */
	private class InvListener implements ActionListener{
		/**
		 * Labela u koju upisujemo
		 */
		JPanel p;
		public InvListener(JLabel labela, OpUnaryListener l, OpBinaryListener k, JPanel p) {
			this.p = p;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			init();
		}
		
		/**
		 * Metoda koja provodi operacije potrebne kada se 
		 * Inv selektira/deselektira.
		 */
		private void init() {
			int n = p.getComponentCount();
			for(int i = 0; i < n; ++i) {
				if(p.getComponent(i) instanceof JButton) {
					JButton button = (JButton)(p.getComponent(i));
					if(!button.getText().equals("+/-") && 
							env.getUnary().containsKey(button.getText()) || 
							button.getText().equals("x^n") || button.getText().equals("x^(1/n)")) {
						button.setText(setText(button.getText()));
						
					}
				}
			}
		}
		
		/**
		 * Metoda vraca promjenjenu vrijednost proslijedenog stringa,
		 * td. dobivamo inverzne operacije
		 * @param text string koji provjeravamo
		 * @return novi tekst
		 */
		private String setText(String text) {
			if(text.equals("sin") || text.equals("cos") || text.equals("tan") ||
					text.equals("ctg")) {
				return "ar" + text;
			}else if(text.equals("log")){
				return "e^";
			}else if(text.equals("ln")) {
				return "10^";
			}else if(text.equals("x^n")) {
				return "x^(1/n)";
			}else if(text.equals("arcos") || text.equals("arsin") || text.equals("artan")
					|| text.equals("arctg")) {
				return text.substring(2);
			}else if(text.equals("e^")){
				return "ln";
			}else if(text.equals("10^")) {
				return "log";
			}else if(text.equals("x^(1/n)")) {
				return "x^n";
			}else return "1/x";
		}
	}
}
